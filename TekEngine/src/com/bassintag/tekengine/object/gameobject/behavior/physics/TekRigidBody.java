package com.bassintag.tekengine.object.gameobject.behavior.physics;

import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.object.gameobject.behavior.TekBehavior;
import com.bassintag.tekengine.physics.ITekPhysicsListener;
import com.bassintag.tekengine.physics.TekCollision;
import com.bassintag.tekengine.physics.materials.TekPhysicsMaterial;
import com.bassintag.tekengine.utils.vector.TekVector2f;
import com.bassintag.tekengine.window.TekWindow;
import org.lwjgl.opengl.GL11;

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
    public float                gravityScale = 1.0f;

    /**
     * Represents the direction of the gravity
     */
    public TekVector2f          gravityVector = TekVector2f.DOWN;

    /**
     * Represents whether or not the rotation should be frozen
     */
    public boolean              freezeRotation = false;

    /**
     * Represents whether or not this body should use physics
     */
    public boolean              kinematic = false;

    private float               mass = 1.0f;

    private float               inv_mass = 1.0f;

    /**
     * Represents the rotational inertia
     */
    public float                inertia = 0.0f;

    /**
     * Represents the physics material of this body
     */
    public TekPhysicsMaterial   material = TekPhysicsMaterial.DEFAULT;

    /**
     * Represents the current velocity of the body
     */
    public TekVector2f          velocity;

    /**
     * Represent the sum of the forces applied to this object
     */
    public TekVector2f          force = TekVector2f.ZERO.clone();
    /**
     * Represents the collider of this body
     */
    public TekCollider          collider;

    /**
     * Represents the static friction
     */
    public float                staticFriction = 0.5f;

    /**
     * Represents the static friction
     */
    public float                dynamicFriction = 0.5f;

    /**
     * @param gameObject the game object holding this behavior
     */
    public TekRigidBody(TekGameObject gameObject)
    {
        super(gameObject);
        collider = gameObject.getBehavior(TekCollider.class);
        velocity = TekVector2f.ZERO.clone();
        gameObject.physicsBehaviors.add(this);
    }

    public float    getMass()
    {
        return (mass);
    }

    public void setMass(float mass)
    {
        this.mass = mass;
        if (mass != 0.0f)
            inv_mass = 1.0f / mass;
        else
            inv_mass = 0.0f;
    }

    public float    getInvMass() {
        return (inv_mass);
    }

    @Override
    public void init()
    {
    }

    @Override
    public void update(float delta) {
        integrateForces(delta);
    }

    public void integrateForces(float delta)
    {
        float dts;

        if (inv_mass == 0.0f)
            return ;
        dts = delta * 0.5f;
        velocity.add(TekVector2f.scale(force, inv_mass * dts));
        velocity.add(TekVector2f.scale(gravityVector, inv_mass * gravityScale * dts));
    }

    public void integrateVelocity(float delta)
    {
        transform.position.add(TekVector2f.scale(velocity, delta));
    }

    @Override
    public void render(TekWindow window) {

    }

    @Override
    public void renderDebug() {
        GL11.glColor3f(1.0f, 0, 0);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        TekVector2f v1 = transform.position;
        TekVector2f v2 = TekVector2f.add(transform.position, velocity);
        GL11.glVertex2d(v1.x, v1.y);
        GL11.glVertex2d(v2.x, v2.y);
        GL11.glEnd();
        GL11.glColor3f(1.0f, 1.0f, 0);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        v2 = TekVector2f.add(transform.position, gravityVector.clone().scale(gravityScale));
        GL11.glVertex2d(v1.x, v1.y);
        GL11.glVertex2d(v2.x, v2.y);
        GL11.glEnd();
        GL11.glColor3f(0, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_LINE_STRIP);
        v2 = TekVector2f.add(transform.position, force);
        GL11.glVertex2d(v1.x, v1.y);
        GL11.glVertex2d(v2.x, v2.y);
        GL11.glEnd();
    }

    @Override
    public void destroy() {}

    @Override
    public void onCollision(TekCollision collision) {}

    @Override
    public void onUpdatePhysics(float delta)
    {
        integrateVelocity(delta);
        force.set(0.0f, 0.0f);
    }
}
