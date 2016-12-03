package com.bassintag.tekengine.input;

import com.bassintag.tekengine.window.TekWindow;

/**
 * TekInputManager.java created for TekEngine
 *
 * Represents a manager that holds the current state of the keyboard and mouse buttons.
 * @author Antoine
 * @since 02/12/2016
 * @version 1.0
 */
public class TekInputManager{

    private final TekKeyboardInputManager   keyboard;
    private final TekMouseInputManager      mouse;

    /**
     * @param window the window from which this manager should hold the input states
     */
    public TekInputManager(TekWindow window)
    {
        this.keyboard = new TekKeyboardInputManager(window);
        this.mouse = new TekMouseInputManager(window);
    }

    /**
     * Checks if a key is being pressed
     * @param key the key to check
     * @return whether or not the key is being pressed
     */
    public boolean isKeyDown(int key)
    {
        return (keyboard.keys[key]);
    }

    /**
     * Checks if a key is not being pressed
     * @param key the key to check
     * @return whether or not the key is not being pressed
     */
    public boolean isKeyUp(int key)
    {
        return (!keyboard.keys[key]);
    }

    /**
     * Checks if a mouse button is being pressed
     * @param button the button to checl
     * @return whether or not the button is being pressed
     */
    public boolean  isMouseButtonDown(int button)
    {
        return (mouse.buttons[button]);
    }

    /**
     * Checks if a mouse button is not being pressed
     * @param button the button to checl
     * @return whether or not the button is not being pressed
     */
    public boolean  isMouseButtonUp(int button)
    {
        return (!mouse.buttons[button]);
    }
}
