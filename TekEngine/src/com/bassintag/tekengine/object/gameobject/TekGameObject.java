package com.bassintag.tekengine.object.gameobject;

import com.bassintag.tekengine.object.TekObject;
import com.bassintag.tekengine.utils.vector.TekVector2f;

/**
 * TekGameObject.java created for TekEngine
 * @author Antoine
 * @since 01/12/2016
 * @version 1.0
 */
public abstract class TekGameObject extends TekObject{

    public TekVector2f      pos;
    public TekVector2f      size;
    public TekVector2f      scale;
}
