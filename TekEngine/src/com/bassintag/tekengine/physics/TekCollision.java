package com.bassintag.tekengine.physics;

import com.bassintag.tekengine.object.gameobject.behavior.physics.TekCollider;
import com.bassintag.tekengine.utils.vector.TekVector2f;

/**
 * TekCollision.java created for TekEngine
 *
 * Represents a collision between two colliders
 * @author Antoine
 * @version 1.0
 * @since 04/12/2016
 */
public class TekCollision {

    /**
     * Represents the first collider
     */
    public final TekCollider    collider1;

    /**
     * Represents the second collider
     */
    public final TekCollider    collider2;

    /**
     * Represents the Minimum Translation Vector (MTV)
     */
    public final TekVector2f    MTV;

    /**
     * @param collider1 The first collider
     * @param collider2 The second collider
     * @param MTV The MTV (from collider 1 to collider 2)
     */
    public TekCollision(TekCollider collider1, TekCollider collider2, TekVector2f MTV)
    {
        this.collider1 = collider1;
        this.collider2 = collider2;
        this.MTV = MTV;
    }

    /**
     * Gets the inverted collision event
     * @return the inverted collision
     */
    public TekCollision getInverted()
    {
        return (new TekCollision(collider2, collider1, TekVector2f.scale(MTV, -1.0f)));
    }
}
