package actores;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import interfaces.Dibujable;
import utiles.Constantes;
import utiles.MetricVector2;

public abstract class SpritableActor extends MyActor implements Dibujable {
	protected Body body;
	protected FixtureDef fixtureDef;
	protected Sprite sprite;
	protected BodyDef bodyDef;
	protected Shape shape;
	protected int isJumping = 0;

	public SpritableActor(MetricVector2 position, World world) {
		super();
		bodyDef = new BodyDef();
		configureBodyDef();
		body = world.createBody(this.bodyDef);
		shape = createShape();
		fixtureDef = new FixtureDef();
		configureFixtureDef();
		configureShape();
		this.fixtureDef.shape = this.shape;
		body.createFixture(this.fixtureDef);
		sprite = createSprite();
		configureSprite();
		shape.dispose();
	}

	protected abstract void configureBodyDef();

	protected abstract void configureSprite();

	protected abstract void configureFixtureDef();

	protected abstract Shape createShape();

	protected abstract void configureShape();

	protected abstract Sprite createSprite();

	protected abstract void defineSprite();

	public void draw(Batch batch) {
		sprite.draw(batch);
	}

	@Override
	public abstract void act(float delta);

	protected abstract void updateSpritePosition();

}
