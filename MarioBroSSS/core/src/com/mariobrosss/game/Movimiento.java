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
		switch (keycode) {
		case 87 :
			if (mario.isJumping < 2) {
				mario.body.applyForceToCenter(new Vector2(0, 500), true);
				mario.isJumping++;
			}

			break;
		case 65:
			mario.body.applyLinearImpulse(new Vector2(-2, 0), mario.body.getLocalCenter(), true);
			if (mario.body.getLinearVelocity().x< -2 ) {
				mario.body.setLinearVelocity(-2, mario.body.getLinearVelocity().y);
			}
			break;

		case 68:
			mario.body.applyLinearImpulse(new Vector2(2, 0), mario.body.getLocalCenter(), true);
			if (mario.body.getLinearVelocity().x>2 ) {
				mario.body.setLinearVelocity(2, mario.body.getLinearVelocity().y);
			}
			break;

		default:
			break;
		}
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
			if (mario.isJumping < 2) {
				mario.body.applyForceToCenter(new Vector2(0, 500), true);
				mario.isJumping++;
			}

			break;
			
		case 'a':
			mario.body.applyLinearImpulse(new Vector2(-2, 0), mario.body.getLocalCenter(), true);
			if (mario.body.getLinearVelocity().x< -2 ) {
				mario.body.setLinearVelocity(-2, mario.body.getLinearVelocity().y);
			}
			break;

		case 'd':
			mario.body.applyLinearImpulse(new Vector2(2, 0), mario.body.getLocalCenter(), true);
			if (mario.body.getLinearVelocity().x>2 ) {
				mario.body.setLinearVelocity(2, mario.body.getLinearVelocity().y);
			}
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
