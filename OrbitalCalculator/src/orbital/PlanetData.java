package orbital;
/**
 * Data representation of different planets, provides methods by which orbital properties are calculated.
 */
public class PlanetData implements Objects {
	//Declares planet variables
    private String name;
    private double mass;
    private double radius;
    private double[] altitudes;
	private double gravity = Objects.GRAVITY;

    /**
     * Constructor to initialize PlanetData.
     *
     * @param name      The name of the set planet.
     * @param mass      The mass of the set planet.
     * @param radius    The radius of the set planet.
     * @param gravity   The gravity of the set planet.
     * @param altitudes Array of planetary altitudes.
     */
    public PlanetData(String name, double mass, double radius, double gravity, double[] altitudes) {
    	//Sets up planetary object with required variables
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.gravity = gravity;
        this.altitudes = altitudes;
    }
    
    //returns the a requested planets name.
    public String getName() {
        return name;
    }
    
    //returns the mass value of a requested planet
    public double getMass() {
        return mass;
    }

  //returns the radius value of a requested planet
    public double getRadius() {
        return radius;
    }

  //returns the altitude array associated with the requested planet
    public double[] getAltitudes() {
        return altitudes;
    }
    
    //returns the current gravity utilized in orbital calculations
    public double getGravity() {
    	return gravity;
    }
}

