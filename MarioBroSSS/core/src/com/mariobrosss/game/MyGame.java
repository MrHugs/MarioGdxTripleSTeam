package com.mariobrosss.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.Suelo;
import utiles.Constantes;

public class MyGame {

	private static final int FactorZoomCamera = 4;
	World world;
	Stage stage;
	SpriteBatch batch;
	InputAdapter input;
	OrthographicCamera camera;
	Box2DDebugRenderer debugRenderer;
	Matrix4  debugMatrix;
	Suelo suelo;
	boolean pausa = false;

	public MyGame() {
		super();
		world = new World(new Vector2(Constantes.GRAVEDAD_X, Constantes.GRAVEDAD_Y), true);
		batch = new SpriteBatch();
		debugRenderer = new Box2DDebugRenderer();
		stage = new Stage();
		camera = new OrthographicCamera(Gdx.graphics.getWidth()*FactorZoomCamera,Gdx.graphics.getHeight()*FactorZoomCamera);
		suelo = new Suelo(new Vector2(0,50), world);
		
	}

	public void act() {
		if (!pausa) {
			world.step(1f / 60f, 6, 2);
			stage.act();
		}
	}
	
	public void render() {
		stage.draw();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		debugMatrix=batch.getProjectionMatrix().cpy().scale(Constantes.PIXELS_TO_METERS, Constantes.PIXELS_TO_METERS, 0);
		batch.begin();
		suelo.sprite.draw(batch);
		batch.end();
		debugRenderer.render(world,debugMatrix);
		
	}
	
	public void dispose() {
		world.dispose();
		batch.dispose();
		stage.dispose();
	}
}
