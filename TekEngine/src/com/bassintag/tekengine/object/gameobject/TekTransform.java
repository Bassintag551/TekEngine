package com.bassintag.tekengine.object.gameobject;

import com.bassintag.tekengine.utils.vector.TekVector2f;

/**
 * TekTransform.java created for TekEngine
 *
 * Represents the transformation applied to a game object
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public class TekTransform {

    /**
     * Represents the position transformation
     */
    public TekVector2f  position;

    /**
     * Represents the scale transformation
     */
    public TekVector2f  scale;

    /**
     * Represents the rotation transformation
     */
    public float        rotation;

    public TekTransform()
    {
        position = new TekVector2f(0.0f, 0.0f);
        scale = new TekVector2f(1.0f, 1.0f);
        rotation = 0.0f;
    }

    public TekVector2f  apply(TekVector2f input)
    {
        TekVector2f     result;

        result = new TekVector2f((float)(Math.cos(rotation) * input.x - Math.sin(rotation) * input.y),
                                (float)(Math.cos(rotation) * input.y + Math.sin(rotation) * input.x));
        result.multiply(scale);
        result.add(position);
        return (result);
    }
}
