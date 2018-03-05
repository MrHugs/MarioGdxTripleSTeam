package actores;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	public Body body;
	FixtureDef fixtura;
	Sprite sprite;
	private TextureRegion textureRegion;
	Texture texture;
	MetricSize size;
	public int isJumping= 0;
	public Mario(MetricVector2 position, World world, MetricSize size) {
		super();
		this.size = size;
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.DynamicBody;
		bodydef.position.set(position.getMetersX(), position.getMetersY());
		body = world.createBody(bodydef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getMetersWidth(), size.getMetersHeight());
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.density=7f;
		fixture.friction=1f;
		body.setFixedRotation(true);
		body.createFixture(fixture);
		body.setUserData("mario");
		body.setFixedRotation(true);
		texture = new Texture(Gdx.files.internal("mariobros.png"));
		textureRegion = new TextureRegion(texture);
		defineTextureRegion(position);
		shape.dispose();

	}

	private void defineTextureRegion(MetricVector2 position) {
		textureRegion.setRegionX((int) (position.getPixelsX() - size.getPixelsWidth()));
		textureRegion.setRegionY((int) (position.getPixelsY() - size.getPixelsHeight()));
		textureRegion.setRegionWidth((int) (size.getPixelsWidth() * 2));
		textureRegion.setRegionHeight((int) (size.getPixelsHeight() * 2));
	}

	public void colisiona() {
		// TODO Auto-generated method stub

	}

	public void draw(Batch batch) {
		batch.draw(texture,
				body.getPosition().x * Constantes.PIXELS_TO_METERS - textureRegion.getRegionWidth() / 2,
				body.getPosition().y * Constantes.PIXELS_TO_METERS - textureRegion.getRegionHeight() / 2,
				textureRegion.getRegionWidth(),
				textureRegion.getRegionHeight());


	}


	@Override
	public boolean isColisionable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void act() {
		this.defineTextureRegion(new MetricVector2(body.getPosition().x, body.getPosition().y));
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}


}
