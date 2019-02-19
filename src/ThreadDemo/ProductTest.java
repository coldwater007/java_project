package ThreadDemo;

import java.util.Random;

public class ProductTest {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		Storage storage=new Storage();
		Thread consumer1=new Thread(new Consumer(storage));
		consumer1.setName("������1");
		Thread consumer2=new Thread(new Consumer(storage));
		consumer2.setName("������2");
		Thread producer1=new Thread(new Producer(storage));
		producer1.setName("������1");
		Thread producer2=new Thread(new Producer(storage));
		producer2.setName("������2");
		producer1.start();
		producer2.start();
		Thread.sleep(1000);
		consumer1.start();
		consumer2.start();
		

	}

}

class Storage
{
	private Product[] products=new Product[10];
	private int top=0;
	//�ֿ��СΪ10
	//��������ֿ�����Ʒ
	public synchronized void push(Product product)
	{
		while(top==products.length)
		{
			try
			{
				System.out.println("producer wait");
				wait();//�ȴ�
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		products[top++]=product;
		System.out.println(Thread.currentThread().getName()+"������"+product);
		System.out.println("producer notifyAll");
		notifyAll();//���������߳�
	}
	
	
	//�����ߴӲֿ�ȡ����Ʒ
	
	public synchronized Product pop()
	{
		//��Ҫ�ֿ��а�����Ʒ
		
		while(top==0)
		{
			try
			{
				System.out.println("consumer wait");
				wait();//�ֿ�� �ȴ�
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		--top;
		Product p=new Product(products[top].getId(),products[top].getName());
		products[top]=null;
		System.out.println(Thread.currentThread().getName()+"�����˲�Ʒ"+p);
		System.out.println("consumer notifyAll");
		notifyAll();//���ѵȴ��߳�
		return p;
	}
}

class Consumer implements Runnable
{
	private Storage storage;
	public Consumer(Storage storage)
	{
		this.storage=storage;
	}
	
	public void run()
	{
		int i=0;
		while(i<10)
		{
			i++;
			storage.pop();
			try
			{
				Thread.sleep(1000);
				
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
class Producer implements Runnable
{
	private Storage storage;
	public Producer(Storage storage)
	{
		this.storage=storage;
	}
	public void run()
	{
		int i=0;
		Random r=new Random();
		while(i<10)
		{
			i++;
			Product product=new Product(new Random().nextInt(100),"�绰"+r.nextInt(100));
			storage.push(product);
		}
	}
}

class Product
{
	private int id;//��Ʒid
	private String name;//��Ʒ����
	public Product(int id,String name)
	{
		this.id=id;
		this.name=name;
	}
	public String toString()
	{
		return "(��ƷID:"+id+"���ƣ�"+name+")";
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
}


