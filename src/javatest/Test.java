package javatest;
public class Test {

    public static void main(String[] args) {
        System.out.println(Test2.a);
    }

}
class Test2{
    public static final String a="JD";  //编译器便确定了

    static {
        System.out.print("OK");  //该类不用加载
    }

}