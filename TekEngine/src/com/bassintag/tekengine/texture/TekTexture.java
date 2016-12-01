package com.bassintag.tekengine.texture;

import com.bassintag.tekengine.utils.vector.TekVector2f;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;

/**
 * TekTexture created for TekEngine
 * Created by Antoine on 01/12/2016.
 */
public class TekTexture {

    public static final int BYTES_PER_PIXELS = 4;

    public final int            width;
    public final int            height;
    private final ByteBuffer    buffer;
    private int                 id = -1;
    private int[]               pixels;

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

    public void     draw(float x, float y)
    {
        draw(x, y, width, height);
    }

    public void     draw(float x, float y, float w, float h)
    {
        draw(x, y, w, h, false, false);
    }

    public void     draw(float x, float y, float w, float h, boolean flipX, boolean flipY)
    {
        draw(x, y, w, h, 0.0f, flipX, flipY);
    }

    public void         draw(float x, float y, float w, float h, float angle, boolean flipX, boolean flipY)
    {
        glPushMatrix();
        glColor3f(1,1,1);
        glTranslatef(x, y, 0.0f);
        glRotatef(angle, 0.0f, 0.0f, 1.0f);
        glEnable(GL_TEXTURE_2D);
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

    public int      getID()
    {
        return (id);
    }

    public void     destroy()
    {
        glDeleteTextures(id);
    }
}
