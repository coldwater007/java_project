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
		
		
		//创建线程池
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(5);
		List<Future<Integer>> resultList=new ArrayList<>();
		//结果集合  Future存放线程结果
		//计算1-1000的总和，分成10个任务交由多线程执行
		for(int i=0;i<10;i++)
		{
			SumTask calculator=new SumTask(i*100+1,(i+1)*100);
			Future<Integer> result=executor.submit(calculator);
			resultList.add(result);
		}
		do
		{
			System.out.printf("Main 已经完成了 %d 个任务 \n", executor.getCompletedTaskCount());
			for(int i=0;i<resultList.size();i++)
			{
				Future<Integer> result=resultList.get(i);
				//isDone方法获取是否获得结果集合（线程是否执行返回）
				System.out.printf("Main task %d %s \n", i,result.isDone());
			}
			try
			{
				Thread.sleep(50);
			}catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}while(executor.getCompletedTaskCount()<resultList.size());//跳出条件是所有任务线程执行完
        //所有线程结束后 主线程统计结果
		
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
		
		
		System.out.printf("结果是: %d \n", total);
	}

}
