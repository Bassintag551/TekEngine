package com.bassintag.tekengine.object;

import com.bassintag.tekengine.window.TekWindow;

/**
 * TekObject.java created for TekEngine
 *
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public abstract class TekObject {

    /**
     * Inits the object
     */
    public abstract void    init();

    /**
     * Updates the object
     * @param delta the time elapsed between the previous frame and the current frame
     */
    public abstract void    update(float delta);

    /**
     * Renders the object
     * @param window the window the game is being rendered on
     */
    public abstract void    render(TekWindow window);

    /**
     * Destroys the object
     */
    public abstract void    destroy();
}
