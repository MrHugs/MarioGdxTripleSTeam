package actores;

import java.sql.Date;
import java.time.LocalDateTime;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mariobrosss.game.Movimiento;

import utiles.CollisionBits;
import utiles.Constantes;
import utiles.MetricSize;
import utiles.MetricVector2;

public class Mario extends MyActor {
	final byte MAX_VIDA = 2;
	private float elapsedTime=0;
	byte vida = 1;
	public Body body;
	FixtureDef fixtura;
	Sprite sprite;
	private TextureRegion textureRegion;
	Texture texture;
	public MetricSize size;
	boolean damaged = false;
	int secDamage;
	int finalDamage;
	public boolean isJumping = false;
	public boolean izquierdeando, derecheando = false;
	Movimiento movimiento;
	private TextureAtlas atlas;
	private TextureRegion regionMario;
	Animation animation;

	public Mario(MetricVector2 position, World world, MetricSize size) {
		super();
		textureRegion = new TextureRegion();
		atlas = new TextureAtlas(Gdx.files.internal("Mario_and_Enemies.pack"));
		regionMario = new TextureRegion(atlas.findRegion("little_mario"),0,0,16,16);
		setBounds(0, 0, 16/utiles.Constantes.PIXELS_TO_METERS,  16/utiles.Constantes.PIXELS_TO_METERS);
		textureRegion.setRegion(regionMario);
		this.size = size;
		animation=new Animation(1/15f, atlas.findRegion("little_mario"));

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
		fixture.filter.categoryBits = CollisionBits.mario.getCategoryBits();
		fixture.filter.maskBits = -1;
		body.createFixture(fixture);
		body.setUserData("mario");
		body.setFixedRotation(true);
		sprite = defineSprite();
//		fixture.filter.categoryBits = Constantes.MARIO;
//		fixture.filter.maskBits = Constantes.ENEMIGO| Constantes.SUELO;
		shape.dispose();
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	

	private Sprite defineSprite() {
		sprite = new Sprite(new Texture(Gdx.files.internal("mariobros.png")));
		sprite.setBounds(body.getPosition().x * Constantes.PIXELS_TO_METERS - sprite.getWidth() / 2,
				body.getPosition().y * Constantes.PIXELS_TO_METERS - sprite.getHeight() / 2, size.getPixelsWidth() * 2,
				size.getPixelsHeight() * 2);
		sprite.setOriginCenter();
		return sprite;
	}
	public void invulnerable() {
		this.sprite.setTexture(new Texture(Gdx.files.internal("goldmario.png")));
	}
	public void normal() {
		this.sprite.setTexture(new Texture(Gdx.files.internal("mariobros.png")));
	}
	public void quitaVida(){
		if (!damaged) {
			this.vida--;
			this.damaged = true;
			this.secDamage=LocalDateTime.now().getSecond();
			if (this.secDamage == 60) {
				this.finalDamage = 58;
			}else {
				this.finalDamage = 2;
			}
			this.invulnerable();
		}
		
	}
	public void colisiona() {
		// TODO Auto-generated method stub

	}

	public void draw(Batch batch) {
		super.draw(batch, 1);
		sprite.draw(batch);
		elapsedTime += Gdx.graphics.getDeltaTime();
		batch.draw((TextureRegion)animation.getKeyFrame(elapsedTime, true), 0, 0);


	}
	public void setJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}
	public boolean isJumping() {
		return isJumping;
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		if (vida==0) {
			System.out.println("HE PERDIDO");
		}
		if (damaged) {
			if (Math.abs(LocalDateTime.now().getSecond()-this.secDamage)== this.finalDamage) {
				this.damaged = false;
				this.normal();
			}
		}
		if (body.getLinearVelocity().y==0) {
			setJumping(false);
		}
		if (!derecheando && !izquierdeando || (izquierdeando && derecheando) || movimiento.bordeIzq()) {
			body.setLinearVelocity(0f, body.getLinearVelocity().y);
			if (movimiento.bordeIzq()) {
				body.applyLinearImpulse(new Vector2(0.1f, 0), body.getLocalCenter(), true);
			}
		}
		if (body.getLinearVelocity().x > 2f) {
			body.setLinearVelocity(3f, body.getLinearVelocity().y);
		}
		if (body.getLinearVelocity().x < -2f) {
			body.setLinearVelocity(-3f, body.getLinearVelocity().y);
		}
		if (derecheando&&body.getLinearVelocity().x<=2) {
			body.applyLinearImpulse(new Vector2(0.1f, 0), body.getWorldCenter(), true);
		}
		if (izquierdeando) {
			body.applyLinearImpulse(new Vector2(-0.1f, 0), body.getWorldCenter(), true);
		}
	}

//	private void updateSpritePosition() {
//		float x, y;
//		x = body.getPosition().x * Constantes.PIXELS_TO_METERS - sprite.getWidth() / 2;
//		y = body.getPosition().y * Constantes.PIXELS_TO_METERS - sprite.getHeight() / 2;
//		sprite.setPosition(x, y);
//		sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
//	}

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
