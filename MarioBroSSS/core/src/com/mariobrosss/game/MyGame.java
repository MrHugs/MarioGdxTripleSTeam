package com.mariobrosss.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

import actores.Bala;
import actores.Mario;
import actores.Suelo;
import utiles.Constantes;
import utiles.MetricSize;
import utiles.MetricVector2;

public class MyGame {

	private static final float FactorZoomCamera = 1;
	World world;
	Stage stage;
	SpriteBatch batch;
	InputAdapter input;
	GameCamera camera;
	Movimiento movimiento;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	Suelo suelo;
	Suelo suelo2;
	Mario mario;
	Bala bala ;
	InputAdapter prueba;
	Music music;
	boolean pausa = false;

	public MyGame() {
		super();
		music = Gdx.audio.newMusic(Gdx.files.internal("marioTheme.mp3"));
		world = new World(new Vector2(Constantes.GRAVEDAD_X, Constantes.GRAVEDAD_Y), true);
		batch = new SpriteBatch();
		debugRenderer = new Box2DDebugRenderer();
		stage = new Stage();
		camera = new GameCamera(new OrthographicCamera(Gdx.graphics.getWidth() * Constantes.FACTOR_ZOOM_CAMERA,
				Gdx.graphics.getHeight() * Constantes.FACTOR_ZOOM_CAMERA));
		suelo = new Suelo(new MetricVector2(-512f, -256f), new MetricSize(2048, 64), world);
		suelo2 = new Suelo(new MetricVector2(0, 0), new MetricSize(64, 48), world);
		mario = new Mario(new MetricVector2(50, 200), world, new MetricSize(20, 30));
		bala = new Bala(new MetricVector2(0, 200), world, new MetricSize(40, 30));
		movimiento = new Movimiento(mario);
		stage.addActor(mario);
		stage.addActor(bala);
		Gdx.input.setInputProcessor(camera);
		world.setContactListener(new ListenerSalto(mario));
		Gdx.input.setInputProcessor(movimiento);
		music.play();
		music.setLooping(true);
		music.setVolume(0.9f);
	}

	public void act() {
		if (!pausa) {
			world.step(1f / 60f, 6, 2);
			stage.act();
		}
	}

	public void render() {
		this.act();
		stage.draw();
		camera.update();
		batch.setProjectionMatrix(camera.combined());
		debugMatrix = batch.getProjectionMatrix().cpy().scale(Constantes.PIXELS_TO_METERS, Constantes.PIXELS_TO_METERS,
				0);
		batch.begin();
		mario.draw(batch);
		suelo.draw(batch);
		suelo2.draw(batch);
		batch.end();
		debugRenderer.render(world, debugMatrix);

	}

	public void dispose() {
		world.dispose();
		music.dispose();
		batch.dispose();
		stage.dispose();
	}
}
