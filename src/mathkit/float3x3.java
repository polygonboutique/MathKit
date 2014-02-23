package mathkit;

/** http://deadkitteninaframe.com/wordpress/ */

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/** 3x3 Matrix */

public class float3x3 {
    public float m[][] = new float[3][3];

    public float3x3(){
        setIdentity();
    }

    /**
     * builds a matrix from three 3d vectors
     * the matrix uses the vectors as rows
     * @param row0
     * @param row1
     * @param row2
     */
    public float3x3(float3 row0, float3 row1, float3 row2){
        m[0][0] = row0.x;  m[0][1] = row0.y;  m[0][2] = row0.z;
        m[1][0] = row1.x;  m[1][1] = row1.y;  m[1][2] = row1.z;
        m[2][0] = row2.x;  m[2][1] = row2.y;  m[2][2] = row2.z;
    }

    /**
     * builds a 3x3 matrix from 9 floats
     * @param mat
     */
    public float3x3(float mat[]){
        m[0][0] = mat[0];  m[0][1] = mat[1];  m[0][2] = mat[2];
        m[1][0] = mat[3];  m[1][1] = mat[4];  m[1][2] = mat[5];
        m[2][0] = mat[6];  m[2][1] = mat[7];  m[2][2] = mat[8];
    }

    /**
     * builds a 3x3 matrix using the values which are contained in the
     * parameter 3x3 matrix
     * @param mat
     */
    public float3x3(float3x3 mat){
        this.m = mat.m.clone();
    }

    /**
     * builds a 3x3 matrix from 4x4 matrix.
     * the 4 row and column are cut off
     * @param mat
     */
    public float3x3(float4x4 mat){
        m[0][0] = mat.m[0][0];  m[0][1] = mat.m[0][1];     m[0][2] = mat.m[0][2];
        m[1][0] = mat.m[1][0];  m[1][1] = mat.m[1][1];     m[1][2] = mat.m[1][2];
        m[2][0] = mat.m[2][0];  m[2][1] = mat.m[2][1];     m[2][2] = mat.m[2][2];
    }

    /**
     * changes the values from this matrix,
     * to the values of the parameter matrix
     * @param mat
     */
    public void set(float3x3 mat){
        this.m = mat.m.clone();
    }

    /**
     * resets the matrix to the identity matrix
     */

    public void setIdentity(){
        m[0][0] = 1.0f; m[0][1] = 0.0f; m[0][2] = 0.0f;
        m[1][0] = 0.0f; m[1][1] = 1.0f; m[1][2] = 0.0f;
        m[2][0] = 0.0f; m[2][1] = 0.0f; m[2][2] = 1.0f;
    }

    /**
     * returns the x components as a 3d vector
     * @return x components as a 3d vector
     */
    public float3 getColumn0(){
        return new float3(m[0][0], m[1][0], m[2][0]);
    }

    /**
     * returns the y components as a 3d vector
     * @return y components as a 3d vector
     */
    public float3 getColumn1(){
        return new float3(m[0][1], m[1][1], m[2][1]);
    }

    /**
     * returns the z components as a 3d vector
     * @return z components as a 3d vector
     */
    public float3 getColumn2(){
        return new float3(m[0][2], m[1][2], m[2][2]);
    }

    /**
     * returns 0th row as a 3d vector
     * @return  0th row as a 3d vector
     */
    public float3 getRow0(){
        return new float3(m[0][0], m[0][1], m[0][2]);
    }

    /**
     * returns 1st row as a 3d vector
     * @return 1st row as a 3d vector
     */
    public float3 getRow1(){
        return new float3(m[1][0], m[1][1], m[1][2]);
    }

    /**
     * returns 2nd row as a 3d vector
     * @return 2nd row as a 3d vector
     */
    public float3 getRow2(){
        return new float3(m[2][0], m[2][1], m[2][2]);
    }

    /**
     * returns the transposed matrix
     * @return transposed matrix
     */
    public float3x3 transpose(){
        return new float3x3(getColumn0(), getColumn1(), getColumn2());
    }

    public float3x3 inverse(){
		/* because this is a openGL rotation matrix, we can simply transpose it to inverse it */
        return transpose();
    }

    /* multiplications */

    /**
     * matrix multiplication
     * @param mat
     * @return (this * mat)
     */
    public float3x3 multiply(float3x3 mat){
        float3 row0 = new float3(getRow0().dot(mat.getColumn0()), getRow0().dot(mat.getColumn1()), getRow0().dot(mat.getColumn2()));
        float3 row1 = new float3(getRow1().dot(mat.getColumn0()), getRow1().dot(mat.getColumn1()), getRow1().dot(mat.getColumn2()));
        float3 row2 = new float3(getRow2().dot(mat.getColumn0()), getRow2().dot(mat.getColumn1()), getRow2().dot(mat.getColumn2()));

        return new float3x3(row0, row1, row2);
    }

    /**
     * multiplies the whole matrix componentwise by a scalar
     * @param scalar
     * @return scaled matrix
     */
    public float3x3 multiply(float scalar){
        float3x3 result = new float3x3();

        result.m[0][0] = m[0][0] * scalar;
        result.m[0][1] = m[0][1] * scalar;
        result.m[0][2] = m[0][2] * scalar;

        result.m[1][0] = m[1][0] * scalar;
        result.m[1][1] = m[1][1] * scalar;
        result.m[1][2] = m[1][2] * scalar;

        result.m[2][0] = m[2][0] * scalar;
        result.m[2][1] = m[2][1] * scalar;
        result.m[2][2] = m[2][2] * scalar;

        return result;
    }

    /**
     * parses this matrix into a float array
     * @return float array
     */
    public float[] toFloat(){
        return new float[] { m[0][0], m[0][1], m[0][2], m[1][0], m[1][1], m[1][2], m[2][0], m[2][1], m[2][2] };
    }

    /**
     * parses this matrix into a float buffer
     * @return float buffer
     */
    public FloatBuffer toBuffer(){
        FloatBuffer buffer = ByteBuffer.allocateDirect(9 << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(toFloat());
        buffer.flip();
        return buffer;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return m[0][0] + " | " + m[0][1] + " | " + m[0][2] + "\n" + m[1][0] + " | " + m[1][1] + " | " + m[1][2] + "\n" + m[2][0] + " | " + m[2][1] + " | " + m[2][2];
    }
}
