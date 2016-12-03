package com.bassintag.tekengine.input;

import com.bassintag.tekengine.window.TekWindow;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;

/**
 * TekKeyboardInputManager.java created for TekEngine
 *
 * @author Antoine
 * @version 1.0
 * @since 02/12/2016
 */
public class TekKeyboardInputManager extends GLFWKeyCallback{

    /**
     * Represents the current state of all keys (pressed or not)
     */
    protected boolean   keys[] = new boolean[65536];

    /**
     * @param window the window from which this manager should capture the input
     */
    protected TekKeyboardInputManager(TekWindow window)
    {
        System.out.println("Started keyboard input manager");
        glfwSetKeyCallback(window.id, this);
    }

    @Override
    public void     invoke(long window, int key, int scancode, int action, int mods)
    {
        keys[key] = action != GLFW_RELEASE;
    }
}
