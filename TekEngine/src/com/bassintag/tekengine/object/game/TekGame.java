package com.bassintag.tekengine.object.game;

import com.bassintag.tekengine.camera.TekCamera;
import com.bassintag.tekengine.input.TekInputManager;
import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.utils.vector.TekVector2i;
import com.bassintag.tekengine.viewport.TekViewport;
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
     * Represents the viewport used by the engine to render the camera
     */
    public final TekViewport    viewport;

    /**
     * Represents the camera used by the engine to render the world
     */
    public final TekCamera      camera;

    /**
     * Reference to the window holding this game
     */
    public TekWindow         window;

    /**
     * Reference to the input manager of the window holding this game
     */
    public TekInputManager   input;

    public TekGame()
    {
        camera = initCamera();
        viewport = initViewport();
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
    public TekVector2i      getWindowSize() { return (new TekVector2i(800, 600)); }

    /**
     * Gets the amount of multisampling (anti aliasing), 0 will disable it
     * @return the amount of multisampling
     */
    public int              getMultisampling() { return (4); }

    /**
     * Creates and inits the camera
     * @return the camrea
     */
    protected TekCamera     initCamera()
    {
        TekVector2i size = getWindowSize();
        return (new TekCamera(size.x / 2, size.y / 2, size.x, size.y));
    }

    /**
     * Creates and inits the viewport
     * @return the viewport
     */
    protected TekViewport   initViewport()
    {
        TekVector2i         size;

        size = getWindowSize();
        return (new TekViewport(0,0, size.x, size.y));
    }
}
