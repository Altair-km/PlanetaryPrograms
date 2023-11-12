//imports packages to be used in the program
package orbital;

import java.io.*;
import java.util.ArrayList;
import java.text.NumberFormat;


/**
 * The Orbital Calculator manages all planet data for the 9 planets used in the program. The class also calculates orbital properties 
 * such as orbital velocity, gravitational potential energy and time to complete a planetary orbit.
 */
public class OrbitalCalculator implements Objects {
    //Declares ArrayList of Planet objects based on PlanetData
    private static ArrayList<PlanetData> planets = new ArrayList<>();
    private static PlanetData currentPlanet;
    private double orbitalVelocity;
    private double OrbitalPeriod;
    private double gravitationalPotentialEnergy;
    
    private NumberFormat numberFormat;
    
    /**
     * Constructor initializes the list of planets with their data and sets the current planet to Earth.
     */
    public OrbitalCalculator() {
        // Initialize the list of planets with their data
        double[] PlanetsData = new double[Objects.MaxDatPoints];
        //Declare planet objects to be used in calculations
        planets.add(new PlanetData("Mercury", 3.285e23, 2439700,3.7 ,PlanetsData.clone()));
        planets.add(new PlanetData("Venus", 4.867e24, 6051800, 8.9,PlanetsData.clone()));
        planets.add(new PlanetData("Earth", 5.972e24, 6371000, 9.8,PlanetsData.clone()));
        planets.add(new PlanetData("Mars", 6.391e23, 3389500, 3.7,PlanetsData.clone()));
        planets.add(new PlanetData("Jupiter", 1.898e27, 69911000,24.8 ,PlanetsData.clone()));
        planets.add(new PlanetData("Saturn", 5.683e26, 58232000, 10.4,PlanetsData.clone()));
        planets.add(new PlanetData("Uranus", 8.681e25, 25362000, 8.7,PlanetsData.clone()));
        planets.add(new PlanetData("Neptune", 1.024e26, 24622000, 11.2,PlanetsData.clone()));
        planets.add(new PlanetData("Pluto", 1.309e22, 1188, 0.6,PlanetsData.clone()));

     // Initialize NumberFormat for formatting numeric values
        numberFormat = NumberFormat.getInstance();
     // Set the maximum number of decimal places to 3
        numberFormat.setMaximumFractionDigits(Objects.MaxDecimal); 
        
        // Set the default planet to Earth
        currentPlanet = planets.get(Objects.DefaultPlanet);
    }

    /**
     * Used to return the value of the current planet object. 
     * This method is primarily used in the parsing of objects between classes.
     */
    public ArrayList<PlanetData> getPlanets() {
        return planets;
    }
    
    /**
     * Calculates orbital velocity based on user altitude input and planet setting.
     *
     * @param altitude Altitude above the planet's surface.
     * @return orbitalVelocity The calculated orbital velocity
     */
    public double calculateOrbitalVelocity(double altitude) {
    	//Calculates the combined radius of the planet and altitude to find the orbital radius
        double orbitalRadius = currentPlanet.getRadius() + altitude;
        return orbitalVelocity = Math.sqrt(currentPlanet.getGravity() * currentPlanet.getMass() / orbitalRadius);
    }

    /**
     * Calculates the orbital period based on user altitude input and planet setting.
     *
     * @param altitude Altitude above the planet's surface.
     * @return OrbitalPeriod The calculated orbital period.
     */
    public double calculateOrbitalPeriod(double altitude) {
    	//Calculates the combined radius of the planet and altitude to find the orbital radius
        double orbitalRadius = currentPlanet.getRadius() + altitude;
        //Calculates orbital velocity through velocity equation
        orbitalVelocity = Math.sqrt(currentPlanet.getGravity() * currentPlanet.getMass() / orbitalRadius);
        //returns the calculated orbital period
        return OrbitalPeriod = 2 * Math.PI * orbitalRadius / orbitalVelocity;
    }

