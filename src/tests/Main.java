package tests;

import mathkit.float3;
import mathkit.float4x4;

public class Main {

    public static void main(String[] args) {
        float4x4 M = new float4x4();

        M = M.initTranslationColumn(8,4,23);

        float4x4 S = new float4x4().initScale(2,3,4);
        M = M.multiply(S);

        float3 v = new float3(1, 2, 4);
        System.out.println(M);

        System.out.println(M.multiply(v));
    }
}
