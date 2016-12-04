package com.bassintag.tekengine.object.scene;

/**
 * ITekSceneManagerListener.java created for TekEngine
 *
 * @author Antoine
 * @version 1.0
 * @since 04/12/2016
 */
public interface ITekSceneManagerListener {

    /**
     * Called when a new scene is loaded
     * @param scene the new scene
     */
    void    onLoadScene(TekScene scene);

    /**
     * Called when a scene is unloaded
     * @param scene the old scene
     */
    void    onSceneExit(TekScene scene);
}
