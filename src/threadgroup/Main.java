package threadgroup;

import java.util.concurrent.TimeUnit;


/*
 �߳���ֻ���ṩ��һ��ͳ�ƶ���߳���Ϣ�ķ������൱�ڽ����˶��̵߳Ĺ۲���ģ��
 ȱ�㣺
 
 ��û���ṩϸ���ȵĶ��߳�����Ĺ���
 
 
 * 
 * 
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ���߳���
		ThreadGroup threadGroup=new  ThreadGroup("Searcher");
		
		Result result=new Result();
		//Searcher��ʵ����runnable�ӿڵ��߳���
		Searcher searchTask=new Searcher(result);
		for(int i=0;i<10;i++)
		{
			Thread thread=new Thread(threadGroup,searchTask);
			thread.start(); //����ÿһ���������߳�
			try
			{
				TimeUnit.SECONDS.sleep(1);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("===================");
		System.out.println("�߳�����Ϣ");
		threadGroup.list();//��ӡ�߳�����Ϣ
		System.out.println("===================");
		//�����߳���
		Thread[] threads=new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);//������ǰ�����߳�
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
