package self.AnujBhyravabhotla.FBLA2014.Data;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
/**
 * FBLA Desktop Application Programming Project 2014-2015 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Support file containing necessary algorithms to help the program function
 * */
public class Inventory {
	/**
	 * A data container that stores all the general inventory data for all animals in the shelter.  The supervisors input the data to the program and the program then takes that data, saves
	 * it in the form of an object, and stores it. The shelter supervisors can then search for animals by case number. When the program is closed, the inventory is purged.*/
	public static HashMap<Integer, Animal> generalInventory = new HashMap();
	/**
	 * A data container that stores all the specific inventory data for the dogs in the shelter.  The supervisors input the data to the program and the program then takes that data, saves
	 * it in the form of an object, and stores it. The shelter supervisors can then search for animals by case number. When the program is closed, the inventory is purged.*/
	public static HashMap<Integer, Dog> dogInventory = new HashMap();
	/**
	 * A data container that stores all the specific inventory data for the cats in the shelter.  The supervisors input the data to the program and the program then takes that data, saves
	 * it in the form of an object, and stores it. The shelter supervisors can then search for animals by case number. When the program is closed, the inventory is purged.*/
	public static HashMap<Integer, Cat> catInventory = new HashMap();
	/**
	 * A data container that stores all the specific inventory data for the other animals in the shelter.  The supervisors input the data to the program and the program then takes that data, saves
	 * it in the form of an object, and stores it. The shelter supervisors can then search for animals by case number. When the program is closed, the inventory is purged.*/
	public static HashMap<Integer, Other> otherInventory = new HashMap();

