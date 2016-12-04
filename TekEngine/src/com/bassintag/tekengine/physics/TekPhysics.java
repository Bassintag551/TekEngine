package com.bassintag.tekengine.physics;

import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.object.game.TekGame;
import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.object.gameobject.behavior.physics.TekCollider;
import com.bassintag.tekengine.object.scene.ITekSceneListener;
import com.bassintag.tekengine.object.scene.ITekSceneManagerListener;
import com.bassintag.tekengine.object.scene.TekScene;
import com.bassintag.tekengine.window.TekWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * TekPhysics.java created for TekEngine
 *
 * Represents the physics engine handling all collisions and physic issues
 * @author Antoine
 * @version 1.0
 * @since 04/12/2016
 */
public class TekPhysics extends TekObject implements ITekSceneListener, ITekSceneManagerListener{

    private final List<TekCollider>         colliders;

    /**
     * Represents an instance of the game holding this physics engine
     */
    public final TekGame            game;

    public TekPhysics(TekGame game)
    {
        this.game = game;
        this.colliders = new ArrayList<>();
        game.sceneManager.listeners.add(this);
    }

    private List<TekCollision>       checkCollisions()
    {
        List<TekCollision>          result;
        TekCollision                collision;
        TekCollider                 collider1;
        TekCollider                 collider2;

        result = new ArrayList<>();
        for (int i = 0; i < colliders.size() - 1; i++)
        {
            for (int j = i + 1; j < colliders.size(); j++)
            {
                collider1 = colliders.get(i);
                collider2 = colliders.get(j);
                if ((collision = TekCollisionHelper.doSATCollisionCheck(collider1, collider2)) != null)
                {
                    collider1.gameObject.onCollision(collision);
                    collider2.gameObject.onCollision(collision.getInverted());
                    result.add(collision);
                }
            }
        }
        return (result);
    }

    @Override
    public void onActorAdded(TekGameObject object)
    {
        TekCollider behavior;

        behavior = object.getBehavior(TekCollider.class);
        if (behavior != null)
            this.colliders.add(behavior);
    }

    @Override
    public void onActorRemoved(TekGameObject object)
    {
        TekCollider behavior;

        behavior = object.getBehavior(TekCollider.class);
        if (behavior != null)
            this.colliders.remove(behavior);
    }

    @Override
    public void onLoadScene(TekScene scene)
    {
        scene.listeners.add(this);
        colliders.addAll(scene.getBehaviors(TekCollider.class));
    }

    @Override
    public void onSceneExit(TekScene scene)
    {
        scene.listeners.remove(this);
        colliders.clear();
    }

    @Override
    public void init()
    {
    }

    @Override
    public void update(float delta)
    {
        checkCollisions();
    }

    @Override
    public void render(TekWindow window)
    {

    }

    @Override
    public void destroy()
    {

    }
}
