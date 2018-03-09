package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.RepeatablePolygonSprite;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import interfaces.Elemento;
import utiles.Constantes;
import utiles.MetricSize;
import utiles.MetricVector2;

public class Suelo extends Actor implements Elemento {

	Body body;
	Texture texture;
	private TextureRegion textureRegion; 
	MetricSize size;

	public Suelo(MetricVector2 position, MetricSize size, World world) {
		super();
		this.size = size;
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.StaticBody;
		bodydef.position.set(position.getMetersX(), position.getMetersY());
		body = world.createBody(bodydef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(size.getMetersWidth(), size.getMetersHeight());
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.density=1f;
		body.createFixture(fixture);
		body.setUserData("suelo");
		texture = new Texture(Gdx.files.internal("GroundBrick.png"));
		texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
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

	public void draw(SpriteBatch batch) {
		batch.draw(textureRegion, textureRegion.getRegionX(), textureRegion.getRegionY(),
				textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
	}

	@Override
	public boolean isColisionable() {
		return false;
	}

	@Override
	public void act(float delta) {
		this.defineTextureRegion(new MetricVector2(body.getPosition().x, body.getPosition().y));

	}

	@Override
	public boolean isDibujable() {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		return false;
=======
		return true;
>>>>>>> 210e5b4ca3debe830551549c93dd8c4004bc563f
	}
}
