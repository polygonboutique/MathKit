package mathkit;

/** http://deadkitteninaframe.com/wordpress/ */
/** 2D Vector */

public class float2 {
    public float x, y;

    public float2(){
        x = y = 0;
    }

    public float2(float x){
        this.x = x;
        y = 0;
    }

    public float2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float2(float3 xy){
        this.x = xy.x;
        this.y = xy.y;
    }

    public float2(float2 u){
        this.x = u.x;
        this.y = u.y;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    public float2 clone(){
        return new float2(this);
    }

    /**
     * scales the vector by a factor
     * @param scalar
     * @return new float2
     */
    public float2 multiply(float scalar){
        return new float2(x * scalar, y * scalar);
    }

    /**
     * scales a vector by 2 factors
     * x * u.x
     * y * u.y
     * @param u
     * @return new float2
     */
    public float2 multiply(float2 u){
        return new float2(x * u.x, y * u.y);
    }

    /**
     * devides the vector by the x&y components
     * @param u
     * @return float2
     */
    public float2 divide(float2 u){
        return new float2( x / u.x, y /u.y);
    }

    /**
     * scales the vector down (divides) by a factor
     * @param factor
     * @return new float2
     */
    public float2 divide(float factor){
        float inva = 1.0f / factor;
        return new float2(x * inva, y * inva);
    }

    /**
     * scales the vector 'locally'
     * does not return a new vector
     * @param scalar
     */
    public void scale(float scalar){
        this.x *= scalar;
        this.y *= scalar;
    }

    /**
     * returns the length of the vector
     * @return vectorlength
     */
    public float length(){
        return (float) Math.sqrt(x*x + y*y);
    }

    /**
     * returns the squared length of the vector
     * @return squared vectorlength
     */
    public float lengthSquared(){
        return (x*x + y*y);
    }

    /**
     * normalized the vector 'locally'
     * does not return a new float2
     */
    public void normalize(){
        scale(1 / length());
    }

    /**
     * changes the x & y components to the parameter vectors values
     * @param u float2
     */
    public void set(float2 u){
        this.x = u.x;
        this.y = u.y;
    }

    /**
     * changes the x & y components to the parameters
     * @param x
     * @param y
     */
    public void set(float x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * changes the x value
     * @param x
     */
    public void setX(float x){
        this.x = x;
    }

    /**
     * changes the y value
     * @param y
     */
    public void setY(float y){
        this.y = y;
    }

    /**
     * rotates the vector by a given amout of degrees
     * @param angleInDegrees - angle in degrees
     * @return new float2
     */
    public float2 rotate(float angleInDegrees) {
        float cs = (float) Math.cos(Math.toRadians(angleInDegrees));
        float sn = (float) Math.sin(Math.toRadians(angleInDegrees));
        float px = x * cs - y * sn;
        float py = x * sn + y * cs;
        return new float2(px, py);
    }

    /**
     * reflects the vector around a vector
     * @param around - vector on which this vector will be reflected
     * @return new float2 reflection vector
     */
    public float2 reflect(float2 around){
        float2 normal = around.normalized();
        return normal.multiply((-2 * this.dot(normal))).add(this);
    }

    /**
     * subtracts vector u from this vector
     * @param u
     * @return new float2
     */
    public float2 sub(float2 u) {
        return new float2(x - u.x , y - u.y);
    }

    /**
     * adds vector u to this vector
     * @param u
     * @return new float
     */
    public float2 add(float2 u) {
        return new float2(x + u.x, y + u.y);
    }

    /**
     * creates the 2d cross product
     * source: http://stackoverflow.com/questions/243945/calculating-a-2d-vectors-cross-product
     * @param u
     * @return float cross product
     */
    public float cross(float2 u) {
        return ((x * u.y) - (y * u.x));
    }

    /**
     * creates the 2d cross product
     * source: http://stackoverflow.com/questions/243945/calculating-a-2d-vectors-cross-product
     * @return float2 - crossproduct
     */
    public float2 cross() {
        return new float2(y, -x);
    }

    /**
     * calculates the distance between two vectors
     * @param u
     * @return float
     */
    public float2 distance(float2 u) {
        return new float2(Math.abs(x - u.x), Math.abs(y - u.y));
    }

    /**
     * returns the angle between two vectors
     * @param u
     * @return float
     */
    public float angleBetween(float2 u){
        return (float) (Math.atan2(x * u.y - y * u.x, x * u.x + y * u.y) / Math.PI * 180);
    }

    /**
     * dot product definition
     * returns angle between two vectors
     * @param u
     * @return float
     */
    public float angleBetween2(float2 u){
        return (float) (Math.acos(this.normalized().dot(u.normalized())) / Math.PI * 180);
    }

    /**
     * creates the dotproduct between two vectors
     * @param u
     * @return float
     */
    public float dot(float2 u){
        return (x * u.x + y * u.y);
    }

    /**
     * negates the vector locally
     */
    public void inverse(){
        x = -x;
        y = -y;
    }

    /**
     * returns the inversed vector
     * @return new float2
     */
    public float2 inversed(){
        float2 res = new float2(this);
        res.inverse();
        return res;
    }

    /**
     * returns the normalized vector
     * @return new float2
     */
    public float2 normalized(){
        float2 res = new float2(this);
        res.normalize();
        return res;
    }

    /**
     * parses the vector into an float array
     * @return float array
     */
    public float[] toFloat(){
        return new float[]{x, y};
    }

	/* glsl style swizzling */

    /* float 2*/
    public float2 xx(){
        return new float2(x, x);
    }

    public float2 yy(){
        return new float2(y, y);
    }

    public float2 xy(){
        return new float2(x, y);
    }

    public float2 yx(){
        return new float2(y, x);
    }

    /* float 3 */
    public float3 xxx(){
        return new float3(x, x, x);
    }

    public float3 yyy(){
        return new float3(y, y, y);
    }

    public float3 xxy(){
        return new float3(x, x, y);
    }

    public float3 xyx(){
        return new float3(x, y, x);
    }

    public float3 yxx(){
        return new float3(y, x, x);
    }

    public float3 yyx(){
        return new float3(y, y, x);
    }

    public float3 yxy(){
        return new float3(y, x, y);
    }

    public float3 xyy(){
        return new float3(x, y, y);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "[ X: " + x + " Y: " + y + " ]";
    }
}
