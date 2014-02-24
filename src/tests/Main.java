package tests;

import mathkit.Constants;
import mathkit.float2;
import mathkit.float3;
import mathkit.float4x4;

public class Main {

    public static void main(String[] args) {
        float2 u = new float2(0, 1);
        float2 v = new float2(1, 0);
        System.out.println(u.rotate(90));
    }
}
