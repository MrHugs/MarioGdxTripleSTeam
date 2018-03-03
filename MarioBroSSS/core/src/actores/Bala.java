package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import utiles.BodyEditorLoader;
import utiles.Constantes;
import utiles.MetricSize;
import utiles.MetricVector2;

public class Bala extends MyActor {
	
	
	
	public Body body;
	FixtureDef fixture;
	Sprite sprite;
	TextureRegion textureRegion;
	Texture texture;
	MetricSize size;
	BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("balaChachipi.json"));
	Vector2 origin;
	
	public Bala(MetricVector2 position,World world, MetricSize size) {
		super();
		this.size=size;
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.DynamicBody;
		bodydef.position.set(position.getMetersX(),position.getMetersY());
		body = world.createBody(bodydef);
		PolygonShape shape = new PolygonShape();
		
		fixture.shape = shape;
		fixture.density=7f;
		fixture.friction=1f;
		loader.attachFixture(body, "balaChachipi", fixture, 1);
		origin=loader.getOrigin("balaChachipi", 1).cpy();
		
		

		
	}
	

	

	
	
	public void draw(Batch batch) {
		batch.draw(texture, body.getPosition().x * Constantes.PIXELS_TO_METERS - textureRegion.getRegionWidth() / 2,
				body.getPosition().y * Constantes.PIXELS_TO_METERS - textureRegion.getRegionHeight() / 2,
				textureRegion.getRegionWidth(),
				textureRegion.getRegionHeight());
		System.out.println(body.getPosition().y * Constantes.PIXELS_TO_METERS - textureRegion.getRegionHeight() / 2);

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
	public void act() {
		//this.defineTextureRegion(new MetricVector2(body.getPosition().x, body.getPosition().y));
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
