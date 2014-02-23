package tests;

import mathkit.float3;
import mathkit.float3x3;
import mathkit.float4;
import mathkit.float4x4;

public class Main {

    public static void main(String[] args) {
        float4x4 M = new float4x4();

        M = M.initTranslationColumn(1,1,1);

        float3 v = new float3(1, 2, 4);

        System.out.println(M.multiply(v));
    }
}
