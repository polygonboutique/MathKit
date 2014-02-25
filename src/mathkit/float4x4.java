/**
 Copyright (c) 2013, Sergej Schefer
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Sergej Schefer.
 4. Neither the name of Sergej Schefer nor the
 names of its contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY SERGEJ SCHEFER ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL SERGEJ SCHEFER BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package mathkit;

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
        m[0][0] = row0.x; m[0][1] = row0.y; m[0][2] = row0.z; m[0][3] = row0.w;
        m[1][0] = row1.x; m[1][1] = row1.y; m[1][2] = row1.z; m[1][3] = row1.w;
        m[2][0] = row2.x; m[2][1] = row2.y; m[2][2] = row2.z; m[2][3] = row2.w;
        m[3][0] = row3.x; m[3][1] = row3.y; m[3][2] = row3.z; m[3][3] = row3.w;
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
        m[0][0] = mat.m[0][0];  m[0][1] = mat.m[0][1];     m[0][2] = mat.m[0][2];     m[0][3] = 0.0f;
        m[1][0] = mat.m[1][0];  m[1][1] = mat.m[1][1];     m[1][2] = mat.m[1][2];     m[1][3] = 0.0f;
        m[2][0] = mat.m[2][0];  m[2][1] = mat.m[2][1];     m[2][2] = mat.m[2][2];     m[2][3] = 0.0f;
        m[3][0] = 0.0f;         m[3][1] = 0.0f;            m[3][2] = 0.0f;            m[3][3] = 1.0f;
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
        this.m[0][0] = mat[0];      this.m[0][1] = mat[1];      this.m[0][2] = mat[2];      this.m[0][3] = mat[3];
        this.m[1][0] = mat[4];      this.m[1][1] = mat[5];      this.m[1][2] = mat[6];      this.m[1][3] = mat[7];
        this.m[2][0] = mat[8];      this.m[2][1] = mat[9];      this.m[2][2] = mat[10];     this.m[2][3] = mat[11];
        this.m[3][0] = mat[12];     this.m[3][1] = mat[13];     this.m[3][2] = mat[14];     this.m[3][3] = mat[15];
    }

    /**
     * gets the x values as a 4d vector
     * @return x values as a 4d vector
     */
    public float4 getColumn0() {
        return new float4(m[0][0], m[1][0], m[2][0], m[3][0]);
    }

    /**
     * gets the y values as a 4d vector
     * @return y values as a 4d vector
     */
    public float4 getColumn1() {
        return new float4(m[0][1], m[1][1], m[2][1], m[3][1]);
    }

    /**
     * gets the z values as a 4d vector
     * @return z values as a 4d vector
     */
    public float4 getColumn2() {
        return new float4(m[0][2], m[1][2], m[2][2], m[3][2]);
    }

    /**
     * gets the w values as a 4d vector
     * @return w values as a 4d vector
     */
    public float4 getColumn3() {
        return new float4(m[0][3], m[1][3], m[2][3], m[3][3]);
    }

    /**
     * gets the first row of the matrix
     * @return first row of the matrix
     */
    public float4 getRow0() {
        return new float4(m[0][0], m[0][1], m[0][2], m[0][3]);
    }

    /**
     * gets the second row of the matrix
     * @return second row of the matrix
     */
    public float4 getRow1() {
        return new float4(m[1][0], m[1][1], m[1][2], m[1][3]);
    }

    /**
     * gets the third row of the matrix
     * @return third row of the matrix
     */
    public float4 getRow2() {
        return new float4(m[2][0], m[2][1], m[2][2], m[2][3]);
    }

    /**
     * gets the fourth row of the matrix
     * @return fourth row of the matrix
     */
    public float4 getRow3() {
        return new float4(m[3][0], m[3][1], m[3][2], m[3][3]);
    }

    /**
     * resets this matrix to the identity matrix
     */
    public void loadIdentity() {
        m[0][0] = 1.0f; m[0][1] = 0.0f; m[0][2] = 0.0f; m[0][3] = 0.0f;
        m[1][0] = 0.0f; m[1][1] = 1.0f; m[1][2] = 0.0f; m[1][3] = 0.0f;
        m[2][0] = 0.0f; m[2][1] = 0.0f; m[2][2] = 1.0f; m[2][3] = 0.0f;
        m[3][0] = 0.0f; m[3][1] = 0.0f; m[3][2] = 0.0f; m[3][3] = 1.0f;
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
     * @param x
     * @param y
     * @param z
     * @return translation matrix
     */
    public float4x4 initTranslationRow(float x, float y, float z) {
        float4x4 translationMatrix = new float4x4();

        translationMatrix.m[3][0] = x;
        translationMatrix.m[3][1] = y;
        translationMatrix.m[3][2] = z;

        return translationMatrix;
    }

    /**
     * initializes a column translation matrix
     * @param x
     * @param y
     * @param z
     * @return translation matrix
     */
    public float4x4 initTranslationColumn(float x, float y, float z) {
        float4x4 translationMatrix = new float4x4();

        translationMatrix.m[0][3] = x;
        translationMatrix.m[1][3] = y;
        translationMatrix.m[2][3] = z;

        return translationMatrix;
    }

    /**
     * builds a rotation matrix, that rotates a vertex around the x axis
     * @param angle
     * @return rotation matrix for the x axis
     */
    public float4x4 initRotationX(float angle) {
        float RAD = (float) Constants.toRadians(angle);
        float cos = (float) Math.cos(RAD);
        float sin = (float) Math.sin(RAD);

        float4x4 rotationMatrix = new float4x4();

        rotationMatrix.m[1][1] = cos;    rotationMatrix.m[1][2] = sin;
        rotationMatrix.m[2][1] = -sin;   rotationMatrix.m[2][2] = cos;

        return rotationMatrix;
    }

    /**
     * builds a rotation matrix, that rotates a vertex around the y axis
     * @param angle
     * @return rotation matrix for the y axis
     */
    public float4x4 initRotationY(float angle) {
        float RAD = (float) Constants.toRadians(angle);
        float cos = (float) Math.cos(RAD);
        float sin = (float) Math.sin(RAD);

        float4x4 rotationMatrix = new float4x4();

        rotationMatrix.m[0][0] = cos; rotationMatrix.m[0][2] = -sin;
        rotationMatrix.m[2][0] = sin; rotationMatrix.m[2][2] = cos;

        return rotationMatrix;
    }

    /**
     * builds a rotation matrix, that rotates a vertex around the z axis
     * @param angle
     * @return rotation matrix for the z axis
     */
    public float4x4 initRotateZ(float angle) {
        float RAD = (float) Constants.toRadians(angle);
        float cos = (float) Math.cos(RAD);
        float sin = (float) Math.sin(RAD);

        float4x4 rotationMatrix = new float4x4();

        rotationMatrix.m[0][0] = cos;   rotationMatrix.m[0][1] = sin;
        rotationMatrix.m[1][0] = -sin;  rotationMatrix.m[1][1] = cos;

        return rotationMatrix;
    }

    /**
     * initializes a uniform scaling matrix
     * @param factor
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
     * @param factor
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
     * @param x
     * @param y
     * @param z
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
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param nearPlane
     * @param farPlane
     */
    public void setOrthographic(float left, float right, float bottom, float top, float nearPlane, float farPlane) {
        m[0][0] = (float) 2.0 / (right - left);
        m[1][1] = (float) 2.0 / (top - bottom);
        m[2][2] = (float) -2.0 / (farPlane - nearPlane);

        m[0][3] = -(right + left) / (right - left);
        m[1][3] = -(top + bottom) / (top - bottom);
        m[2][3] = -(farPlane + nearPlane) / (farPlane - nearPlane);

        m[3][3] = 1.0f;
    }

    /**
     * intis a a orthographic projection matrix
     * @param left
     * @param right
     * @param bottom
     * @param top
     * @param nearPlane
     * @param farPlane
     */
    public float4x4 initOrthographic(float left, float right, float bottom, float top, float nearPlane, float farPlane) {
        float4x4 orthoMatrix = new float4x4();
        orthoMatrix.setOrthographic(left, right, bottom, top, nearPlane, farPlane);
        return orthoMatrix;
    }

    /**
     * sets up a perspective projection matrix
     * @param fieldOfView
     * @param aspectRatio
     * @param nearPlane
     * @param farPlane
     */
    public void setPerspective(float fieldOfView, float aspectRatio, float nearPlane, float farPlane) {
        float range = (float) Math.tan(Constants.toRadians(fieldOfView) / 2.0f) * nearPlane;
        float left = -range * aspectRatio;
        float right = range * aspectRatio;
        float bottom = -range;
        float top = range;

        m[0][0] = ((2.0f * nearPlane) / (right - left));
        m[0][2] = ((right+left)/(right-left));
        m[1][1] = ((2.0f * nearPlane) / (top - bottom));
        m[1][2] = ((top+bottom)/(top-bottom));
        m[2][2] = (-(farPlane + nearPlane) / (farPlane - nearPlane));
        m[2][3] = -(2.0f * farPlane * nearPlane) / (farPlane - nearPlane);
        m[3][2] = -1.0f;
    }

    /**
     * init a perspective projection matrix
     * @param fieldOfView
     * @param aspectRatio
     * @param nearPlane
     * @param farPlane
     */
    public float4x4 initPerspective(float fieldOfView, float aspectRatio, float nearPlane, float farPlane) {
        float4x4 perspectiveMatrix = new float4x4();
        perspectiveMatrix.setPerspective(fieldOfView, aspectRatio, nearPlane, farPlane);
        return perspectiveMatrix;
    }

    /**
     * sets up a perspective matrix using a different method
     * @param fieldOfView
     * @param aspectRatio
     * @param nearPlane
     * @param farPlane
     */
    public void setPerspective2(float fieldOfView, float aspectRatio, float nearPlane, float farPlane) {
        float f = (float) (1.0 / Math.tan(Constants.toRadians(fieldOfView / 2)));

        m[0][0] = (float) f / aspectRatio;
        m[1][1] = f;
        m[2][2] = (float) (farPlane + nearPlane) / (nearPlane - farPlane);
        m[2][3] = -1f;
        m[3][2] = (2 * farPlane * nearPlane) / (nearPlane - farPlane);
        m[3][3] = 1f;
    }

    /* multiplications */

    /**
     * scales all matrix values by a given scalar
     * @param scalar
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
     * the vector gets divided by the homogenous coordinate
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
     * the homogenous coordinate gets ignored!
     * @param v
     * @return (mat * vector)
     */
    public float3 multiplyNoHomogenous(float3 v){
        return new float3(v.dot(getRow0().xyz()), v.dot(getRow1().xyz()), v.dot(getRow2().xyz()));
    }

    /* inverse */

    /**
     *  invert the translation and rotation
     */
    public float4x4 inverseTranslationRotation(){

        float4x4 result = new float4x4();

        /* transpose the upper 3x3 */
        result.m[0][0] = m[0][0]; result.m[0][1] = m[1][0]; result.m[0][2] = m[2][0];
        result.m[1][0] = m[0][1]; result.m[1][1] = m[1][1]; result.m[1][2] = m[2][1];
        result.m[2][0] = m[0][2]; result.m[2][1] = m[1][2]; result.m[2][2] = m[2][2];

	    /* dot the t vector and the transposed rotation matrix */
        result.m[0][3] = -(getRow0().xyz().dot(getColumn3().xyz()));
        result.m[1][3] = -(getRow1().xyz().dot(getColumn3().xyz()));
        result.m[2][3] = -(getRow2().xyz().dot(getColumn3().xyz()));

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
        return m[0][0] + " | " + m[0][1] + " | " + m[0][2] + " | " + m[0][3] + "\n" + m[1][0] + " | " + m[1][1] + " | " + m[1][2] + " | " + m[1][3] + "\n" + m[2][0] + " | " + m[2][1] + " | " + m[2][2] + " | " + m[2][3] + "\n" + m[3][0] + " | " + m[3][1] + " | " + m[3][2] + " | " + m[3][3];
    }
}
