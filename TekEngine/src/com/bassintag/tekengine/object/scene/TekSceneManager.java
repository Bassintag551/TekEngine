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

    public final TekGame        game;

    private List<TekScene>      scenes;

    private TekScene            currentScene;

    public  TekSceneManager(TekGame game)
    {
        this.game = game;
        this.scenes = new ArrayList<TekScene>();
    }

    public void registerScene(TekScene scene)
    {
        System.out.println("Registered new scene to Scene Manager: " + scene.name);
        this.scenes.add(scene);
    }

    public void removeScene(String name)
    {
        for (int i = 0; i < scenes.size(); i++)
            if (scenes.get(i).name.equals(name))
                scenes.remove(i--);
    }

    public TekScene getScene(String name)
    {
        for (TekScene scene : scenes)
            if (scene.name.equals(name))
                return (scene);
        return (null);
    }

    public TekScene getCurrentScene()
    {
        return (currentScene);
    }

    public void loadScene(String name)
    {
        if (currentScene != null)
            currentScene.onExit();
        currentScene = getScene(name);
        if (currentScene != null) {
            System.out.println("Loaded scene: " + currentScene.name);
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
