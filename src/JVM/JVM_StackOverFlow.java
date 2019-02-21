package JVM;

public class JVM_StackOverFlow {
	
	/*
	 * 
	 * VM Args: -Xss 2m
	 * 
	 * 
	 */
	public void dontStop()
	{
		while(true)
		{
			
		}
	}
	public void stackleakByThread()
	{
		while(true)
		{
			Thread thread=new Thread(
					
					new Runnable()
					{
						public void run()
						{
							dontStop();
						}
					}
					
					);
		}
	}

	public static void main(String[] args) throws Throwable{
		// TODO Auto-generated method stub
		
		JVM_StackOverFlow oom=new JVM_StackOverFlow();
		oom.stackleakByThread();
		
		

	}

}

