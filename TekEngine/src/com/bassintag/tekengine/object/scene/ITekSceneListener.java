package com.bassintag.tekengine.object.scene;

import com.bassintag.tekengine.object.gameobject.TekGameObject;

/**
 * ITekSceneListener.java created for TekEngine
 *
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public interface ITekSceneListener {

    /**
     * Called when an actor is added to the scene
     * @param object The added actor
     */
    void    onActorAdded(TekGameObject object);

    /**
     * Called when an actor is removed from the scene
     * @param object The removed actor
     */
    void    onActorRemoved(TekGameObject object);
}
