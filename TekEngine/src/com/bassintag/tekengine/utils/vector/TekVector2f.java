package com.bassintag.tekengine.utils.vector;

/**
 * TekVector2f created for TekEngine
 * Created by Antoine on 01/12/2016.
 */
public class TekVector2f {

    public float    x;
    public float    y;

    public  TekVector2f(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void set(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public void add(float x, float y)
    {
        this.x += x;
        this.y += y;
    }

    public void lerp(TekVector2f end, float ratio)
    {
        x = (end.x - x) * ratio;
        y = (end.y - y) * ratio;
    }

    public static final TekVector2f ZERO = new TekVector2f(0, 0);

    public String   toString()
    {
        return ("Vec2f(x: " + x + ", y:" + y + ")");
    }
}
