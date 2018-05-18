package com.tracedemrays.game.shapes;

import com.badlogic.gdx.math.Vector3;
import com.tracedemrays.game.Entity;
import com.tracedemrays.game.Environment;
import com.tracedemrays.game.Ray;

/**
 * Created by Erik on 2018-05-18.
 */
public class Plane implements Shape {
    Vector3 position, normal;

    public Plane(Vector3 position, Vector3 normal){
        this.position = position;
        this.normal = normal;
    }

    @Override
    public float hit(Ray ray) {
        float dist = position.cpy().sub(ray.origin).dot(normal) / ray.direction.cpy().dot(normal);
        return dist;
    }

    @Override
    public Vector3 getNormal(Vector3 hitPoint) {
        return normal;
    }
}
