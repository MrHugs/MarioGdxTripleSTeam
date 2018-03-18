package com.mariobrosss.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import actores.Bala;
import actores.Mario;

public class ListenerBalaDrop implements ContactListener {
	Mario mario;
	Bala bala;

	

	public ListenerBalaDrop(Mario mario, Bala bala) {
		super();
		this.mario = mario;
		this.bala = bala;
	}

	@Override
	public void beginContact(Contact contact) {
		// A veces intentais acceder a elementos que no existen. seguramente porque
		// algún elemento
		// que puede colisionar no tiene userData

		if (contact.getFixtureB().getBody().getUserData() != null
				&& contact.getFixtureA().getBody().getUserData() != null) {
			if (contact.getFixtureB().getBody().getUserData().toString().equals("bala")) {
				if (contact.getFixtureB().getBody().getPosition().y <= contact.getFixtureA().getBody()
						.getPosition().y) {
					mario.isJumping = false;
					mario.body.applyForceToCenter(0f, 100f, true);
					bala.setForDrop();

				}

			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
