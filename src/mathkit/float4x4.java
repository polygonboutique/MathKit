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

//
/** http://deadkitteninaframe.com/wordpress/ */
/** 4x4 Matrix */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class float4x4 {
    public float m[][] = new float[4][4];

    /**
     * builds identity 4x4 matrix
     */
    public float4x4() {
       loadIdentity();
    }

    /**
     * builds 4x4 matrix from 4 vectors.
     * fills the matrix row wise
     * 1 vector = 1 row
     * @param row0
     * @param row1
     * @param row2
     * @param row3
     */
    public float4x4(float4 row0, float4 row1, float4 row2, float4 row3) {
        m[0][0] = row0.x; m[1][0] = row0.y; m[2][0] = row0.z; m[3][0] = row0.w;
        m[0][1] = row1.x; m[1][1] = row1.y; m[2][1] = row1.z; m[3][1] = row1.w;
        m[0][2] = row2.x; m[1][2] = row2.y; m[2][2] = row2.z; m[3][2] = row2.w;
        m[0][3] = row3.x; m[1][3] = row3.y; m[2][3] = row3.z; m[3][3] = row3.w;
    }

    /**
     * builds 4x4 matrix via copying values from another 4x4 matrix
     * @param mat
     */
    public float4x4(float4x4 mat) {
        this.m = mat.m.clone();
    }

    /**
     * builds a 4x4 matrix from a 3x3 matrix
     * the missing parts will be filled with values from a identity matrix
     * @param mat
     */
    public float4x4(float3x3 mat) {
        m[0][0] = mat.m[0][0];  m[1][0] = mat.m[1][0];  m[2][0] = mat.m[2][0];  m[3][0] = 0.0f;
        m[0][1] = mat.m[0][1];  m[1][1] = mat.m[1][1];  m[2][1] = mat.m[2][1];  m[3][1] = 0.0f;
        m[0][2] = mat.m[0][2];  m[1][2] = mat.m[1][2];  m[2][2] = mat.m[2][2];  m[3][2] = 0.0f;
        m[0][3] = 0.0f;         m[1][3] = 0.0f;         m[2][3] = 0.0f;         m[3][3] = 1.0f;
    }

    /**
     * copies over values from given 4x4 matrix to this
     * @param mat
     */
    public void set(float4x4 mat) {
       this.m = mat.m.clone();
    }

    /**
     * builds a 4x4 matrix from a 16 units long float array
     * @param mat
     */
    public float4x4(float[] mat) {
        this.m[0][0] = mat[0]; this.m[1][0] = mat[4]; this.m[2][0] = mat[8];   this.m[3][0] = mat[12];
        this.m[0][1] = mat[1]; this.m[1][1] = mat[5]; this.m[2][1] = mat[9];   this.m[3][1] = mat[13];
        this.m[0][2] = mat[2]; this.m[1][2] = mat[6]; this.m[2][2] = mat[10];  this.m[3][2] = mat[14];
        this.m[0][3] = mat[3]; this.m[1][3] = mat[7]; this.m[2][3] = mat[11];  this.m[3][3] = mat[15];
    }

    /**
     * gets the x values as a 4d vector
     * @return x values as a 4d vector
     */
    public float4 getColumn0() {
        return new float4(m[0][0], m[0][1], m[0][2], m[0][3]);
    }

    /**
     * gets the y values as a 4d vector
     * @return y values as a 4d vector
     */
    public float4 getColumn1() {
        return new float4(m[1][0], m[1][1], m[1][2], m[1][3]);
    }

    /**
     * gets the z values as a 4d vector
     * @return z values as a 4d vector
     */
    public float4 getColumn2() {
        return new float4(m[2][0], m[2][1], m[2][2], m[2][3]);
    }

    /**
     * gets the w values as a 4d vector
     * @return w values as a 4d vector
     */
    public float4 getColumn3() {
        return new float4(m[3][0], m[3][1], m[3][2], m[3][3]);
    }

    /**
     * gets the first row of the matrix
     * @return first row of the matrix
     */
    public float4 getRow0() {
        return new float4(m[0][0], m[1][0], m[2][0], m[3][0]);
    }

    /**
     * gets the second row of the matrix
     * @return second row of the matrix
     */
    public float4 getRow1() {
        return new float4(m[0][1], m[1][1], m[2][1], m[3][1]);
    }

    /**
     * gets the third row of the matrix
     * @return third row of the matrix
     */
    public float4 getRow2() {
        return new float4(m[0][2], m[1][2], m[2][2], m[3][2]);
    }

    /**
     * gets the fourth row of the matrix
     * @return fourth row of the matrix
     */
    public float4 getRow3() {
        return new float4(m[0][3], m[1][3], m[2][3], m[3][3]);
    }

    /**
     * returns the forward vector
     * @return float3 forward vector
     */
    public float3 getForwardVector() {
        return new float3(m[0][0], m[0][1], m[0][2]);
    }

    /**
     * returns the up vector
     * @return float3 up vector
     */
    public float3 getUpVector() {
        return new float3(m[1][0], m[1][1], m[1][2]);
    }

    /**
     * returns the right vector
     * @return float3 right vector
     */
    public float3 getRightVector() {
        return new float3(m[2][0], m[2][1], m[2][2]);
    }

    /**
     * returns the translation vector
     * @return float3 translation vector
     */
    public float3 getTranslationVector() {
        return new float3(m[3][0], m[3][1], m[3][2]);
    }

    public void setColumn0(float4 v) {
        this.m[0][0] = v.x;
        this.m[0][1] = v.y;
        this.m[0][2] = v.z;
        this.m[0][3] = v.w;
    }

    public void setColumn1(float4 v) {
        this.m[1][0] = v.x;
        this.m[1][1] = v.y;
        this.m[1][2] = v.z;
        this.m[1][3] = v.w;
    }

    public void setColumn2(float4 v) {
        this.m[2][0] = v.x;
        this.m[2][1] = v.y;
        this.m[2][2] = v.z;
        this.m[2][3] = v.w;
    }

    public void setColumn3(float4 v) {
        this.m[3][0] = v.x;
        this.m[3][1] = v.y;
        this.m[3][2] = v.z;
        this.m[3][3] = v.w;
    }

    public void setRow0(float4 v) {
        this.m[0][0] = v.x; this.m[1][0] = v.y; this.m[2][0] = v.z; this.m[3][0] = v.w;
    }

    public void setRow1(float4 v) {
        this.m[0][1] = v.x; this.m[1][1] = v.y; this.m[2][1] = v.z; this.m[3][1] = v.w;
    }

    public void setRow2(float4 v) {
        this.m[0][2] = v.x; this.m[1][2] = v.y; this.m[2][2] = v.z; this.m[3][2] = v.w;
    }

    public void setRow3(float4 v) {
        this.m[0][3] = v.x; this.m[1][3] = v.y; this.m[2][3] = v.z; this.m[3][3] = v.w;
    }

    public void setForwardVector(float3 v) {
        m[0][0] = v.x;
        m[0][1] = v.y;
        m[0][2] = v.z;
    }

    public void setUpVector(float3 v) {
        m[1][0] = v.x;
        m[1][1] = v.y;
        m[1][2] = v.z;
    }

    public void setRightVector3(float3 v) {
        m[2][0] = v.x;
        m[2][1] = v.y;
        m[2][2] = v.z;
    }

    public void setTranslationVector(float3 v) {
        m[3][0] = v.x;
        m[3][1] = v.y;
        m[3][2] = v.z;
    }

    /**
     * resets this matrix to the identity matrix
     */
    public void loadIdentity() {
        m[0][0] = 1.0f; m[1][0] = 0.0f; m[2][0] = 0.0f; m[3][0] = 0.0f;
        m[0][1] = 0.0f; m[1][1] = 1.0f; m[2][1] = 0.0f; m[3][1] = 0.0f;
        m[0][2] = 0.0f; m[1][2] = 0.0f; m[2][2] = 1.0f; m[3][2] = 0.0f;
        m[0][3] = 0.0f; m[1][3] = 0.0f; m[2][3] = 0.0f; m[3][3] = 1.0f;
    }

    /**
     * return the transpose of this matrix
     * @return transposed matrix
     */
    public float4x4 transposed() {
        return new float4x4(getColumn0(), getColumn1(), getColumn2(), getColumn3());
    }

    /**
     * initializes a row translation matrix
     * @param x float
     * @param y float
     * @param z float
     * @return translation matrix
     */
    public float4x4 initTranslation(float x, float y, float z) {
        float4x4 translationMatrix = new float4x4();
        translationMatrix.m[3][0] = x;
        translationMatrix.m[3][1] = y;
        translationMatrix.m[3][2] = z;
        return translationMatrix;
    }

    /**
     * initializes a row translation matrix
     * @param v translation
     * @return translation matrix
     */
    public float4x4 initTranslation(float3 v) {
        float4x4 translationMatrix = new float4x4();
        translationMatrix.m[3][0] = v.x;
        translationMatrix.m[3][1] = v.y;
        translationMatrix.m[3][2] = v.z;
        return translationMatrix;
    }

    /**
     * builds a rotation matrix, that rotates a vertex around the x axis
     * @param angle float
     * @return rotation matrix for the x axis
     */
    public float4x4 initRotationX(float angle) {
        float RAD = (float) Constants.toRadians(angle);
        float cos = (float) Math.cos(RAD);
        float sin = (float) Math.sin(RAD);

        float4x4 rotationMatrix = new float4x4();

        rotationMatrix.m[1][1] = cos; rotationMatrix.m[2][1] = -sin;
        rotationMatrix.m[1][2] = sin; rotationMatrix.m[2][2] = cos;

        return rotationMatrix;
    }

    /**
     * builds a rotation matrix, that rotates a vertex around the y axis
     * @param angle float
     * @return rotation matrix for the y axis
     */
    public float4x4 initRotationY(float angle) {
        float RAD = (float) Constants.toRadians(angle);
        float cos = (float) Math.cos(RAD);
        float sin = (float) Math.sin(RAD);

        float4x4 rotationMatrix = new float4x4();

        rotationMatrix.m[0][0] = cos;   rotationMatrix.m[2][0] = sin;
        rotationMatrix.m[0][2] = -sin;  rotationMatrix.m[2][2] = cos;

        return rotationMatrix;
    }

    /**
     * builds a rotation matrix, that rotates a vertex around the z axis
     * @param angle float
     * @return rotation matrix for the z axis
     */
    public float4x4 initRotateZ(float angle) {
        float RAD = (float) Constants.toRadians(angle);
        float cos = (float) Math.cos(RAD);
        float sin = (float) Math.sin(RAD);

        float4x4 rotationMatrix = new float4x4();

        rotationMatrix.m[0][0] = cos; rotationMatrix.m[1][0] = -sin;
        rotationMatrix.m[0][1] = sin; rotationMatrix.m[1][1] = cos;

        return rotationMatrix;
    }

    /**
     * initializes a uniform scaling matrix
     * @param factor float
     * @return uniform scaling matrix
     */
    public float4x4 initScale(float factor) {
        float4x4 scaleMatrix = new float4x4();

        scaleMatrix.m[0][0] = factor;
        scaleMatrix.m[1][1] = factor;
        scaleMatrix.m[2][2] = factor;

        return scaleMatrix;
    }

    /**
     * initializes a non uniform scaling matrix
     * @param factor float
     * @return non uniform scaling matrix
     */
    public float4x4 initScale(float3 factor) {
        float4x4 scaleMatrix = new float4x4();

        scaleMatrix.m[0][0] = factor.x;
        scaleMatrix.m[1][1] = factor.y;
        scaleMatrix.m[2][2] = factor.z;

        return scaleMatrix;
    }

    /**
     * initializes a non uniform scaling matrix
     * @param x float
     * @param y float
     * @param z float
     * @return non uniform scaling matrix
     */
    public float4x4 initScale(float x, float y, float z) {
        float4x4 scaleMatrix = new float4x4();

        scaleMatrix.m[0][0] = x;
        scaleMatrix.m[1][1] = y;
        scaleMatrix.m[2][2] = z;

        return scaleMatrix;
    }

    /**
     * sets up a orthographic projection matrix
     * @param left float
     * @param right float
     * @param bottom float
     * @param top float
     * @param nearPlane float
     * @param farPlane float
     */
    public void setOrthographic(float left, float right, float bottom, float top, float nearPlane, float farPlane) {
        loadIdentity();

        float deltaX = right - left;
        float deltaY = top - bottom;
        float deltaZ = farPlane - nearPlane;

        m[0][0] = 2.0f / deltaX;		                        m[3][0] = -(right + left) / deltaX;
                                 m[1][1] = 2.0f / deltaY;		m[3][1] = -(top + bottom) / deltaY;
                                 m[2][2] = -2.0f / deltaZ;		m[3][2] = -(farPlane + nearPlane) / deltaZ;
    }

    /**
     * intis a a orthographic projection matrix
     * @param left float
     * @param right float
     * @param bottom float
     * @param top float
     * @param nearPlane float
     * @param farPlane float
     */
    public float4x4 initOrthographic(float left, float right, float bottom, float top, float nearPlane, float farPlane) {
        float4x4 orthoMatrix = new float4x4();
        orthoMatrix.setOrthographic(left, right, bottom, top, nearPlane, farPlane);
        return orthoMatrix;
    }

    /**
     * sets up a perspective projection matrix
     * @param fieldOfView float
     * @param aspectRatio float
     * @param nearPlane float
     * @param farPlane float
     */
    public void setPerspective(float fieldOfView, float aspectRatio, float nearPlane, float farPlane) {
        loadIdentity();
        float tanOver2 = (float) Math.tan(fieldOfView * Math.PI / 360.0);

        m[0][0] = 1 / tanOver2;

                                        m[1][1] = aspectRatio / tanOver2;
                                                                                m[2][2] = (nearPlane + farPlane) / (nearPlane - farPlane);	    m[3][2] = 2.0f * nearPlane * farPlane / (nearPlane - farPlane);
                                                                                m[2][3] = -1.0f;													m[3][3] = 0.0f;
    }

    /**
     * init a perspective projection matrix
     * @param fieldOfView float
     * @param aspectRatio float
     * @param nearPlane float
     * @param farPlane float
     */
    public float4x4 initPerspective(float fieldOfView, float aspectRatio, float nearPlane, float farPlane) {
        float4x4 perspectiveMatrix = new float4x4();
        perspectiveMatrix.setPerspective(fieldOfView, aspectRatio, nearPlane, farPlane);
        return perspectiveMatrix;
    }

    /* multiplications */

    /**
     * scales all matrix values by a given scalar
     * @param scalar float
     * @return scaled matrix
     */
    public float4x4 multiply(float scalar) {
        float4x4 result = new float4x4();

        result.m[0][0] = m[0][0] * scalar;
        result.m[0][1] = m[0][1] * scalar;
        result.m[0][2] = m[0][2] * scalar;
        result.m[0][3] = m[0][3] * scalar;

        result.m[1][0] = m[1][0] * scalar;
        result.m[1][1] = m[1][1] * scalar;
        result.m[1][2] = m[1][2] * scalar;
        result.m[1][3] = m[1][3] * scalar;

        result.m[2][0] = m[2][0] * scalar;
        result.m[2][1] = m[2][1] * scalar;
        result.m[2][2] = m[2][2] * scalar;
        result.m[2][3] = m[2][3] * scalar;

        result.m[3][0] = m[3][0] * scalar;
        result.m[3][1] = m[3][1] * scalar;
        result.m[3][2] = m[3][2] * scalar;
        result.m[3][3] = m[3][3] * scalar;

        return result;
    }

    /**
     * matrix multiplication
     * @param mat
     * @return (this * mat)
     */
    public float4x4 multiply(float4x4 mat){
        float4 row0 = new float4(getRow0().dot(mat.getColumn0()), getRow0().dot(mat.getColumn1()), getRow0().dot(mat.getColumn2()), getRow0().dot(mat.getColumn3()));
        float4 row1 = new float4(getRow1().dot(mat.getColumn0()), getRow1().dot(mat.getColumn1()), getRow1().dot(mat.getColumn2()), getRow1().dot(mat.getColumn3()));
        float4 row2 = new float4(getRow2().dot(mat.getColumn0()), getRow2().dot(mat.getColumn1()), getRow2().dot(mat.getColumn2()), getRow2().dot(mat.getColumn3()));
        float4 row3 = new float4(getRow3().dot(mat.getColumn0()), getRow3().dot(mat.getColumn1()), getRow3().dot(mat.getColumn2()), getRow3().dot(mat.getColumn3()));
        return new float4x4(row0, row1, row2, row3);
    }

    /**
     * multiplies a 4d vector with the matrix
     * @param v
     * @return (mat * vector)
     */
    public float4 multiply(float4 v){
        return new float4(v.dot(getRow0()), v.dot(getRow1()), v.dot(getRow2()), v.dot(getRow3()));
    }

    /**
     * multiplies a 3d vector with the matrix
     * the vector gets divided by the homogeneous coordinate
     * Source: openFrameworks matrix class !
     * @param v
     * @return (mat * vector)
     */
    public float3 multiply(float3 v){
        float4 u = multiply(new float4(v, 1.0f));
        return u.xyz().divide(u.w);
    }

    /**
     * multiplies a 3d vector with the matrix
     * the homogeneous coordinate gets ignored!
     * @param v
     * @return (mat * vector)
     */
    public float3 multiplyNoHomogeneous(float3 v){
        return new float3(v.dot(getRow0().xyz()), v.dot(getRow1().xyz()), v.dot(getRow2().xyz()));
    }

    /* inverse */

    public float getDeterminant() {

        float det;
        float4x4 result = new float4x4();

        result.m[0][0] = m[1][1] * m[2][2] * m[3][3] -
                m[1][1] * m[2][3] * m[3][2] -
                m[2][1] * m[1][2] * m[3][3] +
                m[2][1] * m[1][3] * m[3][2] +
                m[3][1] * m[1][2] * m[2][3] -
                m[3][1] * m[1][3] * m[2][2];

        result.m[1][0] = -m[1][0] * m[2][2] * m[3][3] +
                m[1][0] * m[2][3] * m[3][2] +
                m[2][0] * m[1][2] * m[3][3] -
                m[2][0] * m[1][3] * m[3][2] -
                m[3][0] * m[1][2] * m[2][3] +
                m[3][0] * m[1][3] * m[2][2];

        result.m[2][0] = m[1][0] * m[2][1] * m[3][3] -
                m[1][0] * m[2][3] * m[3][1] -
                m[2][0] * m[1][1] * m[3][3] +
                m[2][0] * m[1][3] * m[3][1] +
                m[3][0] * m[1][1] * m[2][3] -
                m[3][0] * m[1][3] * m[2][1];

        result.m[3][0] = -m[1][0] * m[2][1] * m[3][2] +
                m[1][0] * m[2][2] * m[3][1] +
                m[2][0] * m[1][1] * m[3][2] -
                m[2][0] * m[1][2] * m[3][1] -
                m[3][0] * m[1][1] * m[2][2] +
                m[3][0] * m[1][2] * m[2][1];

        result.m[0][1] = -m[0][1] * m[2][2] * m[3][3] +
                m[0][1] * m[2][3] * m[3][2] +
                m[2][1] * m[0][2] * m[3][3] -
                m[2][1] * m[0][3] * m[3][2] -
                m[3][1] * m[0][2] * m[2][3] +
                m[3][1] * m[0][3] * m[2][2];

        result.m[1][1] = m[0][0] * m[2][2] * m[3][3] -
                m[0][0] * m[2][3] * m[3][2] -
                m[2][0] * m[0][2] * m[3][3] +
                m[2][0] * m[0][3] * m[3][2] +
                m[3][0] * m[0][2] * m[2][3] -
                m[3][0] * m[0][3] * m[2][2];

        result.m[2][1] = -m[0][0] * m[2][1] * m[3][3] +
                m[0][0] * m[2][3] * m[3][1] +
                m[2][0] * m[0][1] * m[3][3] -
                m[2][0] * m[0][3] * m[3][1] -
                m[3][0] * m[0][1] * m[2][3] +
                m[3][0] * m[0][3] * m[2][1];

        result.m[3][1] = m[0][0] * m[2][1] * m[3][2] -
                m[0][0] * m[2][2] * m[3][1] -
                m[2][0] * m[0][1] * m[3][2] +
                m[2][0] * m[0][2] * m[3][1] +
                m[3][0] * m[0][1] * m[2][2] -
                m[3][0] * m[0][2] * m[2][1];

        result.m[0][2] = m[0][1] * m[1][2] * m[3][3] -
                m[0][1] * m[1][3] * m[3][2] -
                m[1][1] * m[0][2] * m[3][3] +
                m[1][1] * m[0][3] * m[3][2] +
                m[3][1] * m[0][2] * m[1][3] -
                m[3][1] * m[0][3] * m[1][2];

        result.m[1][2] = -m[0][0] * m[1][2] * m[3][3] +
                m[0][0] * m[1][3] * m[3][2] +
                m[1][0] * m[0][2] * m[3][3] -
                m[1][0] * m[0][3] * m[3][2] -
                m[3][0] * m[0][2] * m[1][3] +
                m[3][0] * m[0][3] * m[1][2];

        result.m[2][2] = m[0][0] * m[1][1] * m[3][3] -
                m[0][0] * m[1][3] * m[3][1] -
                m[1][0] * m[0][1] * m[3][3] +
                m[1][0] * m[0][3] * m[3][1] +
                m[3][0] * m[0][1] * m[1][3] -
                m[3][0] * m[0][3] * m[1][1];

        result.m[3][2] = -m[0][0] * m[1][1] * m[3][2] +
                m[0][0] * m[1][2] * m[3][1] +
                m[1][0] * m[0][1] * m[3][2] -
                m[1][0] * m[0][2] * m[3][1] -
                m[3][0] * m[0][1] * m[1][2] +
                m[3][0] * m[0][2] * m[1][1];

        result.m[0][3] = -m[0][1] * m[1][2] * m[2][3] +
                m[0][1] * m[1][3] * m[2][2] +
                m[1][1] * m[0][2] * m[2][3] -
                m[1][1] * m[0][3] * m[2][2] -
                m[2][1] * m[0][2] * m[1][3] +
                m[2][1] * m[0][3] * m[1][2];

        result.m[1][3] = m[0][0] * m[1][2] * m[2][3] -
                m[0][0] * m[1][3] * m[2][2] -
                m[1][0] * m[0][2] * m[2][3] +
                m[1][0] * m[0][3] * m[2][2] +
                m[2][0] * m[0][2] * m[1][3] -
                m[2][0] * m[0][3] * m[1][2];

        result.m[2][3] = -m[0][0] * m[1][1] * m[2][3] +
                m[0][0] * m[1][3] * m[2][1] +
                m[1][0] * m[0][1] * m[2][3] -
                m[1][0] * m[0][3] * m[2][1] -
                m[2][0] * m[0][1] * m[1][3] +
                m[2][0] * m[0][3] * m[1][1];

        result.m[3][3] = m[0][0] * m[1][1] * m[2][2] -
                m[0][0] * m[1][2] * m[2][1] -
                m[1][0] * m[0][1] * m[2][2] +
                m[1][0] * m[0][2] * m[2][1] +
                m[2][0] * m[0][1] * m[1][2] -
                m[2][0] * m[0][2] * m[1][1];

        det = m[0][0] * result.m[0][0] + m[0][1] * result.m[1][0] + m[0][2] * result.m[2][0] + m[0][3] * result.m[3][0];

        return det;
    }

    /**
     *  invert the translation and rotation
     */
    public float4x4 inverseTranslationRotation(){

        float4x4 result = new float4x4();

        /* transpose the upper 3x3 */
        result.m[0][0] = m[0][0];   result.m[1][0] = m[0][1]; result.m[2][0] = m[0][2];
        result.m[0][1] = m[1][0];   result.m[1][1] = m[1][1]; result.m[2][1] = m[1][2];
        result.m[0][2] = m[2][0];   result.m[1][2] = m[2][1]; result.m[2][2] = m[2][2];

	    /* dot the t vector and the transposed rotation matrix */
        result.m[3][0] = -(result.getRow0().xyz().dot(getColumn3().xyz()));
        result.m[3][1] = -(result.getRow1().xyz().dot(getColumn3().xyz()));
        result.m[3][2] = -(result.getRow2().xyz().dot(getColumn3().xyz()));

       return result;
    }

    /**
     * Source: http://stackoverflow.com/questions/1148309/inverting-a-4x4-matrix
     * @return float4x4 inverted matrix
     */
    public float4x4 inverse()
    {
        float det;
        float4x4 result = new float4x4();

        //				0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15
        // float[16] = {00, 01, 02, 03, 10, 11, 12, 13, 20, 21, 22, 23, 30, 31, 32, 33};

        result.m[0][0] = m[1][1] * m[2][2] * m[3][3] -
                         m[1][1] * m[2][3] * m[3][2] -
                         m[2][1] * m[1][2] * m[3][3] +
                         m[2][1] * m[1][3] * m[3][2] +
                         m[3][1] * m[1][2] * m[2][3] -
                         m[3][1] * m[1][3] * m[2][2];

        result.m[1][0] = -m[1][0] * m[2][2] * m[3][3] +
                          m[1][0] * m[2][3] * m[3][2] +
                          m[2][0] * m[1][2] * m[3][3] -
                          m[2][0] * m[1][3] * m[3][2] -
                          m[3][0] * m[1][2] * m[2][3] +
                          m[3][0] * m[1][3] * m[2][2];

        result.m[2][0] = m[1][0] * m[2][1] * m[3][3] -
                         m[1][0] * m[2][3] * m[3][1] -
                         m[2][0] * m[1][1] * m[3][3] +
                         m[2][0] * m[1][3] * m[3][1] +
                         m[3][0] * m[1][1] * m[2][3] -
                         m[3][0] * m[1][3] * m[2][1];

        result.m[3][0] = -m[1][0] * m[2][1] * m[3][2] +
                          m[1][0] * m[2][2] * m[3][1] +
                          m[2][0] * m[1][1] * m[3][2] -
                          m[2][0] * m[1][2] * m[3][1] -
                          m[3][0] * m[1][1] * m[2][2] +
                          m[3][0] * m[1][2] * m[2][1];

        result.m[0][1] = -m[0][1] * m[2][2] * m[3][3] +
                          m[0][1] * m[2][3] * m[3][2] +
                          m[2][1] * m[0][2] * m[3][3] -
                          m[2][1] * m[0][3] * m[3][2] -
                          m[3][1] * m[0][2] * m[2][3] +
                          m[3][1] * m[0][3] * m[2][2];

        result.m[1][1] = m[0][0] * m[2][2] * m[3][3] -
                         m[0][0] * m[2][3] * m[3][2] -
                         m[2][0] * m[0][2] * m[3][3] +
                         m[2][0] * m[0][3] * m[3][2] +
                         m[3][0] * m[0][2] * m[2][3] -
                         m[3][0] * m[0][3] * m[2][2];

        result.m[2][1] = -m[0][0] * m[2][1] * m[3][3] +
                          m[0][0] * m[2][3] * m[3][1] +
                          m[2][0] * m[0][1] * m[3][3] -
                          m[2][0] * m[0][3] * m[3][1] -
                          m[3][0] * m[0][1] * m[2][3] +
                          m[3][0] * m[0][3] * m[2][1];

        result.m[3][1] = m[0][0] * m[2][1] * m[3][2] -
                         m[0][0] * m[2][2] * m[3][1] -
                         m[2][0] * m[0][1] * m[3][2] +
                         m[2][0] * m[0][2] * m[3][1] +
                         m[3][0] * m[0][1] * m[2][2] -
                         m[3][0] * m[0][2] * m[2][1];

        result.m[0][2] = m[0][1] * m[1][2] * m[3][3] -
                         m[0][1] * m[1][3] * m[3][2] -
                         m[1][1] * m[0][2] * m[3][3] +
                         m[1][1] * m[0][3] * m[3][2] +
                         m[3][1] * m[0][2] * m[1][3] -
                         m[3][1] * m[0][3] * m[1][2];

        result.m[1][2] = -m[0][0] * m[1][2] * m[3][3] +
                          m[0][0] * m[1][3] * m[3][2] +
                          m[1][0] * m[0][2] * m[3][3] -
                          m[1][0] * m[0][3] * m[3][2] -
                          m[3][0] * m[0][2] * m[1][3] +
                          m[3][0] * m[0][3] * m[1][2];

        result.m[2][2] = m[0][0] * m[1][1] * m[3][3] -
                         m[0][0] * m[1][3] * m[3][1] -
                         m[1][0] * m[0][1] * m[3][3] +
                         m[1][0] * m[0][3] * m[3][1] +
                         m[3][0] * m[0][1] * m[1][3] -
                         m[3][0] * m[0][3] * m[1][1];

        result.m[3][2] = -m[0][0] * m[1][1] * m[3][2] +
                          m[0][0] * m[1][2] * m[3][1] +
                          m[1][0] * m[0][1] * m[3][2] -
                          m[1][0] * m[0][2] * m[3][1] -
                          m[3][0] * m[0][1] * m[1][2] +
                          m[3][0] * m[0][2] * m[1][1];

        result.m[0][3] = -m[0][1] * m[1][2] * m[2][3] +
                          m[0][1] * m[1][3] * m[2][2] +
                          m[1][1] * m[0][2] * m[2][3] -
                          m[1][1] * m[0][3] * m[2][2] -
                          m[2][1] * m[0][2] * m[1][3] +
                          m[2][1] * m[0][3] * m[1][2];

        result.m[1][3] = m[0][0] * m[1][2] * m[2][3] -
                         m[0][0] * m[1][3] * m[2][2] -
                         m[1][0] * m[0][2] * m[2][3] +
                         m[1][0] * m[0][3] * m[2][2] +
                         m[2][0] * m[0][2] * m[1][3] -
                         m[2][0] * m[0][3] * m[1][2];

        result.m[2][3] = -m[0][0] * m[1][1] * m[2][3] +
                          m[0][0] * m[1][3] * m[2][1] +
                          m[1][0] * m[0][1] * m[2][3] -
                          m[1][0] * m[0][3] * m[2][1] -
                          m[2][0] * m[0][1] * m[1][3] +
                          m[2][0] * m[0][3] * m[1][1];

        result.m[3][3] = m[0][0] * m[1][1] * m[2][2] -
                         m[0][0] * m[1][2] * m[2][1] -
                         m[1][0] * m[0][1] * m[2][2] +
                         m[1][0] * m[0][2] * m[2][1] +
                         m[2][0] * m[0][1] * m[1][2] -
                         m[2][0] * m[0][2] * m[1][1];

        det = m[0][0] * result.m[0][0] + m[0][1] * result.m[1][0] + m[0][2] * result.m[2][0] + m[0][3] * result.m[3][0];

        if (det != 0)
        {
            det = 1.0f / det;

            result.m[0][0] *= det;
            result.m[0][1] *= det;
            result.m[0][2] *= det;
            result.m[0][3] *= det;

            result.m[1][0] *= det;
            result.m[1][1] *= det;
            result.m[1][2] *= det;
            result.m[1][3] *= det;

            result.m[2][0] *= det;
            result.m[2][1] *= det;
            result.m[2][2] *= det;
            result.m[2][3] *= det;

            result.m[3][0] *= det;
            result.m[3][1] *= det;
            result.m[3][2] *= det;
            result.m[3][3] *= det;
        }
        else
        {
            System.out.println("No determinant found \n");
        }

        return result;
    }

    /**
     * parses this matrix into a float array
     * @return float array
     */
    public float[] toFloat() {
        float[] c0 = getColumn0().toFloat();
        float[] c1 = getColumn1().toFloat();
        float[] c2 = getColumn2().toFloat();
        float[] c3 = getColumn3().toFloat();
        return new float[] { c0[0], c0[1], c0[2], c0[3], c1[0], c1[1], c1[2], c1[3], c2[0], c2[1], c2[2], c2[3], c3[0], c3[1], c3[2], c3[3] };
    }

    /**
     * parses this matrix in a float buffer
     * column after column
     * used to transfer the matrix to gpu
     * @return float buffer
     */
    public FloatBuffer toBuffer() {
        FloatBuffer buffer = ByteBuffer.allocateDirect(16 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(toFloat());
        buffer.flip();
        return buffer;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return    m[0][0] + " | " + m[1][0] + " | " + m[2][0] + " | " + m[3][0] + "\n"
                + m[0][1] + " | " + m[1][1] + " | " + m[2][1] + " | " + m[3][1] + "\n"
                + m[0][2] + " | " + m[1][2] + " | " + m[2][2] + " | " + m[3][2] + "\n"
                + m[0][3] + " | " + m[1][3] + " | " + m[2][3] + " | " + m[3][3];
    }
}
