package utiles;

public class MetricSize {
	float width;
	float height;

	public MetricSize(float width, float height) {
		super();
		this.width = width;
		this.height = height;
	}

	public float getPixelsWidth() {
		return width;
	}

	public float getPixelsHeight() {
		return height;
	}

	public float getMetersWidth() {
		return getPixelsWidth() / Constantes.PIXELS_TO_METERS;
	}

	public float getMetersHeight() {
		return getPixelsHeight() / Constantes.PIXELS_TO_METERS;
	}

}
