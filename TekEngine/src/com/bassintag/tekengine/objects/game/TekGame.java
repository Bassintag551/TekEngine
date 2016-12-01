package com.bassintag.tekengine.objects.game;

import com.bassintag.tekengine.objects.TekObject;
import com.bassintag.tekengine.utils.vector.TekVector2i;
import com.bassintag.tekengine.viewport.TekViewport;

/**
 * TekGame created for TekEngine
 * Created by Antoine on 01/12/2016.
 */
public abstract class TekGame extends TekObject{

    public abstract String  getDisplayName();

    public String           getWindowTitle() { return ("TekEngine Game"); }

    public TekVector2i      getWindowSize() { return (new TekVector2i(800, 600)); }

    public TekViewport      getViewport()
    {
        TekVector2i         size;

        size = getWindowSize();
        return (new TekViewport(0,0, size.x, size.y));
    };
}
