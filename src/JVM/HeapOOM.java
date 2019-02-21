package JVM;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
	/*
	 * 
	 * VM Args : -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
	 * 

	 * 
	 */
	
	static class OOMObject
	{
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		List<OOMObject> list=new ArrayList<OOMObject>();
		while(true)
		{
			//不断在堆中创建对象
			list.add(new OOMObject());
		}
		
		
		

	}

}
