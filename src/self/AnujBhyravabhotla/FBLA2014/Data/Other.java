/**
 * FBLA Desktop Application Programming Project 2014-2015
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Data file used for storage and retrieval of data on animals in the shelter
 * */
package self.AnujBhyravabhotla.FBLA2014.Data;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Other extends Animal{
	private String otherType;
	private int weight;
	private String description;
	private String vaccines;
	/**
	 * Constructs a new other animal object based on the information from the general inventory form and information entered into the 
	 * specialized screen for animals that aren't cats or dogs.*/
	public Other(Animal animal, String otherType, int weight,String description, String vaccines){
		super(animal.caseNumber, animal.name, animal.type,animal.age, animal.birthdate, animal.arrivalDate,animal.microChipped, animal.microchipID, 
				animal.microChipDate, animal.owner, animal.relinquishingParty, animal.cageNumber);
		this.otherType=otherType;
		this.weight=weight;
		this.description=description;
		this.vaccines=vaccines;
	}
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Other other = (Other) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (otherType == null) {
			if (other.otherType != null)
				return false;
		} else if (!otherType.equals(other.otherType))
			return false;
		if (vaccines == null) {
			if (other.vaccines != null)
				return false;
		} else if (!vaccines.equals(other.vaccines))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	/*Getter methods for every field.*/
	public String getOtherType() {
		return otherType;
	}
	public String getDescription() {
		return description;
	}
	public int getWeight(){
		return weight;
	}
	public String getVaccines() {
		return vaccines;
	}
	/**
	 * Represents the animal object as a string array so it can be written to the CSV file.
	 * @return a String[] that contains the data for this animal to be written to OTHER_INVENTORY.csv*/
	@Override
	public String[] toStringArray(){
		return new String[]{caseNumber+"",name,cageNumber+"",otherType,weight+"",description,vaccines,"\n"};
	}
	/**
	 * Takes a string array and parses it into an Animal object.  Meant to make objects out of entries in GENERAL_INVENTORY.csv
	 * @param dataset the String array to be parsed
	 * @return an object representation of the data in the array.*/
	public static Other parseArray(String[] animalDataSet,String[] otherDataSet) {
		return new Other(Animal.parseArray(animalDataSet),otherDataSet[3],Integer.parseInt(otherDataSet[4]),otherDataSet[5],otherDataSet[6]);
	}
	/**
	 * Presents a breakdown of general information for this animal.  This is the information that goes into the client report for each animal
	 * @return a two-dimensional array for use as a TableModel*/
	public String[][] getClientReport() throws IOException{
		String microchipped=new String();
		if(microChipped) microchipped="Yes";
		else microchipped="No";
		return new String[][]{
				{"Name",name},
				{"Age",age+""},
				{"Date of Birth",Inventory.printDate(birthdate)},
				{"Microchipped?",microchipped},
				{"Microchip ID",microchipID+""},
				{"Microchip Date",Inventory.printDate((microChipDate))},
				{"Owner",owner},
				{"Type",otherType},
				{"Weight",weight+""}
				};
	}
}
