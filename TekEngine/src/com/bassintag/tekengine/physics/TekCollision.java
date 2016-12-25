package com.bassintag.tekengine.physics;

import com.bassintag.tekengine.object.gameobject.behavior.physics.TekCollider;
import com.bassintag.tekengine.object.gameobject.behavior.physics.TekRigidBody;
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
     * Represents the penetration depth
     */
    public final float          penetrationDepth;

    /**
     * Represents the collision normal
     */
    public final TekVector2f    normal;

    /**
     * Represents whether or not this collision was canceled;
     */
    public boolean              canceled = false;

    /**
     * @param collider1 The first collider
     * @param collider2 The second collider
     * @param MTV The MTV (from collider 1 to collider 2)
     */
    public          TekCollision(TekCollider collider1, TekCollider collider2, TekVector2f MTV)
    {
        TekVector2f relative;
        this.collider1 = collider1;
        this.collider2 = collider2;
        this.MTV = MTV;
        this.penetrationDepth = MTV.getMagnitude();
        this.normal = MTV.clone().normalize();
    }

    private void        resolveFriction(TekRigidBody bodyA, TekRigidBody bodyB, float j)
    {
        TekVector2f     tangent;
        TekVector2f     frictionImpulse;
        TekVector2f     rv;
        float           jt;
        float           mu;
        float           dynamicFriction;
        float           frictionA;
        float           frictionB;

        rv = TekVector2f.sub(bodyB.velocity, bodyA.velocity);
        if (rv.dot(normal) == rv.getMagnitude())
            return ;
        tangent = TekVector2f.sub(rv, TekVector2f.scale(normal, rv.dot(normal)));
        tangent.normalize();
        jt = -rv.dot(tangent);
        jt /= (bodyA.getInvMass() + bodyB.getInvMass());
        frictionA = bodyA.staticFriction;
        frictionB = bodyB.staticFriction;
        mu = (float) Math.sqrt(frictionA * frictionA + frictionB * frictionB);
        System.out.println("mu: " + mu + ", j: " + j + ", jt: " + jt);
        if (Math.abs(jt) < j * mu)
            frictionImpulse = TekVector2f.scale(tangent, jt);
        else
        {
            frictionA = bodyA.dynamicFriction;
            frictionB = bodyB.dynamicFriction;
            dynamicFriction = (float) Math.sqrt(frictionA * frictionA + frictionB * frictionB);
            frictionImpulse = TekVector2f.scale(tangent, j * dynamicFriction);
        }
        bodyA.velocity.add(frictionImpulse.clone().scale(bodyA.getInvMass()));
        bodyB.velocity.sub(frictionImpulse.scale(bodyB.getInvMass()));
    }

    /**
     * Resolves the collision
     */
    public void         resolve()
    {
        TekRigidBody    bodyA;
        TekRigidBody    bodyB;
        TekVector2f     rv;
        TekVector2f     impulse;
        TekVector2f     correction;
        float           normalVelocity;
        float           massSum;
        float           ratio;
        float           e;
        float           j;

        bodyA = collider1.gameObject.getBehavior(TekRigidBody.class);
        bodyB = collider2.gameObject.getBehavior(TekRigidBody.class);
        if (bodyA == null || bodyB == null || (bodyA.getInvMass() == 0f && bodyB.getInvMass() == 0f))
            return ;
        rv = TekVector2f.sub(bodyB.velocity, bodyA.velocity);
        normalVelocity = rv.dot(normal);
        if (normalVelocity >= 0)
            return ;
        e = Float.min(bodyA.material.restitution, bodyB.material.restitution);
        j = -(1 + e) * normalVelocity;
        j /= bodyA.getInvMass() + bodyB.getInvMass();
        impulse = TekVector2f.scale(normal, j);
        massSum = bodyA.getMass() + bodyB.getMass();
        bodyA.velocity.sub(TekVector2f.scale(impulse, bodyA.getMass() / massSum));
        bodyB.velocity.add(TekVector2f.scale(impulse, bodyB.getMass() / massSum));
        correction = TekVector2f.scale(normal, Float.max(penetrationDepth - 0.001f, 0f) / (bodyA.getInvMass() + bodyB.getInvMass()) * 1f);
        bodyA.transform.position.sub(TekVector2f.scale(correction, bodyA.getInvMass()));
        bodyB.transform.position.add(TekVector2f.scale(correction, bodyB.getInvMass()));
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
