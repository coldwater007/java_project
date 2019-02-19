package ThreadDemo;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockExample {
	
	
	
	/*
	 * 
	 * 问题模拟：
	 * 奶茶店
	 * 买奶茶的人如果看到需要排队 则不买
	 * 假设奶茶店 有多名员工   一个订单本
	 * 老伴负责写新订单 员工不断查看订单制作奶茶 写的期间不允许查看
	 * 多个员工可同时查看订单  查看时老板不允许写新订单
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	private static final ReentrantLock queueLock=new ReentrantLock();//可重入锁
	private static final ReentrantReadWriteLock orderLock=new ReentrantReadWriteLock();//可重入读写锁
	public static void main(String[] args) throws InterruptedException {
		
		//buyMikeTea();
		handleOrder();

	}
	
	public void tryToBuyMikeTea() throws InterruptedException
	{
		boolean flag=true;
		while(flag)
		{
			//tryLock方法 检查是否可获得锁 可以就获取
			if(queueLock.tryLock())
			{
				long thinkingTime=(long)(Math.random()*500);
				Thread.sleep(thinkingTime);
				System.out.println(Thread.currentThread().getName()+": 我需要一杯奶茶");
				flag=false;
				queueLock.unlock();//释放锁
			}
			else
			{
				System.out.println(Thread.currentThread().getName()+"：再等等");
				
			}
			if(flag)
			{
				Thread.sleep(1000);
			}
		}
	}
	
	
	public void addOrder() throws InterruptedException
	{
		orderLock.writeLock().lock(); //添加写锁
		long writingTime=(long)(Math.random()*1000);
		Thread.sleep(writingTime);
		System.out.println("老板加一笔新订单");
		orderLock.writeLock().unlock();//释放锁
	}
	public void viewOrder() throws InterruptedException
	{
		orderLock.readLock().lock();
		long readingTime=(long)(Math.random()*500);
		Thread.sleep(readingTime);
		System.out.println(Thread.currentThread().getName()+":查看订单本");
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
			students[i].start();//创建购买线程 并执行
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
		Thread[] workers=new Thread[workerCnt];//店员线程负责生产
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
