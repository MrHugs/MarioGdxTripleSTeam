package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.RepeatablePolygonSprite;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import interfaces.Elemento;
import utiles.Constantes;

public class Suelo extends Actor implements Elemento {

	Body body;
	Texture texture;
	RepeatablePolygonSprite rps;
	public Sprite sprite;
	

	public Suelo(Vector2 posicion, World world) {
		super();
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.StaticBody;
		bodydef.position.set(posicion.x / Constantes.PIXELS_TO_METERS, posicion.y / Constantes.PIXELS_TO_METERS);
		body = world.createBody(bodydef);
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(32 / Constantes.PIXELS_TO_METERS, 32 / Constantes.PIXELS_TO_METERS);
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		body.createFixture(fixture);
		body.setUserData(this);
		shape.dispose();
		texture = new Texture(Gdx.files.internal("Suelo mario 16bits.jpg"));
		texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		sprite = new Sprite(texture);
		sprite.setSize(Gdx.graphics.getWidth(), 100);
		sprite.setPosition(body.getPosition().x*Constantes.PIXELS_TO_METERS, body.getPosition().y*Constantes.PIXELS_TO_METERS);
		sprite.setOrigin(0, 0);
//		TextureRegion textureRegion = new TextureRegion(texture);
//		textureRegion.setRegion(body.getPosition().x, body.getPosition().y, 0, 500);
		
		
	}
	
	
	

	@Override
	public boolean isColisionable() {
		return false;
	}

}
