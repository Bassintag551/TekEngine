package com.bassintag.tekengine.camera;

/**
 * TekCamera created for TekEngine
 * Created by Antoine on 02/12/2016.
 */
public class TekCamera {

    private float     x, y, width, height;
    private float     minX, minY, maxX, maxY;

    public          TekCamera(float x, float y, float width, float height)
    {
        set(x, y, width, height);
    }

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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getMinX() {
        return minX;
    }

    public float getMinY() {
        return minY;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMaxY() {
        return maxY;
    }
}
