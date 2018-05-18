package com.tracedemrays.game.shapes;

import com.badlogic.gdx.math.Vector3;
import com.tracedemrays.game.Ray;

/**
 * Created by Erik on 5/16/2018.
 */
public interface Shape {
    float hit(Ray ray);
    Vector3 getNormal(Vector3 hitPoint);
}
