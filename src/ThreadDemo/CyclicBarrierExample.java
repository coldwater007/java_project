package ThreadDemo;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
	
	
	/*
	 * 
	 * 3���̷ֱ߳����ÿһ�еĺͣ����ռ����ܺ�
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
		 * CyclicBarrier������
		 * 2������  ��һ����ʾ ���̵߳����� �ڶ�����ʾ�����߳��ɿ��� ִ�ж���Action
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
			
			
				System.out.println(Thread.currentThread().getName()+"�������"+(rowNumber+1)+"���� ��������"+sum);
				barrier.wait();//�ȴ������߳����  ���ɿ�
			
			
			
		}catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	
}

//���߳�ȫ��ִ����Ϻ�Ķ����߳�

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
		System.out.println("���ս���� "+finalRes);
	}
}
