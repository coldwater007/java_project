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
		
		String str0=new String("��������");
		String str1=new StringBuilder("�����").append("���").toString();
		System.out.println(str0==str1);
		System.out.println(str1.intern()==str1);
	
		
	}

}
