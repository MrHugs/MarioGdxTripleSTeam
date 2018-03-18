package com.mariobrosss.game;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import utiles.Constantes;

public class B2WorldCreator {

	public B2WorldCreator(World world, TiledMap map) {

		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body = null;

		// Suelo
		this.recorrerLayers(map, world, 2, bdef, body, shape, fdef);
		// Tuberias
		this.recorrerLayers(map, world, 3, bdef, body, shape, fdef);
		// Monedas
		this.recorrerLayers(map, world, 4, bdef, body, shape, fdef);
		// Ladrillos
		this.recorrerLayers(map, world, 5, bdef, body, shape, fdef);

	}

	public void recorrerLayers(Map map, World world, int layer, BodyDef bdef, Body body, PolygonShape shape,
			FixtureDef fdef) {
		for (MapObject object : map.getLayers().get(layer).getObjects().getByType(RectangleMapObject.class)) {

			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() / 2) / Constantes.PIXELS_TO_METERS,
					(rect.getY() + rect.getHeight() / 2) / Constantes.PIXELS_TO_METERS);
			body = world.createBody(bdef);
			if (layer == 4) {
				body.setUserData("coin");
			}
			shape.setAsBox(rect.getWidth() / 2 / Constantes.PIXELS_TO_METERS,
					rect.getHeight() / 2 / Constantes.PIXELS_TO_METERS);
			fdef.shape = shape;
			body.createFixture(fdef);
//			fdef.filter.categoryBits = Constantes.SUELO;
//			fdef.filter.maskBits = Constantes.MARIO | Constantes.ENEMIGO;
//			
		}
	}

}
