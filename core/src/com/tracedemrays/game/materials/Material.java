package com.tracedemrays.game.materials;

import com.badlogic.gdx.graphics.Color;
/**
 * Created by Erik on 2018-05-18.
 */
public class Material {
    Color diffuse;
    Color specular;
    float reflectiveness;

    public Color getDiffuse() {
        return diffuse;
    }

    public Color getSpecular() {
        return specular;
    }

    public float getReflectiveness(){
        return reflectiveness;
    }

    public Material(Color diffuse, Color specular, float reflectiveness){
        this.diffuse = diffuse;
        this.specular = specular;
        this.reflectiveness = reflectiveness;
    }

    public Material(Color diffuse, Color specular){
        this(diffuse, specular, 0);
    }


}