    /**
     * Calculates gravitational potential energy based on the user input altitude and set planet.
     *
     * @param altitude Altitude above the planet's surface.
     * @return gravitationalPotentialEnergy The calculated gravitational energy
     */
    public double calculateGravitationalPotentialEnergy(double altitude) {
    	//Calculates the combined radius of the planet and altitude to find the orbital radius
        double orbitalRadius = currentPlanet.getRadius() + altitude;
        //Calculates and return gravitational potential energy
        return gravitationalPotentialEnergy = -(currentPlanet.getGravity() * currentPlanet.getMass() * currentPlanet.getMass()) / (2 * orbitalRadius);
    }

    /**
     * Sets the current planet to the one at the specified index.
     *
     * @param planetIndex Index of the planet in PlanetData.
     */
    public void setPlanet(int planetIndex) {
    	//If the planet index is within the correct range of options
        if (planetIndex >= 0 && planetIndex < planets.size()) {
        	//Set the current planet to the correct index
            currentPlanet = planets.get(planetIndex);
        }
    }

    /**
     * Returns the calculated orbital velocity.
     *
     * @return OrbitalVelocity measured in meters per second.
     */
    public double getOrbitalVelocity() {
        return orbitalVelocity;
    }

    /**
     * Returns the calculated orbital period.
     *
     * @return OrbitalPeriod measured in meters per second.
     */
    public double getOrbitalPeriod() {
        return OrbitalPeriod;
    }

    /**
     * Returns the calculated gravitational potential energy.
     *
     * @return gravitationalPotentialEnergy measured in Joules (J).
     */
    public double getGravitationalPotentialEnergy() {
        return gravitationalPotentialEnergy;
    }

    

    /**
     * Main method for projecting and completing the OrbitalCalculator program functions.
     * 
     * @param args java command line storage
     * @throws OrbitalCalculatorException throw statement if program encounters an error
     */
    public static void main(String[] args) throws OrbitalCalculatorException {
    	//Declares a new orbital calculator
        OrbitalCalculator calculator = new OrbitalCalculator();
        //Loads altitude data from file
        FileManager.loadAltitudesFromFile(planets);

        try {
        	//Sets up the scanner for user inputs
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            //Exit system is currently untrue as the program is running
            boolean exitProgram = false;

            //While the user has not exited the program
            while (!exitProgram) {
            	//Display interface for the user to select Operations
                System.out.println("Physics Calculator Menu:");
                System.out.println("1. Calculate Orbital Velocity");
                System.out.println("2. Calculate Time to Complete Orbit");
                System.out.println("3. Calculate Gravitational Potential Energy");
                System.out.println("4. Set Planet");
                System.out.println("5. View Altitudes for Current Planet");
                System.out.println("6. Show current Gravity Constant");
                System.out.println("7. Exit");
                System.out.print("Enter your choice (1-6): ");
                /**
                 * Prompts the user to choose from the available options of the program.
                 * The user must pick which calculation or augmentation to the program they wish to complete.
                 * The user may also choose to exit the program
                 */
                try {
                	//Read the users integer choice
                    int choice = Integer.parseInt(br.readLine());

                    //Switch statement based on user input
                    switch (choice) {
                    //If the user selects orbital velocity
                        case 1:
                        	//Run velocity calculation method
                            DisplayOrbitalVelocity(calculator, br);
                            break;
                   //If the user selects orbital period calculation
                        case 2:
                        	//Run period calculation method
                            DisplayOrbitTime(calculator, br);
                            break;
                   //If the user selects potential energy calculation
                        case 3:
                        	//Run potential energy calculation method
                            DisplayPotentialEnergy(calculator, br);
                            break;
                   //If the user selects change current planet option
                        case 4:
                        	//Run planet selection calculation method
                            selectPlanet(calculator, br);
                            break;
                   //If the user selects the view current altitude option
                        case 5:
                        	//Run altitude viewing calculation method
                            viewAltitudesForCurrentPlanet(calculator, br);
                            break;
                   //If the user selects to view current Gravity         
                        case 6:
                   //Output the gravity value of the current planet
                            System.out.println(currentPlanet.getGravity() + " meters per second");
                            break;
                   //If the user selects to exit the program
                        case 7:
                        	//Save altitude data to the altitudes file
                            FileManager.saveAltitudesToFile(planets);
                            //Exit program is now true
                            exitProgram = true;
                            break;
                  //If the user choice is invalid
                        default:
                        	//Present error message prompting for new input
                            throw new NumberFormatException();
                    }
                    /**
                     * Error handling system should any operations of the method whether in the switch statement or 
                     * further out fail to complete.
                     */
                  //Catch errors from unsuccessful operation
                } catch (NumberFormatException e) {
                	//Output error message
                    System.out.println("Invalid input. Please enter a valid number.");
                    
                } catch (OrbitalCalculatorException e) {
                	System.out.println(e.toString());
                }
            }
          //Catch errors of method
        } catch (IOException e) {
            e.printStackTrace();
          //Call specific error handling method
        } 
    }
    
