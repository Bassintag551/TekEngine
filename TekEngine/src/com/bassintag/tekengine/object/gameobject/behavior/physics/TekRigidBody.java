package com.bassintag.tekengine.object.gameobject.behavior.physics;

import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.object.gameobject.behavior.TekBehavior;
import com.bassintag.tekengine.physics.ITekPhysicsListener;
import com.bassintag.tekengine.physics.TekCollision;
import com.bassintag.tekengine.utils.vector.TekVector2f;
import com.bassintag.tekengine.window.TekWindow;

/**
 * TekRigidBody.java created for TekEngine
 *
 * Represents a body affected by physics
 * @author Antoine
 * @version 1.0
 * @since 04/12/2016
 */
public class TekRigidBody extends TekBehavior implements ITekPhysicsListener{

    /**
     * Represents the stength of the gravity (in y units per second)
     */
    public float        gravityScale = 1.0f;

    /**
     * Represents the direction of the gravity
     */
    public TekVector2f  gravityVector = TekVector2f.DOWN;

    /**
     * Represents the current velocity of the body
     */
    public TekVector2f  velocity;

    /**
     * Represents the collider of this body
     */
    public TekCollider  collider;

    /**
     * @param gameObject the game object holding this behavior
     */
    public TekRigidBody(TekGameObject gameObject)
    {
        super(gameObject);
        collider = gameObject.getBehavior(TekCollider.class);
    }

    @Override
    public void init()
    {
        gameObject.physicsBehaviors.add(this);
    }

    @Override
    public void update(float delta) {
        gameObject.transform.position.add(TekVector2f.scale(gravityVector, gravityScale * delta));
    }

    @Override
    public void onCollision(TekCollision collision)
    {
        transform.position.add(collision.MTV);
    }

    @Override
    public void render(TekWindow window) {

    }

    @Override
    public void destroy() {

    }

}
