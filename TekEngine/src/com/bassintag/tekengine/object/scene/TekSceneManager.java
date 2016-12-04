package com.bassintag.tekengine.object.scene;

import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.object.game.TekGame;
import com.bassintag.tekengine.window.TekWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * TekSceneManager.java created for TekEngine
 *
 * Represents the manager holding all scenes of a game
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public class TekSceneManager extends TekObject {

    /**
     * Represents the game holding this scene manager
     */
    public final TekGame        game;

    /**
     * Represents the listeners of this scene
     */
    public final List<ITekSceneManagerListener> listeners;

    /**
     * Represents all the scenes registered in this scene manager
     */
    private List<TekScene>      scenes;

    /**
     * Represents the scene currently loaded by the scene manager
     */
    private TekScene            currentScene;

    /**
     * @param game the game holding this scene manager
     */
    public  TekSceneManager(TekGame game)
    {
        this.game = game;
        this.scenes = new ArrayList<>();
        this.listeners = new ArrayList<>();
    }

    /**
     * Registers a scene to this scene manager
     * @param scene the scene to be registered
     */
    public void registerScene(TekScene scene)
    {
        System.out.println("Registered new scene to Scene Manager: " + scene.name);
        this.scenes.add(scene);
    }

    /**
     * Removes a scene from this scene manager
     * @param name the name of the scene to be removed
     */
    public void removeScene(String name)
    {
        for (int i = 0; i < scenes.size(); i++)
            if (scenes.get(i).name.equals(name))
                scenes.remove(i--);
    }

    /**
     * Gets a scene by it's name
     * @param name the name of the scene to be found
     * @return the scene or null if it wasn't found
     */
    public TekScene getScene(String name)
    {
        for (TekScene scene : scenes)
            if (scene.name.equals(name))
                return (scene);
        return (null);
    }

    /**
     * Gets the currently loaded scene
     * @return the loaded scene
     */
    public TekScene getCurrentScene()
    {
        return (currentScene);
    }

    /**
     * Load a registered scene and unload the one currently loaded one
     * @param name the name of the scene to be loaded
     */
    public void loadScene(String name)
    {
        if (currentScene != null)
        {
            for (ITekSceneManagerListener lisener : listeners)
                lisener.onSceneExit(currentScene);
            currentScene.onExit();
        }
        currentScene = getScene(name);
        if (currentScene != null) {
            System.out.println("Loaded scene: " + currentScene.name);
            for (ITekSceneManagerListener lisener : listeners)
                lisener.onLoadScene(currentScene);
            currentScene.onLoad();
        }
    }

    @Override
    public void init() {
        for (TekScene scene : scenes)
            scene.init();
    }

    @Override
    public void update(float delta) {
        if (currentScene != null)
            currentScene.update(delta);
    }

    @Override
    public void render(TekWindow window) {
        if (currentScene != null)
            currentScene.render(window);
    }

    @Override
    public void destroy() {
        for (TekScene scene : scenes)
            scene.destroy();
    }
}
