package com.bassintag.tekengine.camera;

/**
 * TekCamera.java created for TekEngine
 *
 * Represents the camera filming the part of the world that should be viewed in the window
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public class TekCamera {

    private float   x, y, width, height;
    private float   minX, minY, maxX, maxY;
    public float    rotation;

    /**
     * @param x position of the center of the camera on the x axis
     * @param y position of the center of the camera on the y axis
     * @param width width of the camera
     * @param height height of the camera
     */
    public          TekCamera(float x, float y, float width, float height)
    {
        set(x, y, width, height);
        rotation = 0.0f;
    }

    /**
     * Moves the camera to a new position and sets it's size
     * @param x position of the center of the camera on the x axis
     * @param y position of the center of the camera on the y axis
     * @param width width of the camera
     * @param height height of the camera
     */
    public void     set(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.minX = x - width / 2;
        this.minY = y - height / 2;
        this.maxX = x + width / 2;
        this.maxY = y + height / 2;
    }

    /**
     * Moves the camera to a new position
     * @param x position of the center of the camera on the x axis
     * @param y position of the center of the camera on the y axis
     */
    public void     translate(float x, float y)
    {
        set(this.x + x, this.y + y, width, height);
    }

    /**
     * Moves the camera in order to make it look at the specified point
     * @param x the position of the point on the x axis
     * @param y the position of the point on the y axis
     */
    public void     lookAt(float x, float y)
    {
        set(x, y, width, height);
    }

    /**
     * Gets the x position of the camera
     * @return the position of the center of the camera on the x axis
     */
    public float    getX() {
        return x;
    }

    /**
     * Gets the y position of the camera
     * @return the position of the center of the camera on the y axis
     */
    public float    getY() {
        return y;
    }

    /**
     * Gets the width of the camera
     * @return the width of the camera
     */
    public float    getWidth() {
        return width;
    }

    /**
     * Gets the height of the camera
     * @return the height of the camera
     */
    public float    getHeight() {
        return height;
    }

    /**
     * Gets the position of the top left corner of the camera on the x axis
     * @return the left most position of the camera
     */
    public float    getMinX() {
        return minX;
    }

    /**
     * Gets the position of the top left corner of the camera on the y axis
     * @return the top most position of the camera
     */
    public float    getMinY() {
        return minY;
    }

    /**
     * Gets the position of the bottom right corner of the camera on the x axis
     * @return the right most position of the camera
     */
    public float    getMaxX() {
        return maxX;
    }

    /**
     * Gets the position of the bottom right corner of the camera on the y axis
     * @return the bottom most position of the camera
     */
    public float    getMaxY() {
        return maxY;
    }
}
