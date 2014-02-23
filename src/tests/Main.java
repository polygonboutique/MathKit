package tests;

import mathkit.float3x3;

public class Main {

    public static void main(String[] args) {
        float3x3 M = new float3x3();
        float3x3 S = new float3x3();
        S = S.multiply(15.0f);

        System.out.println(M.multiply(S));
    }
}
