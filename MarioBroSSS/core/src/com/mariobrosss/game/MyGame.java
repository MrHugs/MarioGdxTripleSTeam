package com.mariobrosss.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;


import actores.Bala;
import actores.Mario;
import actores.Suelo;
import utiles.Constantes;
import utiles.MetricSize;
import utiles.MetricVector2;

public class MyGame  {

	private static final float FactorZoomCamera = 1;
	
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	
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
		
		world = new World(new Vector2(Constantes.GRAVEDAD_X, Constantes.GRAVEDAD_Y), true);
		maploader = new TmxMapLoader();
		map  = maploader.load("level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map);
		new B2WorldCreator(world, map);
		
		music = Gdx.audio.newMusic(Gdx.files.internal("marioTheme.mp3"));
		batch = new SpriteBatch();
		debugRenderer = new Box2DDebugRenderer();
		stage = new Stage();
		mario = new Mario(new MetricVector2(100, 100), world, new MetricSize(10, 16));
		camera = new GameCamera();
		
		
		
		movimiento = new Movimiento(mario);
//		suelo = new Suelo(new MetricVector2(1692f, -256f), new MetricSize(2048, 64), world);
//		suelo2 = new Suelo(new MetricVector2(0f, 0), new MetricSize(64, 48), world);
//		suelo2 = new Suelo(new MetricVector2(512f, 120f), new MetricSize(64, 48), world);
<<<<<<< HEAD
//		bala = new Bala(new MetricVector2(50, 130), world, new MetricSize(40, 30));
//		stage.addActor(bala);
=======
		bala = new Bala(new MetricVector2(200, 100), world, new MetricSize(40, 30));
		stage.addActor(bala);
>>>>>>> b43c10a2beabf5be1ccc254e46b702dc3f4f8d34
		stage.addActor(mario);
		multiplexor = new InputMultiplexer();
		multiplexor.addProcessor(0, camera);
		multiplexor.addProcessor(1, movimiento);
		Gdx.input.setInputProcessor(multiplexor);
		world.setContactListener(new ListenerSalto(mario));
		world.setContactListener(new ListenerBalaDrop(mario, bala));
		movimiento.setCamera(camera);
		camera.setMario(mario);
		music.play();
		music.setLooping(true);
		music.setVolume(0.9f);
		mario.setMovimiento(movimiento);
		//camera.camera.position.set(stage.getViewport().getScreenWidth()/2, stage.getViewport().getWorldHeight()/4.2f,camera.camera.position.z);

	}

	public void act() {
		if (!pausa) {
			world.step(1f / 60f, 6, 2);
			stage.act();
			if (bala.isSetForDrop()) {
				bala.drop();
			}
		}
	}

	public void render() {
		this.act();
//		stage.act();
		renderer.setView(camera.camera);
		camera.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();
		batch.setProjectionMatrix(camera.combined());
		debugMatrix = batch.getProjectionMatrix().cpy().scale(Constantes.PIXELS_TO_METERS, Constantes.PIXELS_TO_METERS,
				0);
		renderer.render();
		batch.begin();
		mario.draw(batch);
//		suelo.draw(batch);
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
