package com.bassintag.tekengine;

import com.bassintag.tekengine.object.game.TekGame;
import com.bassintag.tekengine.window.TekWindow;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_MULTISAMPLE;
import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * TekEngine.java created for TekEngine
 *
 * Main class of the engine, sets up the opengl window and then controls updating and rendering.
 * This class is a singleton
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public final class TekEngine {

    private static TekEngine    instance;
    private TekWindow           window;
    private TekGame             game;

    /**
     * Loads the specified TekGame into the engine.
     * This method should only be called once
     * @param game the game to be loaded
     */
    public void     setup(TekGame game)
    {
        this.game = game;
        System.out.println("Loaded project: " + game.getDisplayName());
        run();
    }

    public TekWindow    getWindow()
    {
        return (window);
    }

    private void    run()
    {
        System.out.println("LWJGL version: " + Version.getVersion() + ".");
        try {
            init();
            loop();
            glfwFreeCallbacks(window.id);
            glfwDestroyWindow(window.id);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            game.destroy();
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    private void    init()
    {
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit())
            throw new IllegalStateException("Unable to initalize GLFW");
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        glfwWindowHint(GLFW_SAMPLES, game.getMultisampling());
        window = new TekWindow(game);
        if (window.id == NULL)
            throw new RuntimeException("Failed to create the GLFW window");
        glfwSetKeyCallback(window.id, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window.id, (vidmode.width() - window.getWidth()) / 2, (vidmode.height() - window.getHeight()) / 2);
        glfwMakeContextCurrent(window.id);
        glfwSwapInterval(1);
        glfwShowWindow(window.id);
        GL.createCapabilities();
        glEnable(GL_MULTISAMPLE);
        glDisable(GL_LIGHTING);
        game.init();
        window.initInputManager();
        game.window = window;
        game.input = window.getInput();
    }

    private void    update(float delta)
    {
        game.update(delta);
    }

    private void    render()
    {
        glClear(GL_COLOR_BUFFER_BIT);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        glOrtho(game.sceneManager.getCurrentScene().camera.getMinX(), game.sceneManager.getCurrentScene().camera.getMaxX(),
                game.sceneManager.getCurrentScene().camera.getMinY(), game.sceneManager.getCurrentScene().camera.getMaxY(),
                -1.0, 1.0);
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();
        game.render(window);
        glPushMatrix();
        glMatrixMode(GL_PROJECTION);
        glPopMatrix();
        glMatrixMode(GL_MODELVIEW);
    }

    private void    loop()
    {
        double      prevTime;
        double      currentTime;

        currentTime = glfwGetTime();
        game.sceneManager.getCurrentScene().viewport.updateGLViewport();
        while (!glfwWindowShouldClose(window.id))
        {
            prevTime = currentTime;
            currentTime = glfwGetTime();
            update((float)(currentTime - prevTime));
            render();
            glfwSwapBuffers(window.id);
            glfwPollEvents();
        }
    }

    /**
     * Gets the singleton instance
     * @return the instance
     */
    public static TekEngine     getInstance()
    {
        if (instance == null)
            instance = new TekEngine();
        return (instance);
    }
}
