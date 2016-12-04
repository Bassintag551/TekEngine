package com.bassintag.tekengine.object.gameobject.behavior.physics;

import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.object.gameobject.behavior.TekBehavior;
import com.bassintag.tekengine.object.scene.ITekSceneListener;
import com.bassintag.tekengine.physics.TekCollisionHelper;
import com.bassintag.tekengine.utils.vector.TekVector2f;
import com.bassintag.tekengine.window.TekWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * TekCollider.java created for TekEngine
 *
 * Represents a collider used to handle physics
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public class TekCollider extends TekBehavior implements ITekSceneListener{

    /**
     * Represents the vertices of this collider
     */
    public final TekVector2f[] vertices;

    private List<TekCollider> colliders;

    /**
     * @param gameObject the game object holding this collider
     * @param vertices the vertices used to create this collider
     */
    public TekCollider(TekGameObject gameObject, TekVector2f... vertices)
    {
        super(gameObject);
        this.vertices = vertices;
    }

    public TekVector2f[]    getTransformedVertices()
    {
        TekVector2f[]       result;

        result = new TekVector2f[vertices.length];
        for (int i = 0; i < vertices.length; i++)
            result[i] = transform.apply(vertices[i]);
        return (result);
    }

    @Override
    public void init()
    {
        colliders = new ArrayList<>();
        colliders.addAll(gameObject.scene.getBehaviors(TekCollider.class));
        colliders.remove(this);
        gameObject.scene.listeners.add(this);
    }

    @Override
    public void update(float delta) {
        for (TekCollider collider : colliders)
        {
            if (TekCollisionHelper.doSATCollisionCheck(this, collider))
            {
                System.out.println("COLLISION");
            }
            else
            {
                System.out.println("NO COLLISION");
            }
        }
    }

    @Override
    public void render(TekWindow window) {}

    @Override
    public void destroy() {}

    @Override
    public void     onActorAdded(TekGameObject object)
    {
        TekCollider behavior;

        if (object == this.gameObject)
            return ;
        behavior = object.getBehavior(TekCollider.class);
        if (behavior != null)
            this.colliders.add(behavior);
    }

    @Override
    public void onActorRemoved(TekGameObject object) {
        TekCollider behavior;

        behavior = object.getBehavior(TekCollider.class);
        if (behavior != null)
            this.colliders.remove(behavior);
    }
}
