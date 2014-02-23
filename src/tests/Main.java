package tests;

import mathkit.float3;
import mathkit.float4x4;

public class Main {

    public static void main(String[] args) {
        float4x4 M = new float4x4();

        M = M.initTranslationColumn(8,4,23);

        float4x4 S = new float4x4().initScale(2,3,4);
        M = M.multiply(S);


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                M.m[i][j] = 1 + i + j;
            }
        }


        System.out.println(M.inverseTranslationRotation());
    }
}
