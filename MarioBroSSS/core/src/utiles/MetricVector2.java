package utiles;

public class MetricVector2 {

	float x, y;

	public MetricVector2(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public float getPixelsX() {
		return x;
	}

	public float getPixelsY() {
		return y;
	}

	public float getMetersX() {
		return x / Constantes.PIXELS_TO_METERS;
	}

	public float getMetersY() {
		return y / Constantes.PIXELS_TO_METERS;
	}

}
