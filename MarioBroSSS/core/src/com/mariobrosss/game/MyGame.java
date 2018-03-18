package com.mariobrosss.game;

import java.util.ArrayList;

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

import actores.Bala;
import actores.Cannon;
import actores.Goomba;
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
	Goomba goomba;
	Cannon cannon;
	ArrayList<Bala> listaBalas = new ArrayList<Bala>();
	InputMultiplexer multiplexor;
	
	boolean pausa = false;

	public MyGame() {
		super();
		world = new World(new Vector2(Constantes.GRAVEDAD_X, Constantes.GRAVEDAD_Y), true);
		cannon = new Cannon(new MetricVector2(34 * Constantes.PIXELS_TO_METERS, 0.56f * Constantes.PIXELS_TO_METERS), world, new MetricSize(150, 60));
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
		
		// bala = new Bala(new MetricVector2(50, 130), world, new MetricSize(40, 30));
		goomba = new Goomba(new MetricVector2(50, 20), world, new MetricSize(10, 10));
		stage.addActor(goomba);
		// stage.addActor(bala);
		bala = new Bala(new MetricVector2(34 * Constantes.PIXELS_TO_METERS, 0.56f * Constantes.PIXELS_TO_METERS), world,
				new MetricSize(20, 15));
		listaBalas.add(bala);
//		creadorBalas = new CreadorBalas();
//        timer.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//                createBullet();
//            }
//        }, 0, 2000);
		stage.addActor(bala);
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
		bala.draw(batch);
		cannon.draw(batch);
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