    /**
     * Method to calculate and display the orbital velocity given the user altitude input and the currently set planet data.
     *
     * @param calculator The current OrbitalCalculator instance.
     * @param br         The BufferedReader for the users input.
     * @throws IOException If a program error occurs.
     */
    private static void DisplayOrbitalVelocity(OrbitalCalculator calculator, BufferedReader br) throws IOException, OrbitalCalculatorException {
    	try {
    	//Prompt for altitude location input
        System.out.print("Enter location (1-100): ");
        //Set location used to user input
        int location = Integer.parseInt(br.readLine());
        //If the input is valid
        if (isValidLocation(location)) {
        	//Prompt for data value
            System.out.print("Enter altitude (in meters): ");
            //Read user input of data value
            double altitude = Double.parseDouble(br.readLine());
            //Set the altitude location in the array to the user inputed value
            calculator.currentPlanet.getAltitudes()[location - 1] = altitude;
            //Calculate the orbital velocity via calling the appropriate method
            calculator.calculateOrbitalVelocity(altitude);
            //Output the calculation result
            System.out.println("Orbital Velocity: " + calculator.getOrbitalVelocity() + " m/s");
        } else {
        	//Prompt for another user input
        	throw new OrbitalCalculatorException("Invalid location. Please select a valid location (1-100).");
        	
        } 
    	} catch (OrbitalCalculatorException e) {
        	e.toString();
        }
    } 

    /**
     * Method to calculate and display the time required to complete an orbit at a given altitude.
     *
     * @param calculator The current OrbitalCalculator instance.
     * @param br         The BufferedReader for the users input.
     * @throws IOException If a method error occurs.
     */
    private static void DisplayOrbitTime(OrbitalCalculator calculator, BufferedReader br) throws IOException {
    	//Prompt for altitude location
        System.out.print("Enter location (1-100): ");
        //Set location to user input
        int location = Integer.parseInt(br.readLine());
        //Check if the input location is valid
        if (isValidLocation(location)) {
        	//Prompt for data value
            System.out.print("Enter altitude (in meters): ");
            //Read user input of data value
            double altitude = Double.parseDouble(br.readLine());
            //Set the altitude location in the array to the user inputed value
            calculator.currentPlanet.getAltitudes()[location - 1] = altitude;
            //Call the calculation method to find the orbital period
            calculator.calculateOrbitalPeriod(altitude);
            //Output calculation result
            System.out.println("Time to Complete Orbit: " + calculator.getOrbitalPeriod() + " seconds");
        } else {
        	//Prompt user for additional input
            System.out.println("Invalid location. Please select a valid location (1-100).");
        }
    }
    /**
     * Method to calculate and display the gravitational potential energy based on current planet setting and altitude user input.
     *
     * @param calculator The current OrbitalCalculator instance.
     * @param br         The BufferedReader for the users input.
     * @throws IOException If a method error occurs.
     */
    private static void DisplayPotentialEnergy(OrbitalCalculator calculator, BufferedReader br) throws IOException, OrbitalCalculatorException {
    	try {
    	//Prompt user for location input
        System.out.print("Enter location (1-100): ");
        //Read user location input
        int location = Integer.parseInt(br.readLine());
        //If input location is valid
        if (isValidLocation(location)) {
        	//Prompt for data value
            System.out.print("Enter altitude (in meters): ");
            //Read user input of data value
            double altitude = Double.parseDouble(br.readLine());
            //Set the altitude location in the array to the user inputed value
            calculator.currentPlanet.getAltitudes()[location - 1] = altitude;
            //Calculate the potential energy via calling the calculation method
            calculator.calculateGravitationalPotentialEnergy(altitude);
            //Output calculation result
            System.out.println("Gravitational Potential Energy: " + calculator.getGravitationalPotentialEnergy() + " J");
        } else {
        	//Throw exception due to invalid input
            throw new OrbitalCalculatorException("Invalid location. Please select a valid location (1-100).");
        }
    } catch (OrbitalCalculatorException e) {
    	System.out.println(e.toString());
    }
    }
    
