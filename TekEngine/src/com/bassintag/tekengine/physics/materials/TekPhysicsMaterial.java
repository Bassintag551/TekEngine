package com.bassintag.tekengine.physics.materials;

/**
 * TekPhysicsMaterial.java created for TekEngine
 *
 * @author Antoine
 * @version 1.0
 * @since 08/12/2016
 */
public class TekPhysicsMaterial {

    public final float density;
    public final float restitution;

    public TekPhysicsMaterial(float density, float restitution) {
        this.density = density;
        this.restitution = restitution;
    }

    public static final TekPhysicsMaterial DEFAULT = new TekPhysicsMaterial(1.0f, 0.0f);
    public static final TekPhysicsMaterial ROCK = new TekPhysicsMaterial(0.6f, 0.1f);
    public static final TekPhysicsMaterial WOOD = new TekPhysicsMaterial(0.3f, 0.2f);
    public static final TekPhysicsMaterial METAL = new TekPhysicsMaterial(1.2f, 0.05f);
}
