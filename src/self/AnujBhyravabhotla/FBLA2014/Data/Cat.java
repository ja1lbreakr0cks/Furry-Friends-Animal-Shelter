package self.AnujBhyravabhotla.FBLA2014.Data;

import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
/**
 * FBLA Desktop Application Programming Project 2014-2015 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Data file used for storage and retrieval of data on animals in the shelter
 * */
public class Cat extends Animal{
	private int breed;
	private boolean isMale;
	private boolean spayNeutered;
	private Calendar spayNeuterDate;
	private boolean fleaTested;
	private Calendar dateFirstFleaTreatment;
	private boolean declawed;
	private boolean twoClawsRemoval;
	private boolean felineLeukemia;
	private Calendar felineLeukemiaTestDate;
	private boolean rabiesVaccine;
	private Calendar rabiesVaccineDate;
	/**
	 * Constructs a new cat object based on the information from the general inventory form and information entered into the 
	 * specialized screen for cats.*/
	public Cat(Animal animal, int breed, boolean isMale, boolean spayNeutered,
			Calendar spayNeuterDate, boolean fleaTested, Calendar dateFirstFleaTreatment, 
			boolean declawed, boolean twoClawsRemoval, boolean felineLeukemia, Calendar felineLeukemiaTestDate,
			boolean rabiesVaccine, Calendar rabiesVaccineDate){
		super(animal.caseNumber, animal.name, animal.type,animal.age, animal.birthdate, animal.arrivalDate,animal.microChipped, animal.microchipID, 
				animal.microChipDate, animal.owner, animal.relinquishingParty, animal.cageNumber);
		this.breed=breed;
		this.isMale=isMale;
		this.spayNeutered=spayNeutered;
		this.spayNeuterDate=spayNeuterDate;
		this.fleaTested=fleaTested;
		this.dateFirstFleaTreatment=dateFirstFleaTreatment;
		this.declawed=declawed;
		this.twoClawsRemoval=twoClawsRemoval;
		this.felineLeukemia=felineLeukemia;
		this.felineLeukemiaTestDate=felineLeukemiaTestDate;
		this.rabiesVaccine=rabiesVaccine;
		this.rabiesVaccineDate=rabiesVaccineDate;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cat other = (Cat) obj;
		if (breed != other.breed)
			return false;
		if (dateFirstFleaTreatment == null) {
			if (other.dateFirstFleaTreatment != null)
				return false;
		} else if (!dateFirstFleaTreatment.equals(other.dateFirstFleaTreatment))
			return false;
		if (declawed != other.declawed)
			return false;
		if (felineLeukemia != other.felineLeukemia)
			return false;
		if (felineLeukemiaTestDate == null) {
			if (other.felineLeukemiaTestDate != null)
				return false;
		} else if (!felineLeukemiaTestDate.equals(other.felineLeukemiaTestDate))
			return false;
		if (fleaTested != other.fleaTested)
			return false;
		if (isMale != other.isMale)
			return false;
		if (rabiesVaccine != other.rabiesVaccine)
			return false;
		if (rabiesVaccineDate == null) {
			if (other.rabiesVaccineDate != null)
				return false;
		} else if (!rabiesVaccineDate.equals(other.rabiesVaccineDate))
			return false;
		if (spayNeuterDate == null) {
			if (other.spayNeuterDate != null)
				return false;
		} else if (!spayNeuterDate.equals(other.spayNeuterDate))
			return false;
		if (spayNeutered != other.spayNeutered)
			return false;
		if (twoClawsRemoval != other.twoClawsRemoval)
			return false;
		return true;
	}
	/*Getter methods for every field*/
	public int getBreed() {
		return breed;
	}
	public boolean isMale() {
		return isMale;
	}
	public boolean isSpayNeutered() {
		return spayNeutered;
	}
	public Calendar getSpayNeuterDate() {
		return spayNeuterDate;
	}
	public boolean isFleaTested() {
		return fleaTested;
	}
	public Calendar getDateFirstFleaTreatment() {
		return dateFirstFleaTreatment;
	}
	public boolean isDeclawed() {
		return declawed;
	}
	public boolean wantsTwoClawsRemoval() {
		return twoClawsRemoval;
	}
	public boolean hasFelineLeukemia() {
		return felineLeukemia;
	}
	public Calendar getFelineLeukemiaTestDate() {
		return felineLeukemiaTestDate;
	}
	public boolean hasRabiesVaccine() {
		return rabiesVaccine;
	}
	public Calendar getRabiesVaccineDate() {
		return rabiesVaccineDate;
	}
	/**
	 * Represents the animal object as a string array so it can be written to the CSV file.
	 * @return a String[] that contains the data for this animal to be written to CAT_INVENTORY.csv*/
	@Override
	public String[] toStringArray(){
		return new String[]{caseNumber+"",name,cageNumber+"",breed+"",gender(),Inventory.booleantoInt(spayNeutered)+"",
				Inventory.printDate(spayNeuterDate),Inventory.booleantoInt(fleaTested)+"",Inventory.printDate(dateFirstFleaTreatment),
				Inventory.booleantoInt(declawed)+"",declawingOptions()+"",Inventory.booleantoInt(felineLeukemia)+"",
				Inventory.printDate(felineLeukemiaTestDate),Inventory.printDate(rabiesVaccineDate),"\n"};
	}
	/*Subroutines for gender and declawing data.  The gender data goes into the gender report.  These methods are used in converting
	 * between animals and arrays.*/
	public String gender() {
		if(isMale) return "Male";
		return "Female";
	}
	private static boolean extractGender(String s){
		return s.equalsIgnoreCase("male");
	}
	private int declawingOptions(){
		if(!declawed&&twoClawsRemoval) return 2;
		if(!declawed&&!twoClawsRemoval) return 4;
		return 0;
	}
	private static boolean extractDeclawProcedure(int i){
		return i==2;
	}
	/**
	 * Takes a string array and parses it into an Animal object.  Meant to make objects out of entries in GENERAL_INVENTORY.csv
	 * Does this by reversing the toStringArray() method field by field.
	 * @param dataset the String array to be parsed
	 * @return an object representation of the data in the array.*/
	public static Cat parseArray(String[] animalDataSet,String[] catDataSet) {
		return new Cat(Animal.parseArray(animalDataSet),Integer.parseInt(catDataSet[3]),extractGender(catDataSet[4]),Inventory.booleanFromInt(Integer.parseInt(catDataSet[5])),
				Inventory.extractDate(catDataSet[6]),Inventory.booleanFromInt(Integer.parseInt(catDataSet[7])),Inventory.extractDate(catDataSet[8]),
				Inventory.booleanFromInt(Integer.parseInt(catDataSet[9])),extractDeclawProcedure(Integer.parseInt(catDataSet[10])),
				Inventory.booleanFromInt(Integer.parseInt(catDataSet[11])),Inventory.extractDate(catDataSet[12]),
				Inventory.booleanFromDate(Inventory.extractDate(catDataSet[13])),Inventory.extractDate(catDataSet[13]));
	}
	/**
	 * Calculates the total cost of the animal's stay
	 * @return a decimal number that represents the total cost of the animal's stay*/
	@Override
	public double calculateCost(){
		totalCost=super.calculateCost();
		if(!spayNeutered) totalCost+=75;
		if(fleaTested) totalCost+=10;
		if(felineLeukemia) totalCost+=15;
		if(!rabiesVaccine){
			totalCost+=30;
			totalCost+=15;
		}
		return totalCost;
	}
	/**
	 * Presents a breakdown of the costs for this animal.  This is the information that goes into the cost report for each animal
	 * @return a two-dimensional array for use as a TableModel*/
	@Override
	public String[][] tabulateCost(){
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		Calendar today = new GregorianCalendar();
		String microChippedCost,totalDailyCost,totalFleaPreventionCost,fleaTreatmentCost,spayNeuterCost,rabiesVaccineCost,rabiesTreatmentCost,felineLeukemiaCost;
		if(!microChipped) microChippedCost="$5.00";
		else microChippedCost="Already Microchipped";
		if(fleaTested) fleaTreatmentCost="$10.00";
		else fleaTreatmentCost="Not Applicable";
		if(!spayNeutered) spayNeuterCost="$75.00";
		else spayNeuterCost="Already Spayed/Neutered";
		if(felineLeukemia) felineLeukemiaCost="$15.00";
		else felineLeukemiaCost="Not Applicable";
		if(!rabiesVaccine){
			rabiesVaccineCost="$15.00";
			rabiesTreatmentCost="$30.00";
		}else{
			rabiesVaccineCost="Administered";
			rabiesTreatmentCost="Not Applicable";
		}
		totalFleaPreventionCost=fmt.format((3*(12*(today.get(today.YEAR)-arrivalDate.get(arrivalDate.YEAR))+today.get(today.MONTH)-arrivalDate.get(arrivalDate.MONTH))));
		totalDailyCost=fmt.format((10*(365.25*(today.get(today.YEAR)-arrivalDate.get(arrivalDate.YEAR))+today.get(today.DAY_OF_YEAR)-arrivalDate.get(arrivalDate.DAY_OF_YEAR))));
		return new String[][]{{"Case Number",caseNumber+""},
				{"Name",name},
				{"Micro-chipped",microChippedCost},
				{"Flea Treatment",fleaTreatmentCost},
				{"Spayed/Neutered",spayNeuterCost},
				{"Feline Leukemia",felineLeukemiaCost},
				{"Rabies Vaccine",rabiesVaccineCost},
				{"Rabies Treatment",rabiesTreatmentCost},
				{"Total Daily Cost",totalDailyCost},
				{"Total Cost for Monthly Flea Prevention",totalFleaPreventionCost},
				{"Total Cost for Stay",fmt.format(calculateCost())}};
	}
	/**
	 * Presents a breakdown of general information for this animal.  This is the information that goes into the client report for each animal
	 * @return a two-dimensional array for use as a TableModel*/
	public String[][] getClientReport() throws IOException{
		String[] breeds=Inventory.getCatBreeds();
		String thisBreed = new String();
		for(String s:breeds){
			if(s.contains(breed+"")) thisBreed=s;
		}
		String breedName = thisBreed.substring(1+thisBreed.indexOf("-"));
		CSVReader reader = new CSVReader(new FileReader("CATBREEDS.csv"));
		List<String[]> allBreedData=reader.readAll();
		reader.close();
		String weight=new String();
		String hairColor=new String();
		String hairLength=new String();
		for(String[] s:allBreedData){
			if(Integer.parseInt(s[0])==breed){
				weight=s[2];
				hairColor=s[3];
				hairLength=s[4];
			}
		}
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
				{"Breed",breedName},
				{"Weight",weight},
				{"Hair color",hairColor},
				{"Hair Length",hairLength}
				};
	}
	/*Subroutines to generate data for the gender report.*/
	public String getAllVaccines(){
		if(rabiesVaccine) return "Rabies";
		else return "None";
	}
	public String getSpayedNeuteredStatus(){
		if(spayNeutered) return "Yes";
		else return "No";
	}
}
