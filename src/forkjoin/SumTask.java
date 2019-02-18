package forkjoin;

import java.util.concurrent.RecursiveTask;

//�̳� Recurisetask �ݹ�������
public class SumTask  extends RecursiveTask<Long>{
	
	private int start;
	private int end;
	public SumTask(int start,int end)
	{
		this.start=start;
		this.end=end;
	}
	//static final������Զ���� ��������ȷ��
	public static final int threadhold=5;//��С�������䳤��
	@Override
	protected Long compute()
	{
		Long sum=0L;
		//����С�ڸ���ֵ ����
		boolean canCompute=(end-start)<=threadhold;
		if(canCompute)
		{
			for(int i=start;i<=end;i++)
				sum+=i;
		}
		else
		{
			int middle=(start+end)/2;
			SumTask subTask1=new SumTask(start,middle);
			SumTask subTask2=new SumTask(middle+1,end);
			//�ݹ����
			invokeAll(subTask1,subTask2);
			Long sum1=subTask1.join();
			Long sum2=subTask2.join();
			sum=sum1+sum2;
			
		}
		return sum;
	}

}
