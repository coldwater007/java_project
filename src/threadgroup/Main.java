package threadgroup;

import java.util.concurrent.TimeUnit;


/*
 线程组只是提供了一种统计多个线程信息的方法，相当于建立了对线程的观察者模型
 缺点：
 
 并没有提供细粒度的对线程任务的管理
 
 
 * 
 * 
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建一个线程组
		ThreadGroup threadGroup=new  ThreadGroup("Searcher");
		
		Result result=new Result();
		//Searcher是实现了runnable接口的线程类
		Searcher searchTask=new Searcher(result);
		for(int i=0;i<10;i++)
		{
			Thread thread=new Thread(threadGroup,searchTask);
			thread.start(); //启动每一个创建的线程
			try
			{
				TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("===================");
		System.out.println("线程组信息");
		threadGroup.list();//打印线程组信息
		System.out.println("===================");
		//遍历线程组
		Thread[] threads=new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);//读出当前存活的线程
		for(int i=0;i<threads.length;i++)
		{
			System.out.printf("Thread %s %s \n",threads[i].getName(),threads[i].getState() );
			
		}
		waitFinish(threadGroup);
		threadGroup.interrupt();
	}
	public static void waitFinish(ThreadGroup threadGroup)
	{
		while(threadGroup.activeCount()>9)
		{
			try
			{
				TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

}
