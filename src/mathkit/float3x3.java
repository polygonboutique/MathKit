package mathkit;

/** http://deadkitteninaframe.com/wordpress/ */
/** 3x3 Matrix */

public class float3x3 {
    public float m[][] = new float[3][3];

    public float3x3(){
        setIdentity();
    }

    public void setIdentity(){
        m[0][0] = 1.0f; m[0][1] = 0.0f; m[0][2] = 0.0f;
        m[1][0] = 0.0f; m[1][1] = 1.0f; m[1][2] = 0.0f;
        m[2][0] = 0.0f; m[2][1] = 0.0f; m[2][2] = 1.0f;
    }
}
