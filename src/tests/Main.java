package tests;

import mathkit.float2;

public class Main {

    public static void main(String[] args) {
        float2 u = new float2(10, 10);
        float2 v = new float2(30, 20);

        System.out.println(u.angleBetween2(v));
    }
}
