package com.tracedemrays.game;

import com.tracedemrays.game.materials.Material;
import com.tracedemrays.game.shapes.Shape;

/**
 * Created by Erik on 2018-05-18.
 */
public class Entity {
    private Material material;
    private Shape shape;

    public Material getMaterial() {
        return material;
    }

    public Shape getShape() {
        return shape;
    }


    public Entity(Shape shape, Material material){
        this.shape = shape;
        this.material = material;
    }
}
