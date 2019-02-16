package executor.example1;

public class Task implements Runnable{
	private String name;
	public Task(String name)
	{
		this.name=name;
	}
	@Override
	public void run()
	{
		try
		{
			long duration=(long)(Math.random()*1000);
			System.out.printf("%s task %s Doing a task during %d seconds \n", Thread.currentThread().getName(),name,duration  );
			Thread.sleep(duration);
			System.out.printf(" task %s finished \n", name  );
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	

}
