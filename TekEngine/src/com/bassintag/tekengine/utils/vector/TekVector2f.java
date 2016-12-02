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
     */
    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Adds values to the x and y position of the vector
     * @param x the amount to be added to the x position
     * @param y the amount to be added to the y position
     */
    public void add(float x, float y)
    {
        this.x += x;
        this.y += y;
    }

    /**
     * Lerps the vector toward another
     * @param end the vector towards which this vector should lerp
     * @param ratio the amount to lerp by (0.0 = no movement, 1.0 = reach the other vector)
     */
    public void lerp(TekVector2f end, float ratio)
    {
        x = (end.x - x) * ratio;
        y = (end.y - y) * ratio;
    }

    /**
     * Represents a null vector
     */
    public static final TekVector2f ZERO = new TekVector2f(0.0f, 0.0f);

    public String   toString()
    {
        return ("Vec2f(x: " + x + ", y:" + y + ")");
    }
}
