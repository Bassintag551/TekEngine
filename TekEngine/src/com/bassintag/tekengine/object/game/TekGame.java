package com.bassintag.tekengine.object.game;

import com.bassintag.tekengine.input.TekInputManager;
import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.object.scene.TekSceneManager;
import com.bassintag.tekengine.physics.TekPhysics;
import com.bassintag.tekengine.utils.vector.TekVector2i;
import com.bassintag.tekengine.window.TekWindow;


/**
 * TekGame.java created for TekEngine
 *
 * Represents a game that can be loaded by the engine
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public abstract class TekGame extends TekObject{

    /**
     * Represents the manager of this game scenes
     */
    public final TekSceneManager    sceneManager;

    /**
     * Represents the physics engine of this game
     */
    public final TekPhysics         physics;

    /**
     * Reference to the window holding this game
     */
    public TekWindow                window;

    /**
     * Reference to the input manager of the window holding this game
     */
    public TekInputManager          input;

    public TekGame()
    {
        sceneManager = new TekSceneManager(this);
        physics = new TekPhysics(this);
    }

    /**
     * Whether or not debug features should be enabled
     * @return true to enable debug otherwise false
     */
    public boolean debug()
    {
        return (false);
    }

    /**
     * Gets the name of the game used for display
     * @return the display name of the game
     */
    public abstract String  getDisplayName();

    /**
     * Gets the title of the window containing the game
     * @return The title of the window
     */
    public String           getWindowTitle() { return ("TekEngine Game"); }

    /**
     * Gets the initial size of the window
     * @return the size of the window
     */
    public TekVector2i      getWindowSize() { return (new TekVector2i(1200, 675)); }

    /**
     * Gets the amount of multisampling (anti aliasing), 0 will disable it
     * @return the amount of multisampling
     */
    public int              getMultisampling() { return (4); }

    @Override
    public void update(float delta)
    {
        sceneManager.update(delta);
        physics.update(delta);
    }

    @Override
    public void render(TekWindow window)
    {
        sceneManager.render(window);
    }
}
