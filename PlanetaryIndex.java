import java.util.Arrays;
import java.util.Scanner;

public class PlanetaryIndex {
	//Main Function
    public static void main(String[] args) {
    	//Calls the inputs function
    	inputs();
    }
    
    //Function that determines and validates the inputs of the program
    public static void inputs() {
    	while (true) {
    		//Declares an array of strings containing planet names the system will accept
        	String [] PlanetNames = {"MERCURY", "VENUS", "EARTH", "MOON", "MARS", "JUPITER", "SATURN", "URANUS", "NEPTUNE", "PLUTO"};
        	//Initializes scanner
        	Scanner scanner = new Scanner(System.in);
        	//Calls the InputPlanetName function passing in the scanner
    		String InputName = InputPlanetName(scanner);
    		//If the function validName returns false (The name is not a valid input)
    		if (!validName(InputName, PlanetNames)) {
    			//Print to the console the name is invalid
    			System.out.println("Invalid Planet name. Please enter a valid planet in the solar system");
    			//Continue to next iteration of the loop
    			continue;
    		}
    		//Declare a double called weight which equals the result of the InputWeight function
    		Double Weight = InputWeight(scanner);
    		//If the function ValidWeight returns false (The weight is not valid)
    		if (!validWeight(Weight)) {
    			//Print to the console the weight is invalid
    			System.out.println("Invalid Weight Input. Please try again");
    			//Continue to the next iteration of the loop
    			continue;
    		}
    		//Declare variable Planet as an object of PlanetConst which equals the value of the InputName converted to UpperCase
    		PlanetsConst Planet = PlanetsConst.valueOf(InputName.toUpperCase());
    		//Calls the Output function passing in the Weight input, PlanetNames array, and Planet object variable
        	Output(Weight, PlanetNames, Planet);
    	}
    }
    
    //Public class containing identifying variable
    public static class Identifier {
    	//static integer variable Identification
		static int Identification;
    }
    
    //Boolean function validName taking the Inputed name and the Planets array to determine if the name entered is valid
    private static Boolean validName(String InputName, String[] PlanetNames) {
    		//Declare boolean found
			boolean found = false;
			//Try to execute
			try {
			//Declare a String variable Name equal to the UpperCase value of the Input
			String Name = InputName.toUpperCase();
			//Set the Identification variable in the Identifier class  to equal the index of the Name variable in the PlanetsArray
			Identifier.Identification = Arrays.asList(PlanetNames).indexOf(Name);
			//If the Identifier variable = -1 (i.e if the InputName dosn't exist within the PlanetsArray)
			if (Identifier.Identification == -1) {
				//Throw an error to be caught be the catch statement
				throw new Exception("error message");
			}
			//If none of the conditions are met
			else {
				//Set found to equal true
				found = true;
			}
		//Catch any errors in the try statement
		} catch (Exception e) {
			 //Set found to equal false if an error is caught
		     found = false;
		     }
		//Return the value of found to the inputs function
		return found;
		}

    //Private Function validWeight that returns a Boolean value depending on whether the weight input is valid or not
	private static Boolean validWeight(Double Weight) {
		//If the value of the Inputed weight is less than or equal to 0 (Therefore the weight cannot be calculated be the rest of the program)
    	if (Weight <= 0) {
    		//Return a false value as the weight is invalid
    		return false;
    	}
    	//If the above condition isn't met
    	else {
    		//Return true as the weight is valid
    		return true;
    	}
    }
    
	//String Function to obtain input of a planet from the user
    private static String InputPlanetName(Scanner scanner) {
    	//Prompt for input of a planet
    	System.out.print("Enter the name of a planet: ");
    	//Return the input of the user
    	return scanner.nextLine();
    }
    
    //Double function that obtains the input of a weight from the user and returns the double value of the input returning 0.0 aka invalid if the weight cannot be used
    private static Double InputWeight(Scanner scanner) {
    	//Prompt for input of a weight
    	System.out.print("Enter a weight in kg: ");
    	//Read the input into variable textEntered
    	String textEntered = scanner.nextLine();
    	//Try to complete
    	try {
    		//Set the double variable weight to equal the double value of the input
    	    Double weight = Double.parseDouble(textEntered);
    	    //Return the double value of the weight
    	    return weight;
    	  //Catch any errors in execution
    	} catch (NumberFormatException e) {
    		//Print the error
    	    System.out.println(e);
    	    //Return 0.0 aka invalid
    	    return 0.0;
    	}
    }
    
