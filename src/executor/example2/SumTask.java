package executor.example2;

import java.util.Random;
import java.util.concurrent.Callable;

public class SumTask implements Callable<Integer>{
	
	private int start_num;
	private int end_num;
	public SumTask(int start_num,int end_num)
	{
		this.start_num=start_num;
		this.end_num=end_num;
	}
	@Override
	public Integer call() throws Exception
	{
		int sum=0;
		for(int i=start_num;i<=end_num;i++)
			sum+=i;
		
		
		Thread.sleep(new Random().nextInt(1000));
		System.out.printf("%s ¼ÆËã½á¹û %d \n",Thread.currentThread().getName(),sum);
		return sum;
	}

}
