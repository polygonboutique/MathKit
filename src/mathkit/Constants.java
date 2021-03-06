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

package mathkit;

/* math constants */
public class Constants {
    public static final double PI = Math.PI;
    public static final double TWO_PI = 2.0f * PI;
    public static final double HALF_PI = 0.5f * PI;
    public static final double ONE_FOURTH_PI = 0.25f * PI;
    public static final double ONE_OVER_PI = 1.0f / PI;
    public static final double ONE_OVER_TWOPI = 1.0f / TWO_PI;
    public static final double DEGREES_TO_RADIANS = PI / 180.0f;
    public static final double RADIANS_TO_DEGREE = 180.0f / PI;
    public static final double ONE_OVER_180_PI = ONE_OVER_PI * 180;

    public static double toRadians(double degrees){ return degrees * DEGREES_TO_RADIANS; }
    public static double toDegrees(double radians){ return radians * RADIANS_TO_DEGREE; }
}
