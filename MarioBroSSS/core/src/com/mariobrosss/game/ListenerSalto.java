package com.mariobrosss.game;

import java.io.Console;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import actores.Mario;

public class ListenerSalto implements ContactListener {
	Mario mario;

	public ListenerSalto(Mario mario) {
		this.mario = mario;
	}

	@Override
	public void beginContact(Contact contact) {
		System.out.println(contact.getFixtureA().getBody().getUserData().toString());
		System.out.println(contact.getFixtureB().getBody().getUserData().toString());
		if (contact.getFixtureA().getBody().getUserData().toString().equals("suelo")) {
			mario.isJumping = 0;
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
