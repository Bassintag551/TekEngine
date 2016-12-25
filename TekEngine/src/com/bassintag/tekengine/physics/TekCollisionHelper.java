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
     * Gets the normals of a shape defined by vertices
     * @param vertices the vertices of the shape
     * @return the normals of the shape
     */
    public static TekVector2f[] getNormals(TekVector2f[] vertices)
    {
        TekVector2f[]           normals;
        TekVector2f             vector1;
        TekVector2f             vector2;
        TekVector2f             edge;
        TekVector2f             normal;

        normals = new TekVector2f[vertices.length];
        for (int i = 0; i < vertices.length; i++)
        {
            vector1 = vertices[i];
            vector2 = vertices[i + 1 == vertices.length ? 0 : i + 1];
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
            product = axis.dot(vertices[i]);
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
     * @return the collision object if both shapes intersect, null if they don't
     */
    @SuppressWarnings("Duplicates")
    public static TekCollision  doSATCollisionCheck(TekCollider collider1, TekCollider collider2)
    {
        float               overlap;
        float               o;
        TekVector2f         smallest;
        TekVector2f[]       axes1;
        TekVector2f[]       axes2;
        TekVector2f[]       vertices1;
        TekVector2f[]       vertices2;
        TekProjection1D     projection1;
        TekProjection1D     projection2;

        overlap = Float.MAX_VALUE;
        smallest = null;
        vertices1 = collider1.getTransformedVertices();
        vertices2 = collider2.getTransformedVertices();
        axes1 = getNormals(vertices1);
        for (TekVector2f axis : axes1)
        {
            projection1 = project(vertices1, axis);
            projection2 = project(vertices2, axis);
            if (!projection1.intersect(projection2))
                return (null);
            else
            {
                o = projection1.getOverlap(projection2);
                if (o < overlap)
                {
                    overlap = o;
                    if (projection1.min > projection2.min)
                        axis.scale(-1.0f);
                    smallest = axis;
                }
            }
        }
        axes2 = getNormals(vertices2);
        for (TekVector2f axis : axes2)
        {
            projection1 = project(vertices1, axis);
            projection2 = project(vertices2, axis);
            if (!projection1.intersect(projection2))
                return (null);
            else
            {
                o = projection1.getOverlap(projection2);
                if (o < overlap)
                {
                    overlap = o;
                    if (projection1.min > projection2.min)
                        axis.scale(-1.0f);
                    smallest = axis;
                }
            }
        }
        return (new TekCollision(collider1, collider2, new TekVector2f(smallest.x, smallest.y).scale(overlap)));
    }
}
