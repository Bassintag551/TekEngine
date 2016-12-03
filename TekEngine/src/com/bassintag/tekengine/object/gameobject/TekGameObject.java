package com.bassintag.tekengine.object.gameobject;

import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.object.gameobject.behavior.TekBehavior;
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
public class TekGameObject extends TekObject{

    /**
     * Represents the transformation applied to this game object
     */
    public final TekTransform  transform;

    /**
     * Represents the behaviors of this game object
     */
    public List<TekBehavior> behaviors = new ArrayList<TekBehavior>();

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
    }

    /**
     * Gets a behavior extending the specified type held by this game object
     * @param type the searched type
     * @return the first behavior found or null if none were found
     */
    public TekBehavior      getBehavior(Class<TekBehavior> type)
    {
        for (TekBehavior behavior : behaviors)
        {
            if (behavior.getClass().isAssignableFrom(type))
                return (behavior);
        }
        return (null);
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
}
