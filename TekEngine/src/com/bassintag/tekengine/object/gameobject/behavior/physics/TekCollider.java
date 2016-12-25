package com.bassintag.tekengine.object.gameobject.behavior.physics;

import com.bassintag.tekengine.TekEngine;
import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.object.gameobject.behavior.TekBehavior;
import com.bassintag.tekengine.utils.vector.TekVector2f;
import com.bassintag.tekengine.window.TekWindow;
import org.lwjgl.opengl.GL11;

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
    public void render(TekWindow window) {
    }

    @Override
    public void         renderDebug()
    {
        TekVector2f     vertices[];

        vertices = getTransformedVertices();
        GL11.glColor3f(0.0f, 1.0f, 0.2f);
        for (int i = 0; i < vertices.length; i++)
        {
            GL11.glBegin(GL11.GL_LINE_STRIP);
            TekVector2f v1 = vertices[i];
            TekVector2f v2 = vertices[i == vertices.length - 1 ? 0 : i + 1];
            GL11.glVertex2d(v1.x, v1.y);
            GL11.glVertex2d(v2.x, v2.y);
            GL11.glEnd();
        }
    }

    @Override
    public void destroy() {}
}