	/**
	 * A data container that stores all the cost data for the shelter.  The supervisors input the data to the program and the program then takes that data, saves
	 * it in the form of an object, and stores it. The shelter supervisors can then search for animals by case number. When the program is closed, the inventory is purged.*/
	public static HashMap<Integer, Double> costReports = new HashMap();
	/**
	 * Takes a string that represents a date in mm/dd/yyyy format and returns an object representation of a calendar date.  The input must be in mm/dd/yyyy format.
	 * @param s a date written in mm/dd/yyyy format
	 * @return an object that represents a date on the Gregorian Calendar.*/
	public static GregorianCalendar extractDate(String s){
		if(s==null||s.equals("")||s.equals("0/0/0")) return null;
		String[] components=s.split("/");
		return new GregorianCalendar(Integer.parseInt(components[2]),Integer.parseInt(components[0])-1,Integer.parseInt(components[1]));
	}
	/**
	 * Takes an object that represents a date on the Gregorian Calendar and prints it in mm/dd/yyyy format
	 * @param date a Gregorian Calendar date object
	 * @return a string representation of this date in mm/dd/yyyy format*/
	public static String printDate(Calendar date){
		if(date==null) return "0/0/0";
		return 1+date.get(date.MONTH)+"/"+date.get(date.DAY_OF_MONTH)+"/"+date.get(date.YEAR);
	}
	/*
	 * The next three methods were subroutines that I was too lazy to code doing brute force if-else statements.  Instead, these take the value
	 * of the boolean, integer, or date field, and return boolean or integer values that are later used with the GUI.*/
	public static int booleantoInt(boolean b){
		if(b) return 1;
		else return 0;
	}
	public static boolean booleanFromDate(GregorianCalendar date){
		return !(printDate(date).equals("0/0/0"));
	}
	public static boolean booleanFromInt(int i){
		return i==1;
	}
	/**
	 * Retrieves the list of dog breeds from the file DOGBREEDS.CSV and lists them in a string array that contains each breed's unique integer identifier and name.
	 * Data for breeds is stored in this format: numerical identifier,name,weight,hair color,hair length.  This file can be edited.
	 * Reads the file by generating a string array for every line in the file, then making a list of these string arrays.  Then creates
	 * a new string array the same size as the list of string arrays containing a string in the format "numerical identifier-name"
	 * */
	public static String[] getDogBreeds() throws IOException{
		CSVReader reader = new CSVReader(new FileReader("DOGBREEDS.csv"));
		List<String[]> retrievedBreeds=reader.readAll();
		reader.close();
		String[] breeds = new String[retrievedBreeds.size()];
		for(int i=0;i<breeds.length;i++)
			breeds[i]=retrievedBreeds.get(i)[0]+"-"+retrievedBreeds.get(i)[1];
		return breeds;
	}
	/**
	 * Retrieves the list of dog breeds from the file CATBREEDS.CSV and lists them in a string array that contains each breed's unique integer identifier and name.
	 * Data for breeds is stored in this format: numerical identifier,name,weight,hair color,hair length.  This file can be edited.
	 * Reads the file by generating a string array for every line in the file, then making a list of these string arrays.  Then creates
	 * a new string array the same size as the list of string arrays containing a string in the format "numerical identifier-name"
	 * */
	public static String[] getCatBreeds() throws IOException{
		CSVReader reader = new CSVReader(new FileReader("CATBREEDS.csv"));
		List<String[]> retrievedBreeds=reader.readAll();
		reader.close();
		String[] breeds = new String[retrievedBreeds.size()];
		for(int i=0;i<breeds.length;i++)
			breeds[i]=retrievedBreeds.get(i)[0]+"-"+retrievedBreeds.get(i)[1];
		return breeds;
	}
	/**
	 * Takes a string array object and writes it to a CSV file using the OpenCSV library.  Data is written as string arrays where each
	 * element in the array is separated by a comma.
	 * @throws IOException if bad things happen during the writing or the initialization of the writer.*/
	public static void writeToCSV(String[] dataset,String filepath) throws IOException{
		CSVWriter writer=new CSVWriter(new FileWriter(filepath,true));
		writer.writeNext(dataset);
		writer.close();
	}
	/**
	 * Takes a list of string array objects and writes it to a CSV file using the OpenCSV library.  Data is written as string arrays where each
	 * element in the array is separated by a comma.  This method replaces every entry previously in the file.
	 * @throws IOException if bad things happen during the writing or the initialization of the writer.*/
	public static void writeToCSV(List<String[]> dataset,String filepath) throws IOException{
		CSVWriter writer=new CSVWriter(new FileWriter(filepath));
		writer.writeAll(dataset);
		writer.close();
	}
	/**
	 * This is a private subroutine that searches a CSV file for an animal record.  This is later used to create animal objects.
	 * Reads the entire file as a list of string arrays, then searches each string array for a matching case number.
	 * If one is found, that string array is returned.  Otherwise nothing is returned.
	 * @param caseNumber the case number for the animal
	 * @param filepath the file to search in
	 * @return a string array that contains the data for the animal with the matching case number, null if no match is found
	 * @throws IOException if the reader cannot be initialized or the contents cannot be read.*/
	private static String[] searchCaseNumber(int caseNumber,String filePath) throws IOException{
		CSVReader reader = new CSVReader(new FileReader(filePath));
		List<String[]> animals = reader.readAll();
		reader.close();
		String[] match = null;
		for(String[] animal:animals){
			if(animal.length>1&&Integer.parseInt(animal[0])==caseNumber){
				match=animal;
				break;
			}
		}
		return match;
	}
	/*
	 * The next three methods are more subroutines that use the entries generated by the searchCaseNumber method to create animal
	 * objects.*/
	private static Dog extractDogFromCSV(int caseNum) throws IOException{
		return Dog.parseArray(searchCaseNumber(caseNum,"GENERAL_INVENTORY.csv"),searchCaseNumber(caseNum,"DOG_INVENTORY.csv"));		
	}
	private static Cat extractCatFromCSV(int caseNum) throws IOException{
		return Cat.parseArray(searchCaseNumber(caseNum,"GENERAL_INVENTORY.csv"),searchCaseNumber(caseNum,"CAT_INVENTORY.csv"));
	}
	private static Other extractOtherFromCSV(int caseNum) throws IOException{
		return Other.parseArray(searchCaseNumber(caseNum,"GENERAL_INVENTORY.csv"),searchCaseNumber(caseNum,"OTHER_INVENTORY.csv"));
	}
	/**
	 * Upon initialization of the application, this method goes through the CSV files that contain the inventory information for
	 * the animals in the shelter, extracts them as objects, and places them in the inventory hashmaps.  These animals can
	 * then be searched later through the hashmap.
	 * It does this by writing the CSV file GENERAL_INVENTORY.csv to a list, then going through each entry to determine the type.  From there, it searches
	 * the specific inventory CSV files for the matching data.  When the data is found, it then parses the arrays into animal objects and inserts them
	 * into the hashmap.
	 * @throws IOException if bad things happen during the extraction process.
	 * */
	public static void populateInventoryHashMaps() throws IOException{
		CSVReader reader = new CSVReader(new FileReader("GENERAL_INVENTORY.csv"));
		List<String[]> animals = reader.readAll();
		reader.close();
		if(!animals.isEmpty()){
			for(String[] animal:animals){
				generalInventory.put(Integer.parseInt(animal[0]), Animal.parseArray(animal));
				if(animal.length==13&&animal[2].equalsIgnoreCase("Dog")){
					dogInventory.put(Integer.parseInt(animal[0]),extractDogFromCSV(Integer.parseInt(animal[0])));
					costReports.put(Integer.parseInt(animal[0]),extractDogFromCSV(Integer.parseInt(animal[0])).calculateCost());
				}
				if(animal.length==13&&animal[2].equalsIgnoreCase("Cat")){
					catInventory.put(Integer.parseInt(animal[0]),extractCatFromCSV(Integer.parseInt(animal[0])));
					costReports.put(Integer.parseInt(animal[0]),extractCatFromCSV(Integer.parseInt(animal[0])).calculateCost());
				}
				if(animal.length==13&&animal[2].equalsIgnoreCase("Other")){
					otherInventory.put(Integer.parseInt(animal[0]),extractOtherFromCSV(Integer.parseInt(animal[0])));
					costReports.put(Integer.parseInt(animal[0]),extractOtherFromCSV(Integer.parseInt(animal[0])).calculateCost());
				}
			}
		}
	}
	/**
	 * Updates the CSV files using the data from the Hashmaps.  This method replaces every entry previously in the CSV files with the updated entries.
	 * @throws IOException if bad things happen during the writing or with the initialization of the writer.*/
	public static void mapDataToCSV() throws IOException{
		ArrayList<String[]> animals=new ArrayList<String[]>();
		ArrayList<String[]> dogs=new ArrayList<String[]>(); 
		ArrayList<String[]> cats=new ArrayList<String[]>(); 
		ArrayList<String[]> others=new ArrayList<String[]>();
		Collection<Animal> data = generalInventory.values();
		for(Animal a:data){
			animals.add(a.toStringArray());
			if(a.type.equalsIgnoreCase("Dog")&&!dogInventory.isEmpty()) dogs.add(dogInventory.get(new Integer(a.caseNumber)).toStringArray());
			if(a.type.equalsIgnoreCase("Cat")&&!catInventory.isEmpty()) cats.add(catInventory.get(new Integer(a.caseNumber)).toStringArray());
			if(a.type.equalsIgnoreCase("Other")&&!otherInventory.isEmpty()) others.add(otherInventory.get(new Integer(a.caseNumber)).toStringArray());
		}
		writeToCSV(animals,"GENERAL_INVENTORY.csv");
		writeToCSV(dogs,"DOG_INVENTORY.csv");
		writeToCSV(cats,"CAT_INVENTORY.csv");
		writeToCSV(others,"OTHER_INVENTORY.csv");
	}
}