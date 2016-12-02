package com.bassintag.tekengine.viewport;

import com.bassintag.tekengine.utils.vector.TekVector2i;

import static org.lwjgl.opengl.GL11.glViewport;

/**
 * TekViewport.java created for TekEngine
 *
 * Represents a viewport used to render a camera on the scren
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public class TekViewport {

    private TekVector2i     worldMin;

    private TekVector2i     worldMax;

    /**
     * @param x the x position of the viewport on the window (should be 0)
     * @param y the y position of the viewport on the window (should be 0)
     * @param w the width of the viewport (should be equal to the window width)
     * @param h the height of the viewport (should be equal to the window height)
     */
    public  TekViewport(int x, int y, int w, int h)
    {
        worldMin = new TekVector2i(x, y);
        worldMax = new TekVector2i(x + w, y + h);
    }

    /**
     * Applies the viewport to openGL
     */
    public void updateGLViewport()
    {
        glViewport(worldMin.x, worldMin.y, worldMax.x, worldMax.y);
    }
}
