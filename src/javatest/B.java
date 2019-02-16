package javatest;
class A {
    public A() 
    {
        System.out.println("class A");
    }
    { System.out.println("I'm A class"); }  //普通构造快  对象创建时执行  在构造函数之前
    static { System.out.println("class A static"); }  //静态代码块  只会在类加载时执行一次
}
public class B extends A 
{
    public B() 
    {
        System.out.println("class B");
    }
    { System.out.println("I'm B class"); }
    static { System.out.println("class B static"); }
     
    public static void main(String[] args) 
    {
       new B();
       //先加载 A B类  也就是依次执行父类 子类的静态代码块
       //完成父类A的初始化  依次执行父类普通构造块  构造函数
       //最后完成子类B的初始化
    }
}
