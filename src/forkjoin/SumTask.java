package forkjoin;

import java.util.concurrent.RecursiveTask;

//继承 Recurisetask 递归任务父类
public class SumTask  extends RecursiveTask<Long>{
	
	private int start;
	private int end;
	public SumTask(int start,int end)
	{
		this.start=start;
		this.end=end;
	}
	//static final变量永远不变 编译器就确定
	public static final int threadhold=5;//最小任务区间长度
	@Override
	protected Long compute()
	{
		Long sum=0L;
		//任务小于给定值 计算
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
			//递归计算
			invokeAll(subTask1,subTask2);
			Long sum1=subTask1.join();
			Long sum2=subTask2.join();
			sum=sum1+sum2;
			
		}
		return sum;
	}

}
