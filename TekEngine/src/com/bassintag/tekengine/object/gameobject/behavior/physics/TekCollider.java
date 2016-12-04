package com.bassintag.tekengine.object.gameobject.behavior.physics;

import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.object.gameobject.behavior.TekBehavior;
import com.bassintag.tekengine.utils.vector.TekVector2f;
import com.bassintag.tekengine.window.TekWindow;

/**
 * TekCollider.java created for TekEngine
 *
 * Represents a collider used to handle physics
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public class TekCollider extends TekBehavior {

    /**
     * Represents the vertices of this collider
     */
    public final TekVector2f[] vertices;

    /**
     * @param gameObject the game object holding this collider
     * @param vertices the vertices used to create this collider
     */
    public TekCollider(TekGameObject gameObject, TekVector2f... vertices)
    {
        super(gameObject);
        this.vertices = vertices;
    }

    /**
     * Gets the vertices with applied transformation from the transform matrix
     * @return the transformed vertices
     */
    public TekVector2f[]    getTransformedVertices()
    {
        TekVector2f[]       result;

        result = new TekVector2f[vertices.length];
        for (int i = 0; i < vertices.length; i++)
            result[i] = transform.apply(vertices[i]);
        return (result);
    }


    @Override
    public void init() {}

    @Override
    public void update(float delta) {}

    @Override
    public void render(TekWindow window) {}

    @Override
    public void destroy() {}
}
