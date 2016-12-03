package com.bassintag.tekengine.input;

import com.bassintag.tekengine.window.TekWindow;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;

/**
 * TekMouseInputManager.java created for TekEngine
 *
 * @author Antoine
 * @version 1.0
 * @since 02/12/2016
 */
public class TekMouseInputManager extends GLFWMouseButtonCallback {

    /**
     * Represents the current state of all keys (pressed or not)
     */
    protected boolean   buttons[] = new boolean[8];

    protected   TekMouseInputManager(TekWindow window)
    {
        System.out.println("Started mouse input manager");
        glfwSetMouseButtonCallback(window.id, this);
    }

    @Override
    public void invoke(long window, int button, int action, int mods)
    {
        buttons[button] = action != GLFW_RELEASE;
    }
}
