package com.bassintag.tekengine.object;

import com.bassintag.tekengine.window.TekWindow;

/**
 * TekObject.java created for TekEngine
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public abstract class TekObject {

    public abstract void    init();

    public abstract void    update(float delta);

    public abstract void    render(TekWindow window);

    public abstract void    destroy();
}
