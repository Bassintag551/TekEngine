package com.bassintag.tekengine.object.gameobject.behavior.physics;

import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.utils.vector.TekVector2f;

/**
 * TekEdgeCollider.java created for TekEngine
 *
 * Represents a collider with only one edge
 * @author Antoine
 * @version 1.0
 * @since 04/12/2016
 */
public class TekEdgeCollider extends TekCollider{

    /**
     * @param gameObject the game object holding this collider
     * @param start the first point of the edge
     * @param end the second point of the edge
     */
    public TekEdgeCollider(TekGameObject gameObject, TekVector2f start, TekVector2f end) {
        super(gameObject, start, end);
    }
}
