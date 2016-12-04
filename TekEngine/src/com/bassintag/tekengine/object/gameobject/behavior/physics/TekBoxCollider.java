package com.bassintag.tekengine.object.gameobject.behavior.physics;

import com.bassintag.tekengine.object.gameobject.TekGameObject;
import com.bassintag.tekengine.utils.vector.TekVector2f;

/**
 * TekBoxCollider.java created for TekEngine
 *
 * Represents a box shaped collider
 * @author Antoine
 * @version 1.0
 * @since 03/12/2016
 */
public class TekBoxCollider extends TekCollider{

    /**
     * @param gameObject The game object holding this collider
     * @param width the width of the box collider
     * @param height the height of the box collider
     */
    public TekBoxCollider(TekGameObject gameObject, float width, float height) {
        this(gameObject, width, height, 0, 0);
    }

    /**
     * @param gameObject The game object holding this collider
     * @param width the width of the box collider
     * @param height the height of the box collider
     * @param offX the offset of the box collider on the x axis
     * @param offY the offset of the box collider on the y axis
     */
    public TekBoxCollider(TekGameObject gameObject, float width, float height, int offX, int offY) {
        super(gameObject,
                new TekVector2f(offX - width / 2, offY - height / 2),
                new TekVector2f(offX + width / 2, offY - height / 2),
                new TekVector2f(offX + width / 2, offY + height / 2),
                new TekVector2f(offX - width / 2, offY + height / 2));
    }

}
