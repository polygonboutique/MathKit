package mathkit;

/** http://deadkitteninaframe.com/wordpress/ */
/** 3D Vector */

public class float3 {
    public float x, y, z;

    public float3() {
        x = y = z = 0;
    }

    public float3(float x) {
        this.x = x;
        y = z = 0;
    }

    public float3(float x, float y) {
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public float3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float3(float3 u) {
        this.x = u.x;
        this.y = u.y;
        this.z = u.z;
    }

    public float3(float2 xy) {
        x = xy.x;
        y = xy.y;
        z = 0;
    }

    public float3(float2 xy, float z) {
        this.x = xy.x;
        this.y = xy.y;
        this.z = z;
    }

    public float3(float x, float2 yz) {
        this.x = x;
        this.y = yz.x;
        this.z = yz.y;
    }

    public float3(float4 xyz) {
        this.x = xyz.x;
        this.y = xyz.y;
        this.z = xyz.z;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public float3 clone(){
        return new float3(this);
    }

    /**
     * creates surface normal vector
     * @param u
     * @param v
     * @return surface normal vector
     */
    public float3 makeNormal(float3 u, float3 v) {
        float3 p1 = u.sub(this);
        float3 p2 = v.sub(this);

        float3 normal = p1.cross(p2);
        normal.normalize();
        return normal;
    }

    /**
     * creates surface tangent vector
     * source and good explanation: http://fabiensanglard.net/bumpMapping/index.php
     * @param u
     * @param v
     * @param u_texCoord
     * @param v_texCoord
     * @param texCoord_associated_with_this_Vertex
     * @return surface tangent vector
     */
    public float3 makeTangent(float3 u, float3 v, float2 u_texCoord, float2 v_texCoord, float2 texCoord_associated_with_this_Vertex) {
        float3 v1 = u.sub(this);
        float3 v2 = v.sub(this);

        float2 st1 = u_texCoord.sub(texCoord_associated_with_this_Vertex);
        float2 st2 = v_texCoord.sub(texCoord_associated_with_this_Vertex);

        float coef = (float) (1.0 / (st1.x * st2.y - st2.x * st1.y));

        float3 tangent = new float3();
        tangent.x = coef * ((v1.x * st2.y) + (v2.x * -st1.y));
        tangent.y = coef * ((v1.y * st2.y) + (v2.y * -st1.y));
        tangent.z = coef * ((v1.z * st2.y) + (v2.z * -st1.y));
        tangent.normalize();

        return tangent;
    }

    /**
     * creates surface binormal vector
     * @param normal
     * @param tangent
     * @return surface binormal vector
     */
    public float3 getVertexBinormal(float3 normal, float3 tangent){
        return normal.cross(tangent);
    }
	
	/* only use this, if THIS float3 is a normalvector !*/
    /**
     * returns binormal vector, if THIS vector is a normal vector
     * @param tangent
     * @return http://fabiensanglard.net/bumpMapping/index.php
     */
    public float3 makeBinormal(float3 tangent){
        return this.cross(tangent);
    }

    /**
     * returns the length of the vector
     * @return float
     */
    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * returns the squared length of the vector
     * @return squared length
     */
    public float lengthSquared() {
        return (x * x + y * y + z * z);
    }

    /**
     * creates the 3d cross product between two vectors
     * @param u
     * @return new float3
     */
    public float3 cross(float3 u) {
        return new float3(y * u.z - z * u.y, z * u.x - x * u.z, x * u.y - y * u.x);
    }

    /**
     * creates the dot product between two vectors
     * @param u
     * @return float
     */
    public float dot(float3 u) {
        return (x * u.x + y * u.y + z * u.z);
    }

    /**
     * normalizes the vector locally
     */
    public void normalize() {
        scale(1 / length());
    }

    /**
     * subtracts vector u from this vector
     * @param u
     * @return new float3
     */
    public float3 sub(float3 u) {
        return new float3(x - u.x, y - u.y, z - u.z);
    }

    /**
     * adds vector u to this vector
     * @param u
     * @return new float3
     */
    public float3 add(float3 u) {
        return new float3(x + u.x, y + u.y, z + u.z);
    }

    /**
     * scales the vector by a factor
     * @param scalar
     * @return new float3
     */
    public float3 multiply(float scalar) {
        return new float3(x * scalar, y * scalar, z * scalar);
    }

    /**
     * scales a vector by 3 factors
     * @param u
     * @return new float3
     */
    public float3 multiply(float3 u) {
        return new float3(x * u.x, y * u.y, z * u.z);
    }

    /**
     * scales down (divides) the vector by 3 factors
     * @param u
     * @return new float3
     */
    public float3 divide(float3 u) {
        return new float3(x / u.x, y / u.y, z / u.z);
    }

    /**
     * scales down (divides) the vector by 3 factors
     * @param factor
     * @return new float3
     */
    public float3 divide(float factor) {
        float inv = 1.0f / factor;
        return new float3(x * inv, y * inv, z * inv);
    }

    /**
     * reflects the vector around a vector
     * @param around - vector on which this vector will be reflected
     * @return new float3
     */
    public float3 reflect(float3 around){
        float3 normal = around.normalized();
        return normal.multiply((-2 * this.dot(normal))).add(this);
    }

    /**
     * inverses / negates the vector locally
     */
    public void inverse() {
        x = -x;
        y = -y;
        z = -z;
    }

    /**
     * returns the inversed vector
     * @return new float3
     */
    public float3 inversed() {
        float3 res = new float3(this);
        res.inverse();
        return res;
    }

    /**
     * returns the normalized vector
     * @return new float3
     */
    public float3 normalized() {
        float3 res = new float3(this);
        res.normalize();
        return res;
    }

    /**
     * projects this vector onto another
     * @param n
     * @return projected vector
     */
    public float3 projectOnto(float3 n){
        return n.multiply(dot(n)/n.dot(n));
    }

	/* dot product definition */
    /**
     * returns the angle between two vectors
     * @param u
     * @return float (angle between 2 vectors)
     */
    public float angleBetween(float3 u) {
        return (float) (Math.acos((dot(u) / (length() * u.length()))) / Math.PI * 180);
    }

    /**
     * returns the distance between 2 vectors
     * @param u
     * @return float - distance
     */
    public float3 distance(float3 u) {
        return new float3(Math.abs(x - u.x), Math.abs(y - u.y), Math.abs(z - u.z));
    }

    /**
     * returns the euclidean distance between 2 vectors
     * @param u
     * @return float - euclidean distance between 2 vectors
     */
    public float distanceEuclid(float3 u){
        float ex = (x - u.x);
        float ey = (y - u.y);
        float ez = (z - u.z);
        return (float) Math.sqrt(ex * ex + ey * ey + ez * ez);
    }

    /**
     * scales the vector locally
     * @param scalar
     */
    public void scale(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
    }

    /**
     * changes the vectors value to the parameter vectors values locally
     * @param u
     */
    public void set(float3 u) {
        this.x = u.x;
        this.y = u.y;
        this.z = u.z;
    }

    /**
     * changes the x component
     * @param x
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * changes the y component
     * @param y
     */
    public void setY(float y) {
        this.y = y;
    }

    /**
     * changes the z component
     * @param z
     */
    public void setZ(float z) {
        this.z = z;
    }

    /**
     * parses the vector to a float array
     * @return float array
     */
    public float[] toFloat() {
        return new float[] { x, y, z };
    }

    /* float 2 */
    public float2 xx() {
        return new float2(x, x);
    }

    public float2 yy() {
        return new float2(y, y);
    }

    public float2 xy() {
        return new float2(x, y);
    }

    public float2 yx() {
        return new float2(y, x);
    }

    public float2 xz() {
        return new float2(x, z);
    }

    public float2 zx() {
        return new float2(z, x);
    }

    public float2 yz() {
        return new float2(y, z);
    }

    public float2 zy() {
        return new float2(z, y);
    }

    public float2 zz() {
        return new float2(z, z);
    }

    /* float 3 */
    public float3 xxx() {
        return new float3(x, x, x);
    }

    public float3 yyy() {
        return new float3(y, y, y);
    }

    public float3 xxy() {
        return new float3(x, x, y);
    }

    public float3 xyx() {
        return new float3(x, y, x);
    }

    public float3 yxx() {
        return new float3(y, x, x);
    }

    public float3 yyx() {
        return new float3(y, y, x);
    }

    public float3 yxy() {
        return new float3(y, x, y);
    }

    public float3 xyy() {
        return new float3(x, y, y);
    }

    public float3 zzz() {
        return new float3(z, z, z);
    }

    public float3 xyz() {
        return new float3(x, y, z);
    }

    public float3 zxy() {
        return new float3(z, x, y);
    }

    public float3 zyx() {
        return new float3(z, y, x);
    }

    public float3 xzy() {
        return new float3(x, y, z);
    }

    public float3 yzx() {
        return new float3(y, z, x);
    }

    public float3 yxz() {
        return new float3(y, x, z);
    }

    public float3 yyz() {
        return new float3(y, y, z);
    }

    public float3 xxz() {
        return new float3(x, x, z);
    }

    public float3 yzy() {
        return new float3(y, z, y);
    }

    public float3 zyy() {
        return new float3(z, y, y);
    }

    public float3 xzx() {
        return new float3(x, z, x);
    }

    public float3 zxx() {
        return new float3(z, x, x);
    }

    public float3 xzz() {
        return new float3(x, z, z);
    }

    public float3 yzz() {
        return new float3(y, z, z);
    }

    public float3 zzx() {
        return new float3(z, z, x);
    }

    public float3 zzy() {
        return new float3(z, z, y);
    }

    public float3 zyz() {
        return new float3(z, y, z);
    }

    public float3 zxz() {
        return new float3(z, x, z);
    }

    /**
     * checks if two vectors are equal by values
     * @param u
     * @return bool
     */
    public boolean equals(float3 u) {
        return (x == u.x && y == u.y && z == u.z);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[ X: " + x + " Y: " + y + " Z: " + z + " ]";
    }
}
