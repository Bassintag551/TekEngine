package com.bassintag.tekengine.utils.vector;

/**
 * TekVector2f.java created for TekEngine
 *
 * Represents a 2D vector using float coordinates
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public class TekVector2f {

    /**
     * Represents the x position of the vector
     */
    public float    x;

    /**
     * Represents the y position of the vector
     */
    public float    y;

    /**
     * @param x the x position of the vector
     * @param y the y position of the vector
     */
    public  TekVector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the vector to new values
     * @param x the new x position of the vector
     * @param y the new y position of the vector
     * @return a reference to this vector
     */
    public TekVector2f  set(float x, float y)
    {
        this.x = x;
        this.y = y;
        return (this);
    }

    /**
     * Adds values to the x and y position of the vector
     * @param x the amount to be added to the x position
     * @param y the amount to be added to the y position
     * @return a reference to this vector
     */
    public TekVector2f  add(float x, float y)
    {
        this.x += x;
        this.y += y;
        return (this);
    }

    /**
     * Adds a vector to this one
     * @param vector the vector to be added to this one
     * @return a reference to this vector
     */
    public TekVector2f  add(TekVector2f vector)
    {
        this.x += vector.x;
        this.y += vector.y;
        return (this);
    }

    /**
     * Subs values from the x and y position of the vector
     * @param x the amount to be subbed from the x position
     * @param y the amount to be subbed from the y position
     * @return a reference to this vector
     */
    public TekVector2f  sub(float x, float y)
    {
        this.x -= x;
        this.y -= y;
        return (this);
    }

    /**
     * Subs a vector from this one
     * @param vector the vector to be subbed
     * @return a reference to this vector
     */
    public TekVector2f  sub(TekVector2f vector)
    {
        this.x -= vector.x;
        this.y -= vector.y;
        return (this);
    }

    /**
     * Multiply the x and y position of the vector by two respective values
     * @param x the amount to multiply the x position by
     * @param y the amount to multiply the y position by
     * @return a reference to this vector
     */
    public TekVector2f  multiply(float x, float y)
    {
        this.x *= x;
        this.y *= y;
        return (this);
    }

    /**
     * Multiply a vector with another
     * @param vector the vector to multiply this one by
     * @return a reference to this vector
     */
    public TekVector2f  multiply(TekVector2f vector)
    {
        this.x *= vector.x;
        this.y *= vector.y;
        return (this);
    }

    /**
     * Scales a vector by a given amount
     * @param scale the amount to scale by
     * @return a reference to this vector
     */
    public TekVector2f  scale(float scale)
    {
        this.x *= scale;
        this.y *= scale;
        return (this);
    }

    /**
     * Lerps the vector toward another
     * @param end the vector towards which this vector should lerp
     * @param ratio the amount to lerp by (0.0 = no movement, 1.0 = reach the other vector)
     * @return a reference to this vector
     */
    public TekVector2f  lerp(TekVector2f end, float ratio)
    {
        x = (end.x - x) * ratio;
        y = (end.y - y) * ratio;
        return (this);
    }

    /**
     * Gets the magnitude of this vector
     * @return the magnitude of the vector
     */
    public float        getMagnitude()
    {
        return ((float) Math.sqrt(x * x + y * y));
    }

    /**
     * Normalize the vector
     * @return a reference to this vector
     */
    public TekVector2f  normalize()
    {
        float           magnitude;

        magnitude = getMagnitude();
        x = x / magnitude;
        y = y / magnitude;
        return (this);
    }

    /**
     * Gets the dot product between this vector and another
     * @param vector the other vector
     * @return the dot product
     */
    public float    dot(TekVector2f vector)
    {
        return (x * vector.x + y * vector.y);
    }

    /**
     * Gets the cross product between this vector and another
     * @param vector the other vector
     * @return the cross product
     */
    public float    cross(TekVector2f vector)
    {
        return (x * vector.y) - (y * vector.x);
    }

    /**
     * Gets the perpendicular vector to this one, equals to a vector of coordinates (x, -y)
     * @return the perpendicular vector
     */
    public TekVector2f  getPerpendicular()
    {
        return (new TekVector2f(-y, -x));
    }

    /**
     * Creates a new TekVector2f with the same coordinates as this one
     * @return the new vector
     */
    public TekVector2f  clone()
    {
        return (new TekVector2f(x, y));
    }

    /**
     * Gets the sum of two vectors as a new vector
     * @param vector1 the first vector
     * @param vector2 the second vector
     * @return the sum of both vectors
     */
    public static TekVector2f   add(TekVector2f vector1, TekVector2f vector2)
    {
        return (new TekVector2f(vector1.x + vector2.x, vector1.y + vector2.y));
    }

    /**
     * Gets the result of the second vector substracted from the first vector
     * @param vector1 the first vector
     * @param vector2 the second vector
     * @return the substraction of the second vector from the first vector
     */
    public static TekVector2f   sub(TekVector2f vector1, TekVector2f vector2)
    {
        return (new TekVector2f(vector1.x - vector2.x, vector1.y - vector2.y));
    }

    /**
     * Gets a new vector equals to the given one scaled by the given amount
     * @param vector the vector to scale
     * @param amount the amount to scale by
     * @return the scaled vector
     */
    public static TekVector2f   scale(TekVector2f vector, float amount)
    {
        return (new TekVector2f(vector.x * amount, vector.y  * amount));
    }

    /**
     * Represents a null vector
     */
    public static final TekVector2f ZERO = new TekVector2f(0.0f, 0.0f);

    /**
     * Represents a vector going up
     */
    public static final TekVector2f UP = new TekVector2f(0.0f, 1.0f);

    /**
     * Represents a vector going down
     */
    public static final TekVector2f DOWN = new TekVector2f(0.0f, -1.0f);

    /**
     * Represents a vector going left
     */
    public static final TekVector2f LEFT = new TekVector2f(1.0f, 0.0f);

    /**
     * Represents a vector going right
     */
    public static final TekVector2f RIGHT = new TekVector2f(-1.0f, 0.0f);

    @Override
    public String   toString()
    {
        return ("Vec2f(x: " + x + ", y:" + y + ")");
    }
}
