package actores;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import utiles.MetricVector2;

public class Goomba extends SpritableActor {

	public Goomba(MetricVector2 position, World world) {
		super(position, world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void colisiona() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isColisionable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDibujable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void configureBodyDef() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void configureFixtureDef() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Shape createShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void configureShape() {
		// TODO Auto-generated method stub

	}

	@Override
	protected Sprite createSprite() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void configureSprite() {
		// TODO Auto-generated method stub

	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void updateSpritePosition() {
		// TODO Auto-generated method stub

	}

}
