package com.tracedemrays.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Erik on 5/16/2018.
 */
public class LightSource {
    private Vector3 position;
    private Color color;
    private float intensity;

    public float getIntensity(){
        return intensity;
    }

    public Color getColor() {
        return color;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 pos){
        this.position = pos;
    }


    public LightSource(Vector3 position, Color color, float intensity){
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }
}
