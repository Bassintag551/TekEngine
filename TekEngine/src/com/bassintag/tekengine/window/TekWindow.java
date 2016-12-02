package com.bassintag.tekengine.window;

import com.bassintag.tekengine.objects.game.TekGame;
import com.bassintag.tekengine.utils.vector.TekVector2i;

import static org.lwjgl.glfw.GLFW.glfwCreateWindow;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * TekWindow created for TekEngine
 * Created by Antoine on 01/12/2016.
 */
public class TekWindow {

    private TekVector2i size;
    public final long   id;

    public  TekWindow(TekGame game)
    {
        this.size = game.getWindowSize();
        this.id = glfwCreateWindow(size.x, size.y, game.getWindowTitle(), NULL, NULL);
    }

    public int  getWidth()
    {
        return (size.x);
    }

    public int  getHeight()
    {
        return (size.y);
    }
}
