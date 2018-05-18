package com.tracedemrays.game;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by Erik on 5/16/2018.
 */
public class Ray {
    public final Vector3 origin, direction;
    public Ray(Vector3 origin, Vector3 direction){
        this.origin = origin;
        this.direction = direction;
    }

    @Override
    public String toString(){
        return origin+" -> "+direction;
    }
}
