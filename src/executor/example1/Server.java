package executor.example1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
	//线程池
	private ThreadPoolExecutor executor;
	public Server()
	{
		//采用默认方法创建大小可变线程池
		executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		//executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(5);
	}
	public void submitTask(Task task)
	{
		System.out.printf("Server:A new task has arrived \n");
		executor.execute(task);
		
		//打印线程池信息
		System.out.printf("Server Pool Size %d \n", executor.getPoolSize());//线程池大小
		System.out.printf("Server Active Count %d \n", executor.getActiveCount());
		System.out.printf("Server Completed Tasks %d \n", executor.getCompletedTaskCount());
	}
	public void endServer()
	{
		executor.shutdown();
	}

}
