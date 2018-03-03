package com.mariobrosss.game;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

import actores.Mario;

public class Movimiento implements InputProcessor {

	Mario mario;

	public Movimiento(Mario mario) {
		super();
		this.mario = mario;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		System.out.println(character);
		switch (character) {
		case 'w':
			mario.body.applyForceToCenter(new Vector2(0, 500), true);

			break;
		case 'a':
			mario.body.applyForceToCenter(new Vector2(-250, 0), true);

			break;

		case 'd':
			mario.body.applyForceToCenter(new Vector2(250, 0), true);

			break;

		default:
			break;
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
