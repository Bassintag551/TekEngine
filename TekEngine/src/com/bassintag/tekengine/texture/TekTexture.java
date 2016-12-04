package com.bassintag.tekengine.texture;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

/**
 * TekTexture.java created for TekEngine
 * Represents a texture that can be drawn on the screen
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public class TekTexture {

    /**
     * Represents the number of bytes used to store the data of one pixel.
     */
    public static final int BYTES_PER_PIXELS = 4;

    /**
     * Represents the width of the texture
     */
    public final int            width;

    /**
     * Represents the height of the texture
     */
    public final int            height;

    private final ByteBuffer    buffer;
    private int                 id = -1;
    private int[]               pixels;

    /**
     * @param path The path of the texture to load
     */
    public              TekTexture(String path)
    {
        BufferedImage   image = null;

        try {
            image = ImageIO.read(new File(path));
        } catch(IOException exception) {
            System.err.println("Couldn't load texture: " + path);
        }
        if (image != null)
        {
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        }
        else
        {
            width = 0;
            height = 0;
        }
        buffer = BufferUtils.createByteBuffer(width * height * BYTES_PER_PIXELS);
        updateTexture();
    }

    /**
     * Refresh the texture and store it in memory
     */
    public void     updateTexture()
    {
        int         pixel;

        if (id != -1)
            glDeleteTextures(id);
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                pixel = pixels[y * width + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));
                buffer.put((byte) ((pixel >> 8) & 0xFF));
                buffer.put((byte) (pixel & 0xFF));
                buffer.put((byte) ((pixel >> 24) & 0xFF));
            }
        }
        buffer.flip();
        id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
    }

    /**
     * Draws the pixel on the screen at a given position
     * @param x the x position where the image should be rendered
     * @param y the y position where the image should be rendered
     */
    public void     draw(float x, float y)
    {
        draw(x, y, width, height);
    }

    /**
     * Draws the pixel on the screen at a given position and with a given size
     * @param x the x position where the image should be rendered
     * @param y the y position where the image should be rendered
     * @param w the width of the drawn image
     * @param h the height of the drawn image
     */
    public void     draw(float x, float y, float w, float h)
    {
        draw(x, y, w, h, false, false);
    }

    /**
     * Draws the pixel on the screen at a given position, with a given size and flipped or not
     * @param x the x position where the image should be rendered
     * @param y the y position where the image should be rendered
     * @param w the width of the drawn image
     * @param h the height of the drawn image
     * @param flipX whether or not the image should be flipped horizontally
     * @param flipY whether or not the image should be flipped vertically
     */
    public void     draw(float x, float y, float w, float h, boolean flipX, boolean flipY)
    {
        draw(x, y, w, h, 0.0f, flipX, flipY);
    }

    /**
     * Draws the pixel on the screen at a given position, with a given size and a given rotation, and flipped or not
     * @param x the x position where the image should be rendered
     * @param y the y position where the image should be rendered
     * @param w the width of the drawn image
     * @param h the height of the drawn image
     * @param angle the rotation the image should be rendered with
     * @param flipX whether or not the image should be flipped horizontally
     * @param flipY whether or not the image should be flipped vertically
     */
    public void     draw(float x, float y, float w, float h, float angle, boolean flipX, boolean flipY)
    {
        glPushMatrix();
        glColor3f(1,1,1);
        glTranslatef(x, y, 0.0f);
        glRotatef(angle, 0.0f, 0.0f, 1.0f);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glBindTexture(GL_TEXTURE_2D, id);
        glBegin(GL_QUADS);
        glTexCoord2f(flipX ? 1 : 0, flipY ? 0 : 1);
        glVertex3f(-w / 2, -h / 2, 0);
        glTexCoord2f(flipX ? 0 : 1, flipY ? 0 : 1);
        glVertex3f(w / 2, -h / 2, 0);
        glTexCoord2f(flipX ? 0 : 1, flipY ? 1 : 0);
        glVertex3f(w / 2, h / 2, 0);
        glTexCoord2f(flipX ? 1 : 0, flipY ? 1 : 0);
        glVertex3f(-w / 2, h / 2, 0);
        glEnd();
        glPopMatrix();
        glDisable(GL_TEXTURE_2D);
    }

    /**
     * Gets the ID of the texture
     * @return the id of the texture
     */
    public int      getID()
    {
        return (id);
    }

    /**
     * Frees the texture from memory
     */
    public void     destroy()
    {
        glDeleteTextures(id);
    }
}
