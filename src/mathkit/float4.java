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

/** http://deadkitteninaframe.com/wordpress/ */
/** 4D Vector */

public class float4 {
    public float x, y, z, w;

    public float4(){
        x = y = z = w = 0;
    }

    public float4(float x, float y, float z, float w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public float4(float4 xyzw){
        this.x = xyzw.x;
        this.y = xyzw.y;
        this.z = xyzw.z;
        this.w = xyzw.w;
    }

    public float4(float2 xy, float2 zw){
        this.x = xy.x;
        this.y = xy.y;
        this.z = zw.x;
        this.w = zw.y;
    }

    public float4(float3 xyz, float w){
        this.x = xyz.x;
        this.y = xyz.y;
        this.z = xyz.z;
        this.w = w;
    }

    public float4(float x, float3 yzw){
        this.x = x;
        this.y = yzw.x;
        this.z = yzw.y;
        this.w = yzw.z;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public float4 clone(){
        return new float4(this);
    }

    /**
     * inverses / negates the vector locally
     */
    public void inverse(){
        x = -x;
        y = -y;
        z = -z;
        w = -w;
    }

    /**
     * returns the inversed vector
     * @return inversed vector
     */
    public float4 inversed(){
        float4 res = new float4(this);
        res.inverse();
        return res;
    }

    /**
     * returns the normalized vector
     * @return normalized vector
     */
    public float4 normalized(){
        float4 res = new float4(this);
        res.normalize();
        return res;
    }

    /**
     * scales the vector locally
     * @param factor
     */
    public void scale(float factor){
        x *= factor;
        y *= factor;
        z *= factor;
        w *= factor;
    }

    /**
     * scales the vector by a factor
     * @param scalar
     * @return scaled vector
     */
    public float4 multiply(float scalar){
        return new float4(x * scalar, y * scalar, z * scalar, w *scalar);
    }

    /**
     * multiplies vector * vector componentwise
     * @param u
     * @return new float4
     */
    public float4 multiply(float4 u){
        return new float4(x * u.x, y * u.y, z * u.z, w * u.w);
    }

    /**
     * divides vector componentwise
     * @param u
     * @return new float4
     */
    public float4 divide(float4 u){
        return new float4( x / u.x, y /u.y, z /u.z, w / u.w);
    }

    /**
     * scales down/divides vector by a scalar
     * @param factor
     * @return scaled down vector
     */
    public float4 divide(float factor){
        float inv = 1.0f / factor;
        return new float4(x * inv, y * inv, z * inv, w * inv);
    }

    /**
     * normalizes the vector locally
     */
    public void normalize(){
        scale(1/length());
    }

    /**
     * returns the length of the vector
     * @return vector length
     */
    public float length(){
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    /**
     * returns the squared length of the vector
     * @return squared length
     */
    public float lengthSquared(){
        return (x * x + y * y + z * z + w * w);
    }

    /**
     * returns the dotproduct between two vectors
     * @param u
     * @return dotproduct
     */
    public float dot(float4 u) {
        return (x * u.x + y * u.y + z * u.z + w * u.w);
    }

    /**
     * parses the vector into a float array
     * @return float array
     */
    public float[] toFloat(){
        return new float[]{x, y, z, w};
    }

    public float3 xyz(){
        return new float3(x, y, z);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[ X: " + x + " Y: " + y + " Z: " + z + " W: " + w + " ]";
    }
}
