package executor.example1;

public class Main {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		//����һ��ִ�з�����(�̳߳�)
	    Server server=new Server();
	    //����100������ �ƽ���ִ�������ȴ�ִ�����
	    for(int i=0;i<100;i++)
	    {
	    	Task task=new Task("Task "+i);
	    	Thread.sleep(10);//ÿ��10�봴��
	    	server.submitTask(task);
	    }
	    server.endServer();//�ر�ִ����

	}

}
