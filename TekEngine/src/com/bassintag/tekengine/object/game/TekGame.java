package com.bassintag.tekengine.object.game;

import com.bassintag.tekengine.camera.TekCamera;
import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.utils.vector.TekVector2i;
import com.bassintag.tekengine.viewport.TekViewport;


/**
 * TekGame.java created for TekEngine
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public abstract class TekGame extends TekObject{

    public final TekViewport    viewport;

    public final TekCamera      camera;

    public TekGame()
    {
        camera = initCamera();
        viewport = initViewport();
    }

    public abstract String  getDisplayName();

    public String           getWindowTitle() { return ("TekEngine Game"); }

    public TekVector2i      getWindowSize() { return (new TekVector2i(800, 600)); }

    public int              getMultisampling() { return (4); }

    protected TekCamera     initCamera()
    {
        TekVector2i size = getWindowSize();
        return (new TekCamera(size.x / 2, size.y / 2, size.x, size.y));
    }

    protected TekViewport   initViewport()
    {
        TekVector2i         size;

        size = getWindowSize();
        return (new TekViewport(0,0, size.x, size.y));
    }
}
