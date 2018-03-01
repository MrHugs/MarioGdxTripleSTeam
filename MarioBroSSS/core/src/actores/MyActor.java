package actores;

import com.badlogic.gdx.scenes.scene2d.Actor;

import interfaces.Colisionable;
import interfaces.Elemento;

public abstract class MyActor extends Actor implements Colisionable, Elemento {

	public abstract void act();

	public abstract void render();

}
