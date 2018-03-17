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
import utiles.CollisionBits;
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
	boolean setForDrop = false;

	public Bala(MetricVector2 position, World world, MetricSize size) {
		super();
		this.size = size;
		BodyDef bodydef = new BodyDef();
		bodydef.type = BodyType.KinematicBody;
		bodydef.position.set(position.getMetersX(), position.getMetersY());
		body = world.createBody(bodydef);
		body.setUserData("bala");
		PolygonShape shape = new PolygonShape();
		fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.density = 7f;
		fixture.friction = 1f;
		fixture.filter.categoryBits = CollisionBits.bala.getCategoryBits();
		fixture.filter.maskBits = CollisionBits.mario.getCategoryBits();
		texture = new Texture(loader.getImagePath("Name"));
		textureRegion = new TextureRegion(texture);
		// Esto cambiado porque en el archivo json se llama "Name"
		loader.attachFixture(body, "Name", fixture, 1);
		origin = loader.getOrigin("Name", 1).cpy();
		defineTextureRegion(position);

	}

	public void draw(Batch batch) {
		batch.draw(texture, body.getPosition().x * Constantes.PIXELS_TO_METERS - textureRegion.getRegionWidth() / 100,
				body.getPosition().y * Constantes.PIXELS_TO_METERS - textureRegion.getRegionHeight() / 100,
				textureRegion.getRegionWidth() * 1.25f, textureRegion.getRegionHeight());

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

	private void defineTextureRegion(MetricVector2 position) {
		textureRegion.setRegionX((int) (position.getPixelsX() - size.getPixelsWidth()));
		textureRegion.setRegionY((int) (position.getPixelsY() - size.getPixelsHeight()));
		textureRegion.setRegionWidth((int) (size.getPixelsWidth() * 2));
		textureRegion.setRegionHeight((int) (size.getPixelsHeight() * 2));
	}
	
	public void setForDrop() {
		this.setForDrop = true;
	}
	
	public void drop() {
		body.setType(BodyType.DynamicBody);
		body.getFixtureList().get(0).getFilterData().maskBits=0;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		this.defineTextureRegion(new MetricVector2(body.getPosition().x, body.getPosition().y));
	}

	@Override
	public boolean isDibujable() {
		return true;
	}

	public boolean isSetForDrop() {
		return setForDrop;
	}

}
