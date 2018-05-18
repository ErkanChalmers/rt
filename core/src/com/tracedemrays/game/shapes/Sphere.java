package com.tracedemrays.game.shapes;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.tracedemrays.game.Ray;

/**
 * Created by Erik on 5/16/2018.
 */
public class Sphere implements Shape {
    private Vector3 position;
    private float radius;

    public Sphere(Vector3 position, float radius){
        this.position = position;
        this.radius = radius;
    }

    @Override
    public float hit(Ray ray) {
        Vector3 L = position.cpy().sub(ray.origin);
        float tc = L.dot(ray.direction);
        float d = (float)Math.sqrt(L.len2() - Math.pow(tc, 2));


        if (d > radius) {
            return -1;
        }

        float t1c = (float)Math.sqrt(radius*radius - d * d);


        float t1 = tc - t1c;
        float t2 = tc + t1c;

        if (t1 < 0)
            return t2;
        return t1;
    }

    @Override
    public Vector3 getNormal(Vector3 hitPoint) {
        return hitPoint.cpy().sub(position).setLength(1);
    }
}
