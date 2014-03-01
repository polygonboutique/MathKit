package tests;

import mathkit.*;

public class Main {

    public static void main(String[] args) {
        float4x4 m = new float4x4();
        m.setColumn0(new float4(1, 1, 1, 1));
        m.setColumn1(new float4(2, 2, 2, 2));
        m.setColumn2(new float4(3, 3, 3, 3));
        m.setColumn3(new float4(4, 4, 4, 4));

        System.out.println(m);
    }
}
