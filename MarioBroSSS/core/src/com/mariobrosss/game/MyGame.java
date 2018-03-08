package com.mariobrosss.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
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
	Suelo suelo, suelo2, suelo3;
	Mario mario;
	Bala bala;
	InputAdapter prueba;
	Music music;
	InputMultiplexer multiplexor;
	boolean pausa = false;

	public MyGame() {
		super();
		music = Gdx.audio.newMusic(Gdx.files.internal("marioTheme.mp3"));
		world = new World(new Vector2(Constantes.GRAVEDAD_X, Constantes.GRAVEDAD_Y), true);
		batch = new SpriteBatch();
		debugRenderer = new Box2DDebugRenderer();
		stage = new Stage();
		mario = new Mario(new MetricVector2(50, 200), world, new MetricSize(20, 30));
		camera = new GameCamera(new OrthographicCamera(Gdx.graphics.getWidth() / Constantes.FACTOR_ZOOM_CAMERA,
				Gdx.graphics.getHeight() / Constantes.FACTOR_ZOOM_CAMERA));
		movimiento = new Movimiento(mario);
		suelo = new Suelo(new MetricVector2(1692f, -256f), new MetricSize(2048, 64), world);
		//suelo2 = new Suelo(new MetricVector2(0f, 0), new MetricSize(64, 48), world);
		//suelo3 = new Suelo(new MetricVector2(0f, -200f), new MetricSize(90, 60), world);
		//bala = new Bala(new MetricVector2(50, 130), world, new MetricSize(40, 30));
		//stage.addActor(bala);
		stage.addActor(mario);
		multiplexor = new InputMultiplexer();
		multiplexor.addProcessor(0, camera);
		multiplexor.addProcessor(1, movimiento);
		Gdx.input.setInputProcessor(multiplexor);
		world.setContactListener(new ListenerSalto(mario));
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
		stage.act();
		camera.update();
		batch.setProjectionMatrix(camera.combined());
		debugMatrix = batch.getProjectionMatrix().cpy().scale(Constantes.PIXELS_TO_METERS, Constantes.PIXELS_TO_METERS,
				0);
		batch.begin();
		mario.draw(batch);
		suelo.draw(batch);
//		suelo2.draw(batch);
//		suelo3.draw(batch);
//		bala.draw(batch);
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
