package com.bassintag.tekengine.utils.vector;

/**
 * TekVector2i.java created for TekEngine
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public class TekVector2i {

    public int x;
    public int y;

    public TekVector2i(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public static TekVector2i lerp(TekVector2i start, TekVector2i end, float ratio)
    {
        TekVector2i result;
        result = new TekVector2i(start.x + (int)((end.x - start.x) * ratio),
                start.y + (int)((end.y - start.y) * ratio));
        return (result);
    }
}
