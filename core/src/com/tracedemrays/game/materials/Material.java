package com.tracedemrays.game.materials;

import com.badlogic.gdx.graphics.Color;
/**
 * Created by Erik on 2018-05-18.
 */
public class Material {
    Color diffuse;
    Color specular;

    public Color getDiffuse() {
        return diffuse;
    }

    public Color getSpecular() {
        return specular;
    }

    public Material(Color diffuse, Color specular){
        this.diffuse = diffuse;
        this.specular = specular;
    }


}
