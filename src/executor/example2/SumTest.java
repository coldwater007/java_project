package executor.example2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class SumTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//�����̳߳�
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(5);
		List<Future<Integer>> resultList=new ArrayList<>();
		//�������  Future����߳̽��
		//����1-1000���ܺͣ��ֳ�10�������ɶ��߳�ִ��
		for(int i=0;i<10;i++)
		{
			SumTask calculator=new SumTask(i*100+1,(i+1)*100);
			Future<Integer> result=executor.submit(calculator);
			resultList.add(result);
		}
		do
		{
			System.out.printf("Main �Ѿ������ %d ������ \n", executor.getCompletedTaskCount());
			for(int i=0;i<resultList.size();i++)
			{
				Future<Integer> result=resultList.get(i);
				//isDone������ȡ�Ƿ��ý�����ϣ��߳��Ƿ�ִ�з��أ�
				System.out.printf("Main task %d %s \n", i,result.isDone());
			}
			try
			{
				Thread.sleep(50);
			}catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}while(executor.getCompletedTaskCount()<resultList.size());//�������������������߳�ִ����
        //�����߳̽����� ���߳�ͳ�ƽ��
		
		int total=0;
		for(int i=0;i<resultList.size();i++)
		{
			Future<Integer> result=resultList.get(i);
			Integer sum=null;
			try
			{
				sum=result.get();
				total+=sum;
			}catch (InterruptedException e)
			{
				e.printStackTrace();
			}catch (ExecutionException e)
			{
				e.printStackTrace();
			}
					
		}
		
		
		System.out.printf("�����: %d \n", total);
	}

}
