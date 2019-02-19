package ThreadDemo;

import java.util.Random;

public class ProductTest {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		
		Storage storage=new Storage();
		Thread consumer1=new Thread(new Consumer(storage));
		consumer1.setName("消费者1");
		Thread consumer2=new Thread(new Consumer(storage));
		consumer2.setName("消费者2");
		Thread producer1=new Thread(new Producer(storage));
		producer1.setName("生产者1");
		Thread producer2=new Thread(new Producer(storage));
		producer2.setName("生产者2");
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
	//仓库大小为10
	//生产者向仓库放入产品
	public synchronized void push(Product product)
	{
		while(top==products.length)
		{
			try
			{
				System.out.println("producer wait");
				wait();//等待
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		
		products[top++]=product;
		System.out.println(Thread.currentThread().getName()+"生产了"+product);
		System.out.println("producer notifyAll");
		notifyAll();//唤醒其它线程
	}
	
	
	//消费者从仓库取出产品
	
	public synchronized Product pop()
	{
		//需要仓库中包含产品
		
		while(top==0)
		{
			try
			{
				System.out.println("consumer wait");
				wait();//仓库空 等待
			}catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		--top;
		Product p=new Product(products[top].getId(),products[top].getName());
		products[top]=null;
		System.out.println(Thread.currentThread().getName()+"消费了产品"+p);
		System.out.println("consumer notifyAll");
		notifyAll();//唤醒等待线程
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
			Product product=new Product(new Random().nextInt(100),"电话"+r.nextInt(100));
			storage.push(product);
		}
	}
}

class Product
{
	private int id;//产品id
	private String name;//产品名称
	public Product(int id,String name)
	{
		this.id=id;
		this.name=name;
	}
	public String toString()
	{
		return "(产品ID:"+id+"名称："+name+")";
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


