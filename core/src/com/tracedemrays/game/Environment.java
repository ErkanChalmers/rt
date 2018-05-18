package com.tracedemrays.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.tracedemrays.game.materials.Material;
import com.tracedemrays.game.shapes.Plane;
import com.tracedemrays.game.shapes.Shape;
import com.tracedemrays.game.shapes.Sphere;

import java.util.*;

/**
 * Created by Erik on 5/16/2018.
 */
public class Environment {
    static final float FAR_PLANE = 999999;

    static List<LightSource> getLights(){
        return lights;
    }

    static final Vector3 BLACK = new Vector3(0, 0,0);

   // static Sphere testSphere = new Sphere(new Vector3(0, 0, 10), 2);



    static List<Entity> entities = new ArrayList<Entity>();
    static List<LightSource> lights = new ArrayList();

    static void init(){
        lights.add(new LightSource(new Vector3(4, 0, 6), new Color(1f,1f,1f, 1), 1f));
       // lights.add(new LightSource(new Vector3(4, 2, 6), new Color(0f,1f,0.8f, 1), 1f));

        entities.add(new Entity(
                new Sphere(new Vector3(0, 0, 10), 2),
                new Material(new Color(.8f, .9f, 1f, 1), null)));

        entities.add(new Entity(
                new Plane(new Vector3(5, 0, 0), new Vector3(-1,0,0)),
                new Material(new Color(0,0,1,1), null)));


        entities.add(new Entity(
                new Plane(new Vector3(-5, 0, 0), new Vector3(1,0,0)),
                new Material(new Color(1f,0,0,1), null)));


        entities.add(new Entity(
                new Plane(new Vector3(0, 0, 20), new Vector3(0,0,-1)),
                new Material(new Color(0f,1f,0f,1), null)));
    }


    static Color trace(Ray ray){
        float hitDistance = FAR_PLANE;
        Entity entityHit = null;
        for (Entity entity: entities) {


            //System.out.println(ray.direction);
            float hDist = entity.getShape().hit(ray);
            if (hDist < hitDistance && hDist > 0){
                hitDistance = hDist;
                entityHit = entity;
            }
        }

        //Hit nothing
        if (hitDistance >= FAR_PLANE)
            return Color.BLACK;

        Vector3 hitPosition = ray.origin.cpy().add(ray.direction.cpy().scl(hitDistance));

        for (LightSource light: lights) {
            for (Entity entity: entities) {
                if (isShadow(hitPosition, entity, light))
                    return Color.BLACK;
                }
        }






        Color color = new Color();

        for (LightSource l : lights){
            color.add(shade(ray, entityHit, hitPosition, entityHit.getShape().getNormal(hitPosition), l));
        }

        return color;
    }

    static Color shade(Ray ray, Entity entity, Vector3 hitPosition, Vector3 normal, LightSource ls){
        Vector3 w0 = ray.direction.cpy().scl(-1);
        Vector3 wi = ls.getPosition().cpy().sub(hitPosition);
        float distanceToLight = wi.len();
        wi = wi.setLength(1);
        Color Li = ls.getColor().cpy().mul(ls.getIntensity()).mul(distanceToLight*distanceToLight); // mul intensity
        float nwi = normal.dot(wi);
        if (nwi <= 0)
            return Color.BLACK;


        Color diffuse = entity.getMaterial().getDiffuse().cpy().mul(1f/(float)Math.PI).mul(nwi).mul(Li);


        Vector3 h = wi.cpy().add(w0).setLength(1);


        float nh = normal.dot(h);

        Color specular = ls.getColor().cpy().mul(ls.getIntensity()).mul((float)Math.pow(nh, 99));
        //System.out.println(nwi);
        return specular.add(diffuse);
    }

    static boolean isShadow(Vector3 hitPosition, Entity entity, LightSource ls){
        Vector3 lightDir = ls.getPosition().cpy().sub(hitPosition);
        float distance_to_light = lightDir.len();
        lightDir.setLength(1);
        Ray lightRay = new Ray(hitPosition.cpy().add(lightDir.cpy().scl(01f)), lightDir);

        //System.out.println(lightRay);
        float hitDistance = entity.getShape().hit(lightRay);
        //System.out.println(hitDistance);

        if (hitDistance < 0 || hitDistance >= distance_to_light - 2 * lightDir.cpy().scl(01f).len() ){// || hitDistance > ls.getPosition().cpy().sub(hitPosition).len()){
            return false;
        }
        return true;
    }
}
