package com.bassintag.tekengine.object.scene;

import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.object.game.TekGame;
import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.object.gameobject.behavior.TekBehavior;
import com.bassintag.tekengine.window.TekWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * TekScene.java created for TekEngine
 *
 * Represents a game scene, for example a menu or an ingame scene
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public abstract class TekScene extends TekObject {

    private List<TekGameObject>     actors;

    /**
     * Represents the listeners of this scene
     */
    public final List<ITekSceneListener> listeners;

    /**
     * Represents the name used to identify this scene
     */
    public final String             name;

    /**
     * Represents the game holding this scene
     */
    public final TekGame            game;

    /**
     * @param game the game holding this scene
     * @param name the name of this scene
     */
    public  TekScene(TekGame game, String name)
    {
        this.game = game;
        this.name = name;
        this.actors = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    /**
     * Called when the game is loaded by the scene manager
     */
    public abstract void    onLoad();

    /**
     * Called when this scene is unloaded by the scene manager
     */
    public abstract void    onExit();

    /**
     * Adds an actor to the scene
     * @param object the actor
     */
    public void addActor(TekGameObject object)
    {
        this.actors.add(object);
        object.scene = this;
        object.init();
        for (ITekSceneListener listener : listeners)
            listener.onActorAdded(object);
    }

    /**
     * Removes an actor from the scene
     * @param object the actor
     */
    public void removeActor(TekGameObject object)
    {
        this.actors.remove(object);
        object.scene = null;
        object.destroy();
        for (ITekSceneListener listener : listeners)
            listener.onActorRemoved(object);
    }

    public List<TekGameObject>  getActorsFromBehavior(Class<? extends TekBehavior> behaviorClass)
    {
        List<TekGameObject> result;

        result = new ArrayList<>();
        for (TekGameObject object : actors)
            if (object.getBehavior(behaviorClass) != null)
                result.add(object);
        return (result);
    }

    public <T extends TekBehavior> List<T>  getBehaviors(Class<T> type)
    {
        List<T>    result;

        result = new ArrayList<>();
        for (TekGameObject object : actors)
            result.addAll(object.getBehaviors(type));
        return (result);
    }

    @Override
    public void init() {
    }

    @Override
    public void update(float delta) {
        for (TekGameObject object : actors)
        {
            object.update(delta);
        }
    }

    @Override
    public void render(TekWindow window) {
        for (TekGameObject object : actors)
        {
            object.render(window);
        }
    }

    @Override
    public void destroy() {
        for (TekGameObject object : actors)
        {
            object.destroy();
        }
    }
}

