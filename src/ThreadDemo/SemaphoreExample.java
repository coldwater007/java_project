package ThreadDemo;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
	
	
	private final Semaphore placeSemaphore=new Semaphore(5);
	
	public boolean parking() throws InterruptedException
	{
		if(placeSemaphore.tryAcquire())
		{
			System.out.println(Thread.currentThread().getName()+":停车成功");
			return true;
		}
		else
		{
			System.out.println(Thread.currentThread().getName()+":没有空位");
		    return false;
		}
	}
	
	public void leaving() throws InterruptedException
	{
		placeSemaphore.release();
		System.out.println(Thread.currentThread().getName()+":开走");
	}

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		int tryToParkCnt=10;//10辆车同时抢车位
		SemaphoreExample semaphoreExample=new SemaphoreExample();
		Thread[] parkers=new Thread[tryToParkCnt];
		for(int i=0;i<tryToParkCnt;i++)
		{
			parkers[i]=new Thread(
					
					new Runnable()
					{
						
						@Override
						public void run()
						{
							try
							{
								long randomTime=(long)(Math.random()*1000);
								Thread.sleep(randomTime);
								if(semaphoreExample.parking()) //获取资源 抢夺信号量
								{
									long parkingTime=(long)(Math.random()*1000);
									Thread.sleep(parkingTime);
									semaphoreExample.leaving();//调用release释放信号量
								}
							}catch(InterruptedException e)
							{
								e.printStackTrace();
							}
						}
						
					}
					);
			parkers[i].start();
		}
		
		for(int i=0;i<tryToParkCnt;i++)
		{
			parkers[i].join();
			
		}

	}

}
