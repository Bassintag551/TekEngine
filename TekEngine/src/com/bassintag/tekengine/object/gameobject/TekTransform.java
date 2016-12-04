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
    public TekRotation  rotation;

    public TekTransform()
    {
        position = new TekVector2f(0.0f, 0.0f);
        scale = new TekVector2f(1.0f, 1.0f);
        rotation = new TekRotation();
    }

    /**
     * Gets a new vector equal to the input vector with the transformation applied to it
     * @param input the initial vector
     * @return the transformed vector
     */
    public TekVector2f  apply(TekVector2f input)
    {
        TekVector2f     result;

        result = new TekVector2f((float)(Math.cos(rotation.getRad()) * input.x - Math.sin(rotation.getRad()) * input.y),
                                (float)(Math.cos(rotation.getRad()) * input.y + Math.sin(rotation.getRad()) * input.x));
        result.multiply(scale);
        result.add(position);
        return (result);
    }
}
