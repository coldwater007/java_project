package ThreadDemo;

public class ThreadDemo0 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestThread0 thread0=new TestThread0();
		new Thread(thread0,"thread1").start();
		new Thread(thread0,"thread2").start();
		new Thread(thread0,"thread3").start();
		new Thread(thread0,"thread4").start();
		
		

	}

}
class TestThread0  implements Runnable
{
	private  volatile  int tickets=100;//staticΪ�����߳�������
	public void run()
	{
		while(true)
		{
			sale();//ͬһʱ��ֻ��һ���߳���ִ����Ʊ���� ����tickets��д
			if(tickets<0)
				break;
		
		}
	}
	private synchronized void sale()
	{
		if(tickets>0)
		{
		
			  System.out.println(Thread.currentThread().getName()+"is selling ticket "+tickets--);
			  
		}
	}
}
