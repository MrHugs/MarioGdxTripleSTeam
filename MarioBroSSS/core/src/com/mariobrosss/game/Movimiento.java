package com.mariobrosss.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;

import actores.Mario;
import utiles.Constantes;

public class Movimiento implements InputProcessor {

	Mario mario;
	Sound sound = Gdx.audio.newSound(Gdx.files.internal("marioSaltoFixed.mp3"));
	GameCamera camera;
	

	public Movimiento(Mario mario) {
		super();
		this.mario = mario;
		sound.setLooping(0, false);
		sound.setVolume(0, 0.70f);
	}

	public void setCamera(GameCamera camera) {
		this.camera = camera;
	}

	@Override
	public boolean keyDown(int keycode) {

		switch (keycode) {
		case 29:
			if (!bordeIzq()) {
				mario.izquierdeando = true;
			}
			break;

		case 32:
			mario.derecheando = true;
			break;

		default:
			break;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case 29:
			mario.izquierdeando = false;
			break;

		case 32:
			mario.derecheando = false;
			break;

		default:
			break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		switch (character) {
		case 'w':
			if (!mario.isJumping()) {
				mario.body.applyForceToCenter(new Vector2(0, 115), true);
				sound.play();
				mario.setJumping(true);
			}
		default:
			break;
		}

		return false;
	}

	public boolean bordeIzq() {
		float margenIzq = (camera.camera.position.x / Constantes.PIXELS_TO_METERS)
				- ((camera.camera.viewportWidth / Constantes.PIXELS_TO_METERS) / 2);
		margenIzq += mario.size.getMetersWidth() * 2;
		if (mario.body.getPosition().x < margenIzq) {
			return true;
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
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

}
