package com.bassintag.tekengine.objects;

import com.bassintag.tekengine.window.TekWindow;

/**
 * TekObject created for TekEngine
 * Created by Antoine on 01/12/2016.
 */
public abstract class TekObject {

    public abstract void    init();

    public abstract void    update(float delta);

    public abstract void    render(TekWindow window);

    public abstract void    destroy();
}
