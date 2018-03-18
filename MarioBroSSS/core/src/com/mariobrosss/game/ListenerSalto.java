package com.mariobrosss.game;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import actores.Mario;

public class ListenerSalto implements ContactListener {
	Mario mario;

	public ListenerSalto(Mario mario) {
		this.mario = mario;
	}

	@Override
	public void beginContact(Contact contact) {
		// A veces intentais acceder a elementos que no existen. seguramente porque
		// algún elemento
		// que puede colisionar no tiene userData
		try {
			if (!contact.getFixtureA().getBody().getUserData().toString().equals("mario")) {
				System.out.println(contact.getFixtureB().getBody().getUserData().toString());
				System.out.println(contact.getFixtureA().getBody().getUserData().toString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (contact.getFixtureB().getBody().getUserData() != null
				&& contact.getFixtureA().getBody().getUserData() != null) {
			if (contact.getFixtureB().getBody().getUserData().toString().equals("enemigo")) {
				mario.quitaVida();
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
