package com.bassintag.tekengine.object.gameobject;

/**
 * TekRotation.java created for TekEngine
 *
 * Represents a rotation, stores the angle both in degrees and radians to minimize conversions
 * @author Antoine
 * @version 1.0
 * @since 04/12/2016
 */
public class TekRotation {

    private float   rotationDeg;
    private float   rotationRad;

    public TekRotation()
    {
        this.rotationDeg = 0.0f;
        this.rotationRad = 0.0f;
    }

    /**
     * Sets the rotation value using degrees
     * @param deg the rotation angle in degrees
     */
    public void setDeg(float deg)
    {
        this.rotationDeg = deg;
        this.rotationRad = (float) Math.toRadians(deg);
    }

    /**
     * Sets the rotation value using radians
     * @param rad the rotation angle in radians
     */
    public void setRad(float rad)
    {
        this.rotationRad = rad;
        this.rotationDeg = (float) Math.toDegrees(rad);
    }

    /**
     * Adds to the rotation value using degrees
     * @param deg the rotation angle to be added in degrees
     */
    public void addDeg(float deg)
    {
        this.rotationDeg += deg;
        this.rotationRad = (float) Math.toRadians(rotationDeg);
    }

    /**
     * Adds to the rotation value using radians
     * @param rad the rotation angle to be added in radians
     */
    public void addRad(float rad)
    {
        this.rotationRad += rad;
        this.rotationDeg = (float) Math.toDegrees(rotationRad);
    }

    /**
     * Gets the rotation angle in degrees
     * @return the rotation angle
     */
    public float    getDeg()
    {
        return (rotationDeg);
    }

    /**
     * Gets the rotation angle in radians
     * @return the rotation angle
     */
    public float    getRad()
    {
        return (rotationRad);
    }
}
