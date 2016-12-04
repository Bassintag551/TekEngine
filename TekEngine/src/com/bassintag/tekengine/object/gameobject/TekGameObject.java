package com.bassintag.tekengine.object.gameobject;

import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.object.gameobject.behavior.TekBehavior;
import com.bassintag.tekengine.object.scene.TekScene;
import com.bassintag.tekengine.physics.ITekPhysicsListener;
import com.bassintag.tekengine.physics.TekCollision;
import com.bassintag.tekengine.window.TekWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * TekGameObject.java created for TekEngine
 *
 * Represents an object that has a position within the game world
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public class TekGameObject extends TekObject implements ITekPhysicsListener {

    /**
     * Represents the transformation applied to this game object
     */
    public final TekTransform               transform;

    /**
     * Represents all the physics behaviors
     */
    public final List<ITekPhysicsListener>  physicsBehaviors;

    /**
     * Reference to the scene holding this game object
     */
    public TekScene                         scene;

    /**
     * Represents the behaviors of this game object
     */
    public List<TekBehavior> behaviors = new ArrayList<>();

    public TekGameObject()
    {
        this(new TekTransform());
    }

    /**
     * @param transform the transformation to be applied to this object
     */
    public  TekGameObject(TekTransform transform)
    {
        this.transform = transform;
        physicsBehaviors = new ArrayList<>();
    }

    /**
     * Gets a behavior extending the specified type held by this game object
     * @param type the searched type
     * @return the first behavior found or null if none were found
     */
    public <T extends TekBehavior> T    getBehavior(Class<T> type)
    {
        for (TekBehavior behavior : behaviors)
        {
            if (behavior.enabled && type.isAssignableFrom(behavior.getClass()))
                return ((T)behavior);
        }
        return (null);
    }

    /**
     * Gets all the behaviors extending the specified type held by this game object
     * @param type the searched type
     * @return a list of all the behaviors found
     */
    public <T extends TekBehavior> List<T>  getBehaviors(Class<T> type)
    {
        List<T>    result;

        result = new ArrayList<>();
        for (TekBehavior behavior : behaviors)
        {
            if (behavior.enabled && type.isAssignableFrom(behavior.getClass()))
                result.add((T)behavior);
        }
        return (result);
    }

    @Override
    public void init() {
        for (TekBehavior behavior : behaviors)
        {
            if (behavior.enabled)
                behavior.init();
        }
    }

    @Override
    public void update(float delta) {
        for (TekBehavior behavior : behaviors)
        {
            if (behavior.enabled)
                behavior.update(delta);
        }
    }

    @Override
    public void render(TekWindow window) {
        for (TekBehavior behavior : behaviors)
        {
            if (behavior.enabled)
                behavior.render(window);
        }
    }

    @Override
    public void destroy() {
        for (TekBehavior behavior : behaviors)
        {
            if (behavior.enabled)
                behavior.destroy();
        }
    }

    @Override
    public void onCollision(TekCollision collision)
    {
        for (ITekPhysicsListener listener : physicsBehaviors)
        {
            listener.onCollision(collision);
        }
    }
}
