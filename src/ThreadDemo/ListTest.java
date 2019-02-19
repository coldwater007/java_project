package ThreadDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		//�̲߳���ȫ
		List<String> unsafelist=new ArrayList<String>();
		//�̰߳�ȫ
		List<String> safelist1=Collections.synchronizedList(new ArrayList<String>());
		//�̰߳�ȫ
		CopyOnWriteArrayList<String> safelist2=new CopyOnWriteArrayList<String>();
		
		ListThread t1=new ListThread(unsafelist);
		ListThread t2=new ListThread(safelist1);
		ListThread t3=new ListThread(safelist2);
		
		for(int i=0;i<10;i++)
		{
			Thread t=new Thread(t1,String.valueOf(i));
			t.start();
		}
		for(int i=0;i<10;i++)
		{
			Thread t=new Thread(t2,String.valueOf(i));
			t.start();
		}
		for(int i=0;i<10;i++)
		{
			Thread t=new Thread(t3,String.valueOf(i));
			t.start();
		}
		//�ȴ������߳�ִ����
		Thread.sleep(2000);
		System.out.println("Thread1.list.size()="+t1.list.size());
		System.out.println("Thread2.list.size()="+t2.list.size());
		System.out.println("Thread3.list.size()="+t3.list.size());

		
		System.out.println("unsafeList");
		for(String s:t1.list)
		{
			if(s==null)
			{
				System.out.print("null ");
			}
			else
			{
				System.out.print(s+" ");
			}
		}
		
		System.out.println("safeList1");
		for(String s:t2.list)
		{
			if(s==null)
			{
				System.out.print("null ");
			}
			else
			{
				System.out.print(s+" ");
			}
		}
		
		System.out.println("safeList2");
		for(String s:t3.list)
		{
			if(s==null)
			{
				System.out.print("null ");
			}
			else
			{
				System.out.print(s+" ");
			}
		}
	}

}

class ListThread implements Runnable
{
	public List<String> list;
	public ListThread(List<String> list)
	{
		this.list=list;
	}
	@Override
	public void run()
	{
		int i=0;
		while(i<10)
		{
			try
			{
				Thread.sleep(10);
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			list.add(Thread.currentThread().getName());
			i++;
		}
	}
	
	
	
	
}
