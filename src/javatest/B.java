package javatest;
class A {
    public A() 
    {
        System.out.println("class A");
    }
    { System.out.println("I'm A class"); }  //��ͨ�����  ���󴴽�ʱִ��  �ڹ��캯��֮ǰ
    static { System.out.println("class A static"); }  //��̬�����  ֻ���������ʱִ��һ��
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
       //�ȼ��� A B��  Ҳ��������ִ�и��� ����ľ�̬�����
       //��ɸ���A�ĳ�ʼ��  ����ִ�и�����ͨ�����  ���캯��
       //����������B�ĳ�ʼ��
    }
}
