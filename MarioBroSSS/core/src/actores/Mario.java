package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import utiles.Constantes;
import utiles.MetricSize;
import utiles.MetricVector2;

public class Mario extends MyActor {
	final byte MAX_VIDA = 2;
	byte vida = 1;
	Body body;
	FixtureDef fixtura;
	Sprite sprite;
	Texture texture;
	
	public Mario(MetricVector2 position, World world, MetricSize size) {
		super();
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.DynamicBody;
		bodydef.position.set(position.getMetersX(), position.getMetersY());
		body = world.createBody(bodydef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getMetersWidth(), size.getMetersHeight());
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		body.createFixture(fixture);
		body.setUserData(this);
		texture = new Texture(Gdx.files.internal("mariobros.png"));
		sprite = new Sprite(texture,20,30);
		shape.dispose();

	}

	public void colisiona() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		batch.draw(texture,body.getPosition().x,body.getPosition().y,200,200);
	}
	
	public void update() {
		sprite.setPosition((body.getPosition().x * Constantes.PIXELS_TO_METERS) - sprite.getWidth() / 2,
		          (body.getPosition().y * Constantes.PIXELS_TO_METERS) - sprite.getHeight() / 2);
	}
	
	@Override
	public boolean isColisionable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void act() {
		this.update();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

}
