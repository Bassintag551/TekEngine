package com.bassintag.tekengine.utils.vector;

/**
 * TekVector2i.java created for TekEngine
 *
 * Represents a 2D vector using int coordinates
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public class TekVector2i {

    /**
     * Represents the x position of the vector
     */
    public int x;

    /**
     * Represents the y position of the vector
     */
    public int y;

    /**
     * @param x the x position of the vector
     * @param y the y position of the vector
     */
    public  TekVector2i(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Lerps the vector toward another
     * @param end the vector towards which this vector should lerp
     * @param ratio the amount to lerp by (0.0 = no movement, 1.0 = reach the other vector)
     */
    public void lerp(TekVector2i end, float ratio)
    {
        x = x + (int)((end.x - x) * ratio);
        y = y + (int)((end.y - y) * ratio);
    }
}
