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

    public TekVector2f  position;
    public TekVector2f  scale;
    public float        rotation;

    public TekTransform()
    {
        position = new TekVector2f(0.0f, 0.0f);
        scale = new TekVector2f(1.0f, 1.0f);
        rotation = 0.0f;
    }
}
