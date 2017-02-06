/**
 Copyright (c) 2013 - 2017, Sergej Schefer
 All rights reserved.

 Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package tests;

import mathkit.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("\nfloat 3x3 (set rows) #m3");
        float3x3 m3 = new float3x3();
        m3.setRow0(new float3(1, 2, 3));
        m3.setRow1(new float3(4, 5, 6));
        m3.setRow2(new float3(7, 8, 9));
        System.out.println(m3);

        System.out.println("\nfloat 3x3 (identity)");
        float3x3 m3Identity = new float3x3();
        System.out.println(m3Identity);

        System.out.println("\nfloat 3x3 (matrix mul identity)");
        System.out.println( m3Identity.multiply(m3) );

        System.out.println("\nfloat 3x3 (set columns)");
        float3x3 m3Scale = new float3x3();
        m3Scale.setColumn0(new float3(2, 0, 0));
        m3Scale.setColumn1(new float3(0, 2, 0));
        m3Scale.setColumn2(new float3(0, 0, 2));
        System.out.println(m3Scale);

        System.out.println("\nfloat 3x3 (matrix mul I*2)");
        System.out.println(m3.multiply(m3Scale));

        System.out.println("\nfloat 3x3 (transpose) #m3");
        System.out.println(m3.transpose());

        System.out.println("\nfloat4x4 (set columns) #m");
        float4x4 m = new float4x4();
        m.setColumn0(new float4(1, 1, 1, 1));
        m.setColumn1(new float4(2, 2, 2, 2));
        m.setColumn2(new float4(3, 3, 3, 3));
        m.setColumn3(new float4(4, 4, 4, 4));
        System.out.println(m);

        System.out.println("\nfloat4x4 (set columns) #m * I");
        System.out.println(m.multiply(new float4x4()));

        float4x4 m4Scale2 = new float4x4();
        m4Scale2 = m4Scale2.initScale(2);
        System.out.println("\nm * scalar matrix");
        System.out.println(m.multiply(m4Scale2));

        // Perspective matrices
        float4x4 orthographicProjection = new float4x4().initOrthographic(-1, 1, -1, 1, -1, 1);
        System.out.println("\northographic projection (-1, 1, -1, 1, -1, 1)");
        System.out.println(orthographicProjection);

        float4x4 perspectiveProjection = new float4x4().initPerspective(50.0f, 1920.0f/1080.0f, 0.1f, 100.0f);
        System.out.println("\nperspective projection (50.0f, 1920.0f/1080.0f, 0.1f, 100.0f)");
        System.out.println(perspectiveProjection);

        System.out.println("\ninvert perspective projection (50.0f, 1920.0f/1080.0f, 0.1f, 100.0f)");
        System.out.println(perspectiveProjection.inverse());

        System.out.println("\ninvert TR perspective projection (50.0f, 1920.0f/1080.0f, 0.1f, 100.0f)");
        System.out.println(perspectiveProjection.inverseTranslationRotation());

        System.out.println("\nOrtho * perspective");
        System.out.println(orthographicProjection.multiply(perspectiveProjection));
    }
}
