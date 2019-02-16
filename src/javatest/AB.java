package javatest;
public class AB implements B2{
public static void main(String args[]){
    int i;
    AB a1=new  AB();
    i =a1.k;
    System.out.println("i="+i);
    }
}
interface B2{
    int k=10;

}