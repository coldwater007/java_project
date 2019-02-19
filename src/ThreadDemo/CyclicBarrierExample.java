package ThreadDemo;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
	
	
	/*
	 * 
	 * 3个线程分别计算每一行的和，最终计算总和
	 * 
	 * 
	 */
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		final int[][] numbers=new int[3][5];
		final int[] results=new int[3];
		int[] row1=new int[] {1,2,3,4,5};
		int[] row2=new int[] {6,7,8,9,10};
		int[] row3=new int[] {11,12,13,14,15};
		numbers[0]=row1;
		numbers[1]=row2;
		numbers[2]=row3;
		
		CalculateFinalResult finalResultCalculator=new CalculateFinalResult(results);
		
		/*
		 * CyclicBarrier构造器
		 * 2个参数  第一个表示 子线程的数量 第二个表示所有线程松开后 执行动作Action
		 * 
		 * Parameters:parties - the number of threads that must invoke await()
		 * before the barrier is trippedbarrierAction - 
		 * the command to execute when the barrier istripped, 
		 * or null if there is no action
		 * 
		 */
		CyclicBarrier barrier=new CyclicBarrier(3,finalResultCalculator);
		
		for(int i=0;i<3;i++)
		{
			CalculateEachRow rowCalculator=new CalculateEachRow(barrier,numbers,i,results);
			new Thread(rowCalculator).start();
		}
		

	}

}

class CalculateEachRow implements Runnable
{
	final int[][] numbers;
	final int rowNumber;
	final int[] res;
	final CyclicBarrier barrier;
	
	CalculateEachRow(CyclicBarrier barrier,int[][] numbers,int rowNumber,int[] res)
	{
		this.barrier=barrier;
		this.numbers=numbers;
		this.rowNumber=rowNumber;
		this.res=res;
		
	}
	@Override
	public   void run()
	{
		int[] row=numbers[rowNumber];
		int sum=0;
		for(int data:row)
		{
			sum+=data;
			res[rowNumber]=sum;
		}
		
		try
		{
			
			
				System.out.println(Thread.currentThread().getName()+"：计算第"+(rowNumber+1)+"结束 计算结果："+sum);
				barrier.wait();//等待所有线程完成  后松开
			
			
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
}

//子线程全部执行完毕后的动作线程

class CalculateFinalResult implements Runnable
{
	final int[] eachRowRes;
	int finalRes;
	public int getFinalResult()
	{
		return finalRes;
	}
	CalculateFinalResult(int[] eachRowRes)
	{
		this.eachRowRes=eachRowRes;
	}
	
	@Override
	public  void run()
	{
		int sum=0;
		for(int data:eachRowRes)
		{
			sum+=data;
		}
		finalRes=sum;
		System.out.println("最终结果是 "+finalRes);
	}
}