    //Declare enum list of constants
	enum PlanetsConst {
		//List of arrays containing multiple data types corresponding with planetary information each with individual names
    	MERCURY("Terrestrial", "3.30x10^23", 57909175,  3.7, "https://solarsystem.nasa.gov/planets/mercury/overview/"),
        VENUS("Terrestrial", "4.87x10^24", 108208930, 8.9, "https://solarsystem.nasa.gov/planets/venus/overview/"),
        EARTH("Terrestrial", "5.97x10^24", 149597890, 9.8, "https://www.space.com/54-earth-history-composition-and-atmosphere.html"),
        MOON("Moon", "7.35x10^22", 149000000, 1.6, "https://solarsystem.nasa.gov/moons/earths-moon/overview/"),
        MARS("Terrestrial", "6.42x10^23", 227936640, 3.7, "https://solarsystem.nasa.gov/planets/mars/overview/"),
        JUPITER("Gas Giant", "1.90x10^27", 778412010, 24.8, "https://solarsystem.nasa.gov/planets/jupiter/overview/"),
        SATURN("Gas Giant", "5.68x10^26", 1426725400, 10.4, "https://solarsystem.nasa.gov/planets/saturn/overview/"),
        URANUS("Ice Giant", "8.68x10^25", 2870972200L, 8.7, "https://solarsystem.nasa.gov/planets/uranus/overview/"),
        NEPTUNE("Ice Giant", "1.02x10^26", 4498252900L, 11.2, "https://solarsystem.nasa.gov/planets/neptune/overview/"),
        PLUTO("Dwarf Planet", "1.30x10^22", 5906376272L, 0.6, "https://solarsystem.nasa.gov/planets/dwarf-planets/pluto/overview/");
		
		//Declare the immutable values of the constants as variables
		final String Type;
    	final String Mass;
    	final long Distance;
    	final Double Gravity;
    	final String Information;
		
    	//Create an object out of the constants called PlanetsConst passing through the planetary data
		PlanetsConst(String planetType, String planetMass, long planetDistance, Double planetGravity, String planetInformation) {
			//Declare the variables of the object to equal the values of the planetary index data
        	this.Type = planetType;
        	this.Mass = planetMass;
        	this.Distance = planetDistance;
        	this.Gravity = planetGravity;
        	this.Information = planetInformation;
        	
        }
	
	}
	//Suppresses warnings about the static access of EARTH in the output function
    @SuppressWarnings("static-access")
    
    //Private function Output which takes the variables weight, the array PlanetNames and the object PlanetsConst as Planets
	private static void Output(double weight, String[] PlanetNames, PlanetsConst Planets) {
    	
    	//From i=1 to i=7 increasing by 1 with each loop
    	for(int i=1; i<=7; i++ ) {
    		//Switch output based on the value of i
    		switch(i) {
    		  //If i=1
    		  case 1:
    			  //Output the name of the planet from the PlanetsArray using the Identifier determined in ValidName
    			  System.out.println(PlanetNames[Identifier.Identification]);
    			  //Continue to next loop iteration
    			  continue;
    		  //If i=2
    		  case 2:
    			  //Output the Planet Type from the Object Planets
    			  System.out.println("Type: " + Planets.Type);
    			  //Continue to next loop iteration
    			  continue;
              //If i=3
    		  case 3:
    			  //Output the distance between the inputed planet and the earth by calculating the difference between the earth and the sun and the planet input and the sun
    			  System.out.println("Distance of " + PlanetNames[Identifier.Identification] + " from Earth: " + (Planets.Distance - Planets.EARTH.Distance) + "km");
    			  //Continue to next loop iteration
    			  continue;
    		  //If i=4
    		  case 4:
    			  //Output the mass of the inputed planet from object Planets
    			  System.out.println("Mass of " + PlanetNames[Identifier.Identification] + ": " + Planets.EARTH.Mass + "kg");
    			  //Continue to next loop iteration
    			  continue;
    		  //If i=5
    		  case 5:
    			  //Output the Gravity of the planet from object Planets
    			  System.out.println("Gravity: " + Planets.Gravity + "m/s^2");
    			  //Continue to next loop iteration
    			  continue;
    		  //If i=6
    		  case 6:
    			  //Calculate the weight on the inputed planet by multiplying the weight inputed by the planets gravity and dividing that value by earths gravity value
    			  System.out.println("Your weight on " + PlanetNames[Identifier.Identification] + ": " + (weight*Planets.Gravity / Planets.EARTH.Gravity) + "kg");
    			  //Continue to next loop iteration
    			  continue;
    		  //If i=7
    		  case 7:
    			  //Output a link containing further information about the inputed Planet
    			  System.out.println("More information about " + PlanetNames[Identifier.Identification] + ": " + Planets.Information);
    			  //Continue to next loop iteration
    			  continue;
    		}
    	}
    	
    }
    
}