    /**
     * Method to allow the user to change the current planet used in calculations.
     * The user can select any planet within the planets array which includes all solar system planets
     *
     * @param calculator The current OrbitalCalculator instance.
     * @param br         The BufferedReader for the users input.
     * @throws IOException If a method error occurs.
     * @throws OrbitalCalculatorException 
     */
    private static void selectPlanet(OrbitalCalculator calculator, BufferedReader br) throws IOException, OrbitalCalculatorException {
    	try {
    	//Present planet inputs
        System.out.println("Select a planet:");
        //For each planet in the array
        for (int i = 0; i < calculator.planets.size(); i++) {
        	//Output the name of the planet
            System.out.println(i + 1 + ". " + calculator.planets.get(i).getName());
        }
        //Prompt for planet number
        System.out.print("Enter the planet number: ");
        //Read the user input
        int planetIndex = Integer.parseInt(br.readLine()) - 1;
        if (0>planetIndex || planetIndex>9 ) {
        	throw new OrbitalCalculatorException("Invlaid input, please enter a valid planet");	
        } else {
        	System.out.println("Planet set to " + calculator.planets.get(planetIndex).getName());
        	//Set the inputed planet to the current instance
            calculator.setPlanet(planetIndex);
        }
    	} catch (OrbitalCalculatorException e) {
        	System.out.println(e.toString());
        }
    }

    /**
     * Method to view altitudes for the current planet instance at a user inputed location.
     *
     * @param calculator The current OrbitalCalculator instance.
     * @param br         The BufferedReader for the users input.
     * @throws IOException If a method error occurs.
     */
    private static void viewAltitudesForCurrentPlanet(OrbitalCalculator calculator, BufferedReader br) throws IOException {
    	//Prompt for altitude location
        System.out.print("Enter location (1-100): ");
        //Read the users location input
        int location = Integer.parseInt(br.readLine());
        //If the user input location is valid
        if (isValidLocation(location)) {
        	//Set the altitude array instance to the user input
            double altitude = calculator.currentPlanet.getAltitudes()[location - 1];
            //Output location altitude value 
            System.out.println("Altitude at Location " + location + ": " + altitude + " meters");
        } else {
        	//Prompt for additional user input
            System.out.println("Invalid location. Please select a valid location (1-100).");
        }
    }
    
    /**
     * Assisting method to validate that the users location input is a valid input (between numbers 1-100).
     *
     * @param location The altitude location to validate.
     * @return True if the location is valid, otherwise, return false.
     */
    private static boolean isValidLocation(int location) {
        return location >= 1 && location <= 100;
    }

}
