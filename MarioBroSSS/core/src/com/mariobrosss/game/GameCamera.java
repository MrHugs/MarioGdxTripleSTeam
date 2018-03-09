package com.mariobrosss.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.viewport.FitViewport;

import actores.Mario;
import utiles.Constantes;

public class GameCamera implements InputProcessor, Screen {


	public OrthographicCamera camera;
	private final int CAMERA_SPEED_MULTIPLIER = 2;
	int screenX;
	int screenY;
	boolean move = false;
	Mario mario;
	FitViewport myViewPort;
	


	public GameCamera(OrthographicCamera camera) {
		super();
		this.camera = camera;
	}

	public GameCamera() {
		this.camera= new OrthographicCamera();
		float x,y;
		y=210;
		x=y*(16f/9f);
		System.out.println("X:"+ x);
		System.out.println("Y:"+ y);

		this.myViewPort = new FitViewport(x,y,camera);
		this.camera.position.set(myViewPort.getWorldWidth()/2,myViewPort.getWorldHeight()/2,camera.position.z);

	}
	

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void update() {
//		if (move) {
//			move();
//		}
//		System.out.println("VIEWPORT "+(camera.viewportWidth/Constantes.PIXELS_TO_METERS)/2);
//		System.out.println("MARIO "+mario.body.getPosition().x);
//		System.out.println("CAMARA "+camera.position.x/Constantes.PIXELS_TO_METERS);
		if (mario.body.getPosition().x*Constantes.PIXELS_TO_METERS>=camera.position.x) {
			marioMove();
		}
		camera.update();
	}

//	private void move() {
//		float x = (screenX - (Gdx.graphics.getWidth() / 2)) * Gdx.graphics.getDeltaTime();
//		float y = -((screenY - (Gdx.graphics.getHeight() / 2)) * Gdx.graphics.getDeltaTime());
//		y = Constantes.ALLOW_VERTICAL_CAMERA_MOVEMENT ? y : 0;
//		x *= CAMERA_SPEED_MULTIPLIER;
//		y *= CAMERA_SPEED_MULTIPLIER;
//		camera.position.set(camera.position.x + x, camera.position.y + y, camera.position.z);
//
//	}
	private void marioMove() {
		float x = (mario.getX() - (Gdx.graphics.getWidth() / 2)) * Gdx.graphics.getDeltaTime();
		float y = -((mario.getY() - (Gdx.graphics.getHeight() / 2)) * Gdx.graphics.getDeltaTime());
		y = Constantes.ALLOW_VERTICAL_CAMERA_MOVEMENT ? y : 0;
		x *= CAMERA_SPEED_MULTIPLIER;
		y *= CAMERA_SPEED_MULTIPLIER;
		camera.position.set(mario.body.getPosition().x*Constantes.PIXELS_TO_METERS  , camera.position.y + y, camera.position.z);
	}
	public Matrix4 combined() {
		return camera.combined;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setMario(Mario mario) {
		this.mario = mario;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("He pulsado");
		if (button == 0 || pointer != 0) {
			this.screenX = screenX;
			this.screenY = screenY;
			move = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Keys.LEFT || pointer == 0) {
			System.out.println("he dejado de pulsar");
			move = false;
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		this.screenX = screenX;
		this.screenY = screenY;
		move = true;
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		myViewPort.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
