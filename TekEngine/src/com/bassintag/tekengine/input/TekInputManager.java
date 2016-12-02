package com.bassintag.tekengine.input;

import org.lwjgl.glfw.GLFWKeyCallbackI;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;


/**
 * TekInputManager.java created for TekEngine
 *
 * @author Antoine
 * @since 02/12/2016
 * @version 1.0
 */
public class TekInputManager implements GLFWKeyCallbackI{

    /**
     * Represents the current state of all keys (pressed or not)
     */
    public static boolean keys[] = new boolean[65536];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action != GLFW_RELEASE;
    }

}
