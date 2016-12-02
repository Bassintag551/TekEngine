package com.bassintag.tekengine.object.gameobject;

import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.utils.vector.TekVector2f;

/**
 * TekGameObject.java created for TekEngine
 *
 * Represents an object that has a position within the game world
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public abstract class TekGameObject extends TekObject{

    /**
     * Represents the position of the game object
     */
    public TekVector2f      pos;

    /**
     * Represents the size of the game object
     */
    public TekVector2f      size;

    /**
     * Represents the scale of the game object
     */
    public TekVector2f      scale;
}
