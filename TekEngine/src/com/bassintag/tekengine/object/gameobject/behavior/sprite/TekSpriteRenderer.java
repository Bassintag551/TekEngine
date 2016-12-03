package com.bassintag.tekengine.object.gameobject.behavior.sprite;

import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.object.gameobject.behavior.TekBehavior;
import com.bassintag.tekengine.texture.TekTexture;
import com.bassintag.tekengine.utils.vector.TekVector2f;
import com.bassintag.tekengine.window.TekWindow;

/**
 * TekSpriteRenderer.java created for TekEngine
 *
 * Represents an image that can move, rotate and scale within the world
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public class TekSpriteRenderer extends TekBehavior {

    /**
     * Represents the size of the game object
     */
    public TekVector2f      size;

    /**
     * If true the texture will be flipped horizontally
     */
    public boolean          flipX = false;

    /**
     * If true the texture will be flipped vertically
     */
    public boolean          flipY = false;

    /**
     * Represents the texture being rendered on the sprite
     */
    protected TekTexture    texture;

    /**
     * @param gameObject the game object holding this sprite renderer behavior
     */
    public TekSpriteRenderer(TekGameObject gameObject) {
        super(gameObject);
        size = new TekVector2f(0.0f, 0.0f);
    }

    public TekSpriteRenderer(TekGameObject gameObject, TekTexture texture)
    {
        this(gameObject);
        this.texture = texture;
        this.size.x = texture.width;
        this.size.y = texture.height;
    }

    @Override
    public void     init() {
    }

    @Override
    public void     update(float delta) {}

    @Override
    public void     render(TekWindow window)
    {
        texture.draw(transform.position.x, transform.position.y, size.x * transform.scale.x, size.y * transform.scale.y, transform.rotation, flipX, flipY);
    }

    @Override
    public void     destroy()
    {
        texture.destroy();
    }
}
