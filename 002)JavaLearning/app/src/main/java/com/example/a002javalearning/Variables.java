package com.example.a002javalearning;

public class Variables {
    public static void main(String[] args) {
        System.out.println("hello world");
        System.out.println(5*2);
        System.out.println("hello java");

        //variables

        int age = 20;
        System.out.println(10*age);
        System.out.println(age/4);

        int x = 5;
        int y = 11;
        System.out.println(y/x);

        //double - float

        double a = 5;
        double b = 11;
        System.out.println(b/a);

        double pi = 3.14;
        int r = 5;
        System.out.println(2*r*pi);

        //string
        String name = "Elyesa";
        String surname = "ŞİMŞEK";
        System.out.println(name + " " + surname);

        //boolean
        boolean isAlive = true;
        isAlive = false;
        System.out.println(isAlive);

        //final
        final int myInteger = 5;
        System.out.println("myInteger: " + myInteger);
        //myInteger = 4;
        System.out.println("myInteger: " + myInteger);
    }
}
