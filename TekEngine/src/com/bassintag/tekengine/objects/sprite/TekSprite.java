package com.bassintag.tekengine.objects.sprite;

import com.bassintag.tekengine.objects.TekObject;
import com.bassintag.tekengine.texture.TekTexture;
import com.bassintag.tekengine.utils.vector.TekVector2f;
import com.bassintag.tekengine.window.TekWindow;

/**
 * TekSprite created for TekEngine
 * Created by Antoine on 01/12/2016.
 */
public class TekSprite extends TekObject{

    public TekVector2f      pos;
    public TekVector2f      size;
    public TekVector2f      scale;
    public float            rotation = 0;
    public boolean          flipX = false;
    public boolean          flipY = false;

    protected TekTexture    texture;

    public  TekSprite(TekTexture texture)
    {
        this(0, 0, 1, 1, texture);
    }

    public  TekSprite(float x, float y, TekTexture texture)
    {
        this(x, y, 1, 1, texture);
    }

    public  TekSprite(float x, float y, float sx, float sy, TekTexture texture)
    {
        this.pos = new TekVector2f(x, y);
        this.scale = new TekVector2f(sx, sy);
        this.texture = texture;
        size = new TekVector2f(texture.width, texture.height);
    }

    public boolean  collide(TekSprite sprite)
    {
        if (pos.x + size.x * scale.x > sprite.pos.x &&
                pos.x < sprite.pos.x + sprite.size.x * sprite.scale.x &&
                pos.y < sprite.pos.y + sprite.size.y * sprite.scale.y &&
                pos.y + size.y * scale.y > sprite.pos.y)
            return (true);
        return (false);
    }

    @Override
    public void     init() {}

    @Override
    public void     update(float delta) {}

    @Override
    public void     render(TekWindow window)
    {
        texture.draw(pos.x, pos.y, size.x * scale.x, size.y * scale.y, rotation, flipX, flipY);
    }

    @Override
    public void     destroy()
    {
        texture.destroy();
    }
}
