package javatest;
public class Str_test {
	
	

		 
	    public static void main(String[] args) {
	        String s1 = "abc";
	        String s2 = "abc";
	        String s3 = new String("abc");
	        String s4 = s3.intern();
	        System.out.println(s1 == s2);
	        System.out.println(s1 == s3);
	        System.out.println(s1 == s4);
	    }
	
}
