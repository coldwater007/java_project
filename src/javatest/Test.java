package javatest;
public class Test {

    public static void main(String[] args) {
        System.out.println(Test2.a);
    }

}
class Test2{
    public static final String a="JD";  //��������ȷ����

    static {
        System.out.print("OK");  //���಻�ü���
    }

}