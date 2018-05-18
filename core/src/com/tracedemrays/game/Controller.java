package com.tracedemrays.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import javafx.embed.swing.SwingFXUtils;

public class Controller extends ApplicationAdapter {
	SpriteBatch batch;
	int WIDTH, HEIGHT;
	final float FIELD_OF_VIEW = 45f;

	Vector3 right;
	Vector3 up;
	Vector3 fwd = new Vector3(0, 0, 1);

	Pixmap pmap;

	float fovRadians = (float)(Math.PI * (FIELD_OF_VIEW / 2f) / 180f);
	float heightWidthRatio = (float)HEIGHT / (float)WIDTH;
	float halfWidth = (float)Math.tan(fovRadians);
	float halfHeight = heightWidthRatio * halfWidth;
	float camerawidth = halfWidth * 2;
	float cameraheight = halfHeight * 2;
	float pixelWidth = camerawidth / (WIDTH - 1);
	float pixelHeight = cameraheight / (HEIGHT - 1);

	@Override
	public void create () {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		right = fwd.cpy().crs(new Vector3(0, 1, 0));
		up = fwd.cpy().crs(right);

		pmap = new Pixmap(WIDTH, HEIGHT, Pixmap.Format.RGBA8888);

		fovRadians = (float)(Math.PI * (FIELD_OF_VIEW / 2f) / 180f);
		heightWidthRatio = (float)HEIGHT / (float)WIDTH;
		halfWidth = (float)Math.tan(fovRadians);
		halfHeight = heightWidthRatio * halfWidth;
		camerawidth = halfWidth * 2;
		cameraheight = halfHeight * 2;
		pixelWidth = camerawidth / (WIDTH - 1);
		pixelHeight = cameraheight / (HEIGHT - 1);

		Environment.init();

		batch = new SpriteBatch();
	}

	@Override
	public void render () {
		//batch.disableBlending();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		if (Gdx.input.isKeyPressed(Input.Keys.S)){
			for (LightSource l : Environment.getLights()){
				Vector3 pos = l.getPosition();
				l.setPosition(new Vector3(pos.x, pos.y, --pos.z));
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W)){
			for (LightSource l : Environment.getLights()){
				Vector3 pos = l.getPosition();
				l.setPosition(new Vector3(pos.x, pos.y, ++pos.z));
			}
		}


		for (int x = 0; x < Gdx.graphics.getWidth(); x++){
			for (int y = 0; y < Gdx.graphics.getHeight(); y++) {
				Vector3 xcomp = new Vector3((x * pixelWidth) - halfWidth, 0, 0);
				Vector3	ycomp = new Vector3(0,(y * pixelHeight) - halfHeight, 0);
				Vector3 direction = fwd.cpy().add(xcomp).add(ycomp).setLength(1);

				Ray r = new Ray(new Vector3(0, 0, 0), direction);
				Color c = Environment.trace(r);
				c.a = 1;
				pmap.setColor(c);
				pmap.drawPixel(x, y);
			}
		}

		batch.begin();
			batch.draw(new Texture(pmap),0 ,0, WIDTH, HEIGHT, 0, 0,WIDTH, HEIGHT, false, true);
		batch.end();
		//System.exit(0);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
