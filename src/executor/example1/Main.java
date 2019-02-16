package executor.example1;

public class Main {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		//创建一个执行服务器(线程池)
	    Server server=new Server();
	    //创建100个任务 移交给执行器，等待执行完成
	    for(int i=0;i<100;i++)
	    {
	    	Task task=new Task("Task "+i);
	    	Thread.sleep(10);//每隔10秒创建
	    	server.submitTask(task);
	    }
	    server.endServer();//关闭执行器

	}

}
