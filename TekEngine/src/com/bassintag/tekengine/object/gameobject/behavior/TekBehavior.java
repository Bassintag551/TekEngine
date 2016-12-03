package com.bassintag.tekengine.object.gameobject.behavior;

import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.object.gameobject.TekTransform;

/**
 * TekBehavior.java created for TekEngine
 *
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public abstract class TekBehavior extends TekObject {

    /**
     * Represents the game object holding this behavior
     */
    public final TekGameObject  gameObject;

    /**
     * Reference to the transform of the game object holding this behavior
     */
    public final TekTransform   transform;

    /**
     * Represents whether or not this behavior is enabled
     */
    public boolean              enabled;

    /**
     * @param gameObject the game object holding this behavior
     */
    public TekBehavior(TekGameObject gameObject)
    {
        this.gameObject = gameObject;
        this.transform = gameObject.transform;
        this.enabled = true;
    }
}
