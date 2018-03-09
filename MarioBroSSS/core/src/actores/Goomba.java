package actores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mariobrosss.game.Movimiento;

import utiles.Constantes;
import utiles.MetricSize;
import utiles.MetricVector2;

public class Goomba extends MyActor{
	public Body body;
	FixtureDef fixtura;
	Sprite sprite;
	private TextureRegion textureRegion;
	Texture texture;
	public MetricSize size;
	public int isJumping = 0;
	public boolean izquierdeando = false;
	Movimiento movimiento;

	public Goomba(MetricVector2 position, World world, MetricSize size) {
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
	
	private Sprite defineSprite() {
		Sprite sprite = new Sprite(new Texture(Gdx.files.internal("goomba.png")));
		sprite.setBounds(body.getPosition().x * Constantes.PIXELS_TO_METERS - sprite.getWidth() / 2,
				body.getPosition().y * Constantes.PIXELS_TO_METERS - sprite.getHeight() / 2, size.getPixelsWidth() * 2,
				size.getPixelsHeight() * 2);
		sprite.setOriginCenter();
		return sprite;
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		updateSpritePosition();
		if (body.getLinearVelocity().x>2f) {
			body.setLinearVelocity(3f, body.getLinearVelocity().y);
		}
		if (body.getLinearVelocity().x<-2f) {
			body.setLinearVelocity(-3f, body.getLinearVelocity().y);
		}
		if (izquierdeando) {
			body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getLocalCenter(), true);
		}else {
			body.applyLinearImpulse(new Vector2(0.1f, 0), body.getLocalCenter(), true);
		}
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
	
	
	private void updateSpritePosition() {
		float x, y;
		x = body.getPosition().x * Constantes.PIXELS_TO_METERS - sprite.getWidth() / 2;
		y = body.getPosition().y * Constantes.PIXELS_TO_METERS - sprite.getHeight() / 2;
		sprite.setPosition(x, y);
		sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
	}
}

	
	
	
	
	
	
