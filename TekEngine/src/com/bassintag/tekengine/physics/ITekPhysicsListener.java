package com.bassintag.tekengine.physics;

/**
 * ITekPhysicsListener.java created for TekEngine
 *
 * @author Antoine
 * @version 1.0
 * @since 04/12/2016
 */
public interface ITekPhysicsListener {

    /**
     * Called when a collision involving this object is detected
     * @param collision the collision
     */
    void onCollision(TekCollision collision);

    /**
     * Called when physics has been updated
     * @param delta the delta time
     */
    void onUpdatePhysics(float delta);
}
