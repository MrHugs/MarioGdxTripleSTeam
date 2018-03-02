package actores;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Mario extends MyActor {
	final byte MAX_VIDA = 2;
	byte vida = 1;
	Sprite sprite;
	Body body;
	FixtureDef fixtura;

	public void colisiona() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isColisionable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void act() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

}
