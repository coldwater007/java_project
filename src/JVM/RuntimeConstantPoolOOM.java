package JVM;

import java.util.ArrayList;
import java.util.List;

public class RuntimeConstantPoolOOM {
	
	/*
	 * VM Args: -XX:PermSize=10M -XX:MaxPermsize=10M
	 * 
	 * 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String str0=new String("计算机软件");
		String str1=new StringBuilder("计算机").append("软件").toString();
		System.out.println(str0==str1);
		System.out.println(str1.intern()==str1);
	
		
	}

}
