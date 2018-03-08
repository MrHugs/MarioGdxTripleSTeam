package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mariobrosss.game.Movimiento;
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
	public MetricSize size;
	public int isJumping = 0;
	public boolean izquierdeando, derecheando = false;
	Movimiento movimiento;
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
		fixture.density = 7f;
		fixture.friction = 1f;
		body.createFixture(fixture);
		body.setUserData("mario");
		body.setFixedRotation(true);
		sprite = defineSprite();
		shape.dispose();
	}
	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}
	private Sprite defineSprite() {
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("mariobros.png")));
		sprite.setBounds(body.getPosition().x * Constantes.PIXELS_TO_METERS - sprite.getWidth() / 2,
				body.getPosition().y * Constantes.PIXELS_TO_METERS - sprite.getHeight() / 2, size.getPixelsWidth() * 2,
				size.getPixelsHeight() * 2);
		sprite.setOriginCenter();
		return sprite;
	}

	public void colisiona() {
		// TODO Auto-generated method stub

	}

	public void draw(Batch batch) {
		super.draw(batch, 1);
		sprite.draw(batch);

	}

	@Override
	public void act(float delta) {
		super.act(delta);
//		System.out.println(derecheando +" DERECHEANDO");
//		System.out.println(izquierdeando+ " IZQUIERDEANDO");
		updateSpritePosition();
		System.out.println(movimiento.bordeIzq());
		if (!derecheando&&!izquierdeando||(izquierdeando&&derecheando)||movimiento.bordeIzq()) {
			body.setLinearVelocity(0f, body.getLinearVelocity().y);
			if (movimiento.bordeIzq()) {
				body.applyLinearImpulse(new Vector2(0.1f, 0), body.getLocalCenter(), true);
			}
		}
		if (body.getLinearVelocity().x>2f) {
			body.setLinearVelocity(3f, body.getLinearVelocity().y);
		}
		if (body.getLinearVelocity().x<-2f) {
			body.setLinearVelocity(-3f, body.getLinearVelocity().y);
		}
		if (derecheando) {
			body.applyLinearImpulse(new Vector2(0.1f, 0), body.getLocalCenter(), true);
		}
		if (izquierdeando) {
			body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getLocalCenter(), true);
		}
	}

	private void updateSpritePosition() {
		float x, y;
		x = body.getPosition().x * Constantes.PIXELS_TO_METERS - sprite.getWidth() / 2;
		y = body.getPosition().y * Constantes.PIXELS_TO_METERS - sprite.getHeight() / 2;
		sprite.setPosition(x, y);
		sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
	}

	@Override
	public boolean isColisionable() {
		return true;
	}
	@Override
	public boolean isDibujable() {
		// TODO Auto-generated method stub
		return true;
	}

}
