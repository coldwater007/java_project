package ThreadDemo;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockExample {
	
	
	
	/*
	 * 
	 * ����ģ�⣺
	 * �̲��
	 * ���̲�������������Ҫ�Ŷ� ����
	 * �����̲�� �ж���Ա��   һ��������
	 * �ϰ鸺��д�¶��� Ա�����ϲ鿴���������̲� д���ڼ䲻����鿴
	 * ���Ա����ͬʱ�鿴����  �鿴ʱ�ϰ岻����д�¶���
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	private static final ReentrantLock queueLock=new ReentrantLock();//��������
	private static final ReentrantReadWriteLock orderLock=new ReentrantReadWriteLock();//�������д��
	public static void main(String[] args) throws InterruptedException {
		
		//buyMikeTea();
		handleOrder();

	}
	
	public void tryToBuyMikeTea() throws InterruptedException
	{
		boolean flag=true;
		while(flag)
		{
			//tryLock���� ����Ƿ�ɻ���� ���Ծͻ�ȡ
			if(queueLock.tryLock())
			{
				long thinkingTime=(long)(Math.random()*500);
				Thread.sleep(thinkingTime);
				System.out.println(Thread.currentThread().getName()+": ����Ҫһ���̲�");
				flag=false;
				queueLock.unlock();//�ͷ���
			}
			else
			{
				System.out.println(Thread.currentThread().getName()+"���ٵȵ�");
				
			}
			if(flag)
			{
				Thread.sleep(1000);
			}
		}
	}
	
	
	public void addOrder() throws InterruptedException
	{
		orderLock.writeLock().lock(); //���д��
		long writingTime=(long)(Math.random()*1000);
		Thread.sleep(writingTime);
		System.out.println("�ϰ��һ���¶���");
		orderLock.writeLock().unlock();//�ͷ���
	}
	public void viewOrder() throws InterruptedException
	{
		orderLock.readLock().lock();
		long readingTime=(long)(Math.random()*500);
		Thread.sleep(readingTime);
		System.out.println(Thread.currentThread().getName()+":�鿴������");
		orderLock.readLock().unlock();
	}
	
	
	public static void buyMikeTea() throws InterruptedException
	{
		LockExample lockExample=new LockExample();
		int STUDENTS_CNT=10;
		Thread[] students=new Thread[STUDENTS_CNT];
		for(int i=0;i<STUDENTS_CNT;i++)
		{
			students[i]=new Thread(
					
					new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
								long walkingTime=(long)(Math.random()*1000);
								Thread.sleep(walkingTime);
								lockExample.tryToBuyMikeTea();
							}catch(InterruptedException e)
							{
								e.printStackTrace();
							}
						}
					}
					
					);
			students[i].start();//���������߳� ��ִ��
		}
		for(int i=0;i<STUDENTS_CNT;i++)
		{
			students[i].join();
		}
		
	}
	
	
	public static void handleOrder() throws InterruptedException
	{
		LockExample lockExample=new LockExample();
		Thread boss=new Thread(
				new Runnable()
				{
					@Override
					public void run()
					{
						while(true)
						{
							try
							{
								lockExample.addOrder();
								long waitingTime=(long)(Math.random()*1000);
								Thread.sleep(waitingTime);
							}catch(InterruptedException e)
							{
								e.printStackTrace();
							}
						}
					}
				}
				);
		boss.start();
		int workerCnt=3;
		Thread[] workers=new Thread[workerCnt];//��Ա�̸߳�������
		for(int i=0;i<=workerCnt;i++)
		{
			workers[i]=new Thread(
					new Runnable()
					{
						@Override
						public void run()
						{
							while(true)
							{
								try
								{
									lockExample.viewOrder();
									long workingTime=(long)(Math.random()*1000);
									Thread.sleep(workingTime);
								}catch(InterruptedException e)
								{
									e.printStackTrace();
								}
							}
						}
					}
					);
			workers[i].start();
		}
		
		
		
		
		
	}

}
