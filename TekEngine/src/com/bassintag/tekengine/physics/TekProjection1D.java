package com.bassintag.tekengine.physics;

/**
 * TekProjection1D.java created for TekEngine
 *
 * Represents the projection of a 2D shape along an axis
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public class TekProjection1D {

    /**
     * Represents the beginning position of the projection
     */
    public final float  min;

    /**
     * Represents the end position of the projection
     */
    public final float  max;

    /**
     * @param min the beginning position of the projection
     * @param max the end position of the projection
     */
    public  TekProjection1D(float min, float max)
    {
        this.min = min;
        this.max = max;
    }

    /**
     * Checks if this projection intersects with another
     * @param projection the other projection
     * @return true if they intersect or false if they don't
     */
    public boolean  intersect(TekProjection1D projection)
    {
        return (this.max <= projection.max && this.max >= projection.min ||
        this.min >= projection.min && this.min <= projection.max);
    }

    @Override
    public String   toString()
    {
        return ("TekProjection1D(min: " + min + ", max:" + max + ")");
    }
}
