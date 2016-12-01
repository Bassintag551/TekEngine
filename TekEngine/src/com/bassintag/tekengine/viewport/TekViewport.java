package com.bassintag.tekengine.viewport;

import com.bassintag.tekengine.utils.vector.TekVector2i;

import static org.lwjgl.opengl.GL11.glViewport;

/**
 * TekViewport created for TekEngine
 * Created by Antoine on 01/12/2016.
 */
public class TekViewport {

    private TekVector2i     worldMin;
    private TekVector2i worldMax;

    public  TekViewport(int x, int y, int w, int h)
    {
        worldMin = new TekVector2i(x, y);
        worldMax = new TekVector2i(x + w, y + h);
    }

    public void updateGLViewport()
    {
        glViewport(worldMin.x, worldMin.y, worldMax.x, worldMax.y);
    }
}
