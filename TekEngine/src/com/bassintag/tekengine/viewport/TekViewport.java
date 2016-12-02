package com.bassintag.tekengine.viewport;

import com.bassintag.tekengine.utils.vector.TekVector2i;

import static org.lwjgl.opengl.GL11.glViewport;

/**
 * TekViewport.java created for TekEngine
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
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
