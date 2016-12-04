package com.bassintag.tekengine.physics;

import com.bassintag.tekengine.object.gameobject.behavior.physics.TekCollider;
import com.bassintag.tekengine.utils.vector.TekVector2f;

/**
 * TekCollisionHelper.java created for TekEngine
 *
 * Class containing utils methods used to check collisions between objects
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public class TekCollisionHelper {

    /**
     * Gets the normals of a given collider
     * @param collider the collider
     * @return the normals of the collider
     */
    public static TekVector2f[] getNormals(TekCollider collider)
    {
        TekVector2f[]           normals;
        TekVector2f             vector1;
        TekVector2f             vector2;
        TekVector2f             edge;
        TekVector2f             normal;

        normals = new TekVector2f[collider.vertices.length];
        for (int i = 0; i < collider.vertices.length; i++)
        {
            vector1 = collider.vertices[i];
            vector2 = collider.vertices[i + 1 == collider.vertices.length ? 0 : i + 1];
            edge = TekVector2f.sub(vector1, vector2);
            normal = edge.getPerpendicular();
            normals[i] = normal;
        }
        return(normals);
    }

    /**
     * Project a shape defined by vertices along a given axis
     * @param vertices the vertices of the shape
     * @param axis the axis
     * @return the projection
     */
    public static TekProjection1D   project(TekVector2f vertices[], TekVector2f axis)
    {
        float                       min;
        float                       max;
        float                       product;

        min = axis.dot(vertices[0]);
        max = min;
        for (int i = 1; i < vertices.length; i++)
        {
            product = axis.dot(vertices[0]);
            if (product < min)
                min = product;
            else if (product > max)
                max = product;
        }
        return (new TekProjection1D(min , max));
    }

    /**
     * Checks whether or not two colliders intersects using Separating Axis Theorem (SAT)
     * @param collider1 the first collider
     * @param collider2 the second collider
     * @return true if both shapes intersect, false if they don't
     */
    @SuppressWarnings("Duplicates")
    public static boolean   doSATCollisionCheck(TekCollider collider1, TekCollider collider2)
    {
        TekVector2f[]       axes1;
        TekVector2f[]       axes2;
        TekVector2f[]       vertices1;
        TekVector2f[]       vertices2;
        TekProjection1D     projection1;
        TekProjection1D     projection2;

        axes1 = getNormals(collider1);
        vertices1 = collider1.getTransformedVertices();
        vertices2 = collider2.getTransformedVertices();
        for (TekVector2f axis : axes1)
        {
            projection1 = project(vertices1, axis);
            projection2 = project(vertices2, axis);

            if (!projection1.intersect(projection2))
                return (false);
        }
        axes2 = getNormals(collider2);
        for (TekVector2f axis : axes2)
        {
            projection1 = project(vertices1, axis);
            projection2 = project(vertices2, axis);

            if (!projection1.intersect(projection2))
                return (false);
        }
        return (true);
    }
}
