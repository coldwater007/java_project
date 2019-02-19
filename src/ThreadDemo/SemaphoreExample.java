package ThreadDemo;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
	
	
	private final Semaphore placeSemaphore=new Semaphore(5);
	
	public boolean parking() throws InterruptedException
	{
		if(placeSemaphore.tryAcquire())
		{
			System.out.println(Thread.currentThread().getName()+":ͣ���ɹ�");
			return true;
		}
		else
		{
			System.out.println(Thread.currentThread().getName()+":û�п�λ");
		    return false;
		}
	}
	
	public void leaving() throws InterruptedException
	{
		placeSemaphore.release();
		System.out.println(Thread.currentThread().getName()+":����");
	}

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		int tryToParkCnt=10;//10����ͬʱ����λ
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
								if(semaphoreExample.parking()) //��ȡ��Դ �����ź���
								{
									long parkingTime=(long)(Math.random()*1000);
									Thread.sleep(parkingTime);
									semaphoreExample.leaving();//����release�ͷ��ź���
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
