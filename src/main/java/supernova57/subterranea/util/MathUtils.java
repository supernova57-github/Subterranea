package supernova57.subterranea.util;

public class MathUtils {
	
	/**
	 * Converts spherical coordinates into Cartesian coordinates.
	 * @param r - radius
	 * @param theta - azimuthal coordinate (in radians)
	 * @param phi - polar coordinate (in radians)
	 * @param coordinate - which Cartesian coordinate to return; a lowercase character (type {@code char}). Defaults to {@code 'x'}.
	 * @return The indicated Cartesian coordinate.
	 * @author supernova57
	 */
	
	public static double sphericalToCartesian(double r, double theta, double phi, char coordinate) {
		switch (coordinate) {
			case 'x':
				return r * Math.cos(theta) * Math.cos(phi);
			case 'y':
				return r * Math.sin(theta) * Math.cos(phi);
			case 'z':
				return r * Math.sin(phi);
			default:
				return r * Math.cos(theta) * Math.cos(phi); 
		}
	}
	
}
