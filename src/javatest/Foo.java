package javatest;
class Foo {
    final int i=0;
    int j;
    public void doSomething() {
        System.out.println(++j + i);
        System.out.println(i);
    }
}