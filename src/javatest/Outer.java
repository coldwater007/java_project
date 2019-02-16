package javatest;
public class Outer{
        private int age = 99;
        String name = "Coco";
        public class Inner{
            String name = "Jayden";
            public void show(){
                System.out.println(Outer.this.name);
                System.out.println(name);
                System.out.println(age);
            }
        }
        public Inner getInnerClass(){
            return new Inner();
        }
        public static void main(String[] args){
            Outer o = new Outer();
            Inner in = o.new Inner();
            in.show();
        }
    }