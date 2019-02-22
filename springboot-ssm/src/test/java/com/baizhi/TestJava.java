package com.baizhi;

public class TestJava {
    String str = new String("good");
    char[] ch = {'a', 'b', 'c' };

    public static void main(String[] args) {
        TestJava ex = new TestJava();
        ex.change(ex.str,ex.ch);
        System.out.println(ex.str);
        System.out.println( ex.ch);
    }

    public void change(String str, char ch[]) {
        str = "test ok";
        ch[0] ='g';
    }
}
