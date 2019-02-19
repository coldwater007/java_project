package ThreadDemo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int runnerCnt=10;//10名运动员
		//该信号 初始化为1    主程序-》控制10个子线程向后执行
		//（开始10个线程等待-》发令CountDown(1->0)-》  10个线程继续执行）
		CountDownLatch startSignal=new CountDownLatch(1);//
		
		//该信号 初始化为10 表示需要10个子线程
		//主线程await等待  -》10个线程分别CountDown(10->0)-》主程序结束比赛
		CountDownLatch doneSignal=new CountDownLatch(10);
		
		for(int i=0;i<runnerCnt;i++)
		{
			new Thread(new Worker(startSignal,doneSignal)).start();
		}
		System.out.println("准备就绪 ");
		startSignal.countDown();//主程序将1->0  其它子线程被唤醒
		System.out.println("比赛开始");
		doneSignal.await();//主程序阻塞等待 当其它子线程10->0后 主程继续
		System.out.println("比赛结束");

	}
	
}
class Worker implements Runnable
{
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;
	Worker(CountDownLatch startSignal,CountDownLatch doneSignal)
	{
		this.startSignal=startSignal;
		this.doneSignal=doneSignal;
	}
	
	public void run()
	{
		try
		{
			startSignal.await();//一开始所有子线程处于await状态等待主线程count->0
		    long runTime=(long)(Math.random()*100);
		    Thread.sleep(runTime);
			System.out.println(Thread.currentThread().getName()+"跑完全程：");
			doneSignal.countDown();//完成计数器--
		
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}

