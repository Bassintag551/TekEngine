package com.bassintag.tekengine.object.scene;

import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.object.game.TekGame;
import com.bassintag.tekengine.object.gameobject.TekGameObject;
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

    public List<TekGameObject>  actors;

    public final String         name;

    public final TekGame        game;

    public  TekScene(TekGame game, String name)
    {
        this.game = game;
        this.name = name;
        this.actors = new ArrayList<>();
    }

    public abstract void    onLoad();

    public abstract void    onExit();

    @Override
    public void init() {
        for (TekGameObject object : actors)
        {
            object.init();
        }
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
