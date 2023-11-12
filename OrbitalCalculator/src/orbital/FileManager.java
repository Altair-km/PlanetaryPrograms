package orbital;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * File Management Class dealing with the saving and loading of data between sessions.
 * The class allows both the reading of the altitudes.txt file into the system array as well
 * as the saving of inputed data when the program instance is closed. This saves the data 
 * for future use.
 * 
 * */
public class FileManager {
	/**
     * Data is stored per planet in segments of 100 point each for a total of 900 data points."
     * 
     * @param planets The parsing in of the planet object from the Orbital Calculator class.
     */
    public static void saveAltitudesToFile(ArrayList<PlanetData> planets) {
    	//Use the altitudes file to save data in write mode
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("altitudes.txt"))) {
        	//For each planet in PlanetData
            for (PlanetData planet : planets) {
            	//Get the altitude data of the planet index
                double[] altitudes = planet.getAltitudes();
                //For each data point of the altitude index
                for (double altitude : altitudes) {
                	//Add the altitude value to the file
                    fileWriter.write(String.valueOf(altitude));
                    //Start a new line
                    fileWriter.newLine();
                }
            }
            //Output save confirmation
            System.out.println("Data has been saved to altitudes.txt.");
          //Catch errors if operation is unsuccessful
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads altitudes data from "altitudes.txt" data file.
     * Data is loaded per planet
     * 
     * @param planets The parsing in of the planet object from the Orbital Calculator class.
     */
    public static void loadAltitudesFromFile(ArrayList<PlanetData> planets) {
    	//Use the altitudes file in read mode
        try (BufferedReader fileReader = new BufferedReader(new FileReader("altitudes.txt"))) {
        	//For each planet in PlanetData
            for (PlanetData planet : planets) {
            	//Initialize the altitudes array with the planet data 
                double[] altitudes = planet.getAltitudes();
                //For each altitude in the array to 100
                for (int i = 0; i < altitudes.length; i++) {
                	//Read the altitude data point in the file
                    String line = fileReader.readLine();
                    //If the line is empty
                    if (line != null) {
                    	//Parse the line
                        altitudes[i] = Double.parseDouble(line);
                    }
                }
            }
            //Print load confirmation
            System.out.println("Altitudes loaded from altitudes.txt.");
          //Catch errors if unsuccessful operation
        } catch (IOException e) {
        	//Output error message
            System.out.println("No data found. Using default values.");
        }
    }
}
