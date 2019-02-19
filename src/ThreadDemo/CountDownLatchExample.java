package ThreadDemo;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		int runnerCnt=10;//10���˶�Ա
		//���ź� ��ʼ��Ϊ1    ������-������10�����߳����ִ��
		//����ʼ10���̵߳ȴ�-������CountDown(1->0)-��  10���̼߳���ִ�У�
		CountDownLatch startSignal=new CountDownLatch(1);//
		
		//���ź� ��ʼ��Ϊ10 ��ʾ��Ҫ10�����߳�
		//���߳�await�ȴ�  -��10���̷ֱ߳�CountDown(10->0)-���������������
		CountDownLatch doneSignal=new CountDownLatch(10);
		
		for(int i=0;i<runnerCnt;i++)
		{
			new Thread(new Worker(startSignal,doneSignal)).start();
		}
		System.out.println("׼������ ");
		startSignal.countDown();//������1->0  �������̱߳�����
		System.out.println("������ʼ");
		doneSignal.await();//�����������ȴ� ���������߳�10->0�� ���̼���
		System.out.println("��������");

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
			startSignal.await();//һ��ʼ�������̴߳���await״̬�ȴ����߳�count->0
		    long runTime=(long)(Math.random()*100);
		    Thread.sleep(runTime);
			System.out.println(Thread.currentThread().getName()+"����ȫ�̣�");
			doneSignal.countDown();//��ɼ�����--
		
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}

