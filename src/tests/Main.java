package tests;

import mathkit.float3x3;
import mathkit.float4x4;

public class Main {

    public static void main(String[] args) {
        float4x4 M = new float4x4();
        float4x4 S = new float4x4();

        M = M.multiply(2.0f);
        S = S.multiply(15.0f);

        float4x4 R = M.multiply(S);

        R = R.inverseTranslationRotation();
        System.out.println(R);
    }
}
