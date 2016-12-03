package com.bassintag.tekengine.window;

import com.bassintag.tekengine.input.TekInputManager;
import com.bassintag.tekengine.object.game.TekGame;
import com.bassintag.tekengine.utils.vector.TekVector2i;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * TekWindow.java created for TekEngine
 *
 * Represents the window
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public class TekWindow {

    private TekVector2i size;

    /**
     * Represents the id of the window
     */
    public final long               id;

    private TekInputManager         input;

    /**
     * @param game the game that should be contained by the window
     */
    public  TekWindow(TekGame game)
    {
        this.size = game.getWindowSize();
        this.id = glfwCreateWindow(size.x, size.y, game.getWindowTitle(), NULL, NULL);
    }

    /**
     * Gets the width of the window
     * @return the width of the window
     */
    public int  getWidth()
    {
        return (size.x);
    }

    /**
     * Gets the height of the window
     * @return the height of the window
     */
    public int  getHeight()
    {
        return (size.y);
    }

    /**
     * Inits the input manager.
     * Should only be called by the engine
     */
    public void initInputManager()
    {
        if (this.input == null)
            this.input = new TekInputManager(this);
    }

    /**
     * Gets the input manager
     * @return the input manager
     */
    public TekInputManager  getInput()
    {
        return (input);
    }
}
