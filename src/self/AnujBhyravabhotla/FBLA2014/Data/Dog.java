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
public class Dog extends Animal{
	private int breed;
	private boolean isMale;
	private boolean spayNeutered;
	private Calendar spayNeuterDate;
	private boolean fleaTested;
	private Calendar dateFirstFleaTreatment;
	private boolean heartworm;
	private Calendar heartwormTestDate;
	private Calendar heartwormMedicationDate;
	private Calendar heartwormRetestDate;
	private boolean rabiesVaccine;
	private Calendar rabiesVaccineDate;
	private boolean distemperVaccine;
	private Calendar distemperVaccineDate;
	private boolean bordetellaVaccine;
	private Calendar bordetelaVaccineDate;
	/**
	 * Constructs a new dog object based on the information from the general inventory form and information entered into the 
	 * specialized screen for dogs.*/
	public Dog(Animal animal, int breed, boolean isMale, boolean spayNeutered,
			Calendar spayNeuterDate, boolean fleaTested, Calendar dateFirstFleaTreatment,
			boolean heartworm, Calendar heartwormTestDate, Calendar heartwormMedicationDate,
			Calendar heartwormRetestDate, boolean rabiesVaccine, Calendar rabiesVaccineDate, 
			boolean distemperVaccine, Calendar distemperVaccineDate, boolean bordetellaVaccine, 
			Calendar bordetelaVaccineDate){
		super(animal.caseNumber, animal.name, animal.type,animal.age, animal.birthdate, animal.arrivalDate,animal.microChipped, animal.microchipID, 
				animal.microChipDate, animal.owner, animal.relinquishingParty, animal.cageNumber);
		this.breed=breed;
		this.isMale=isMale;
		this.spayNeutered=spayNeutered;
		this.spayNeuterDate=spayNeuterDate;
		this.fleaTested=fleaTested;
		this.dateFirstFleaTreatment=dateFirstFleaTreatment;
		this.heartworm=heartworm;
		this.heartwormTestDate=heartwormTestDate;
		this.heartwormMedicationDate=heartwormMedicationDate;
		this.heartwormRetestDate=heartwormRetestDate;
		this.rabiesVaccine=rabiesVaccine;
		this.rabiesVaccineDate=rabiesVaccineDate;
		this.distemperVaccine=distemperVaccine;
		this.distemperVaccineDate=distemperVaccineDate;
		this.bordetellaVaccine=bordetellaVaccine;
		this.bordetelaVaccineDate=bordetelaVaccineDate;
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
		Dog other = (Dog) obj;
		if (bordetelaVaccineDate == null) {
			if (other.bordetelaVaccineDate != null)
				return false;
		} else if (!bordetelaVaccineDate.equals(other.bordetelaVaccineDate))
			return false;
		if (bordetellaVaccine != other.bordetellaVaccine)
			return false;
		if (breed != other.breed)
			return false;
		if (dateFirstFleaTreatment == null) {
			if (other.dateFirstFleaTreatment != null)
				return false;
		} else if (!dateFirstFleaTreatment.equals(other.dateFirstFleaTreatment))
			return false;
		if (distemperVaccine != other.distemperVaccine)
			return false;
		if (distemperVaccineDate == null) {
			if (other.distemperVaccineDate != null)
				return false;
		} else if (!distemperVaccineDate.equals(other.distemperVaccineDate))
			return false;
		if (fleaTested != other.fleaTested)
			return false;
		if (heartworm != other.heartworm)
			return false;
		if (heartwormMedicationDate == null) {
			if (other.heartwormMedicationDate != null)
				return false;
		} else if (!heartwormMedicationDate
				.equals(other.heartwormMedicationDate))
			return false;
		if (heartwormRetestDate == null) {
			if (other.heartwormRetestDate != null)
				return false;
		} else if (!heartwormRetestDate.equals(other.heartwormRetestDate))
			return false;
		if (heartwormTestDate == null) {
			if (other.heartwormTestDate != null)
				return false;
		} else if (!heartwormTestDate.equals(other.heartwormTestDate))
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
		return true;
	}

	/*
	 * Getter methods for every field*/
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
	public boolean hasHeartworm() {
		return heartworm;
	}
	public Calendar getHeartwormTestDate() {
		return heartwormTestDate;
	}
	public Calendar getHeartwormMedicationDate() {
		return heartwormMedicationDate;
	}
	public Calendar getHeartwormRetestDate() {
		return heartwormRetestDate;
	}
	public boolean gotRabiesVaccine() {
		return rabiesVaccine;
	}
	public Calendar getRabiesVaccineDate() {
		return rabiesVaccineDate;
	}
	public boolean gotDistemperVaccine() {
		return distemperVaccine;
	}
	public Calendar getDistemperVaccineDate() {
		return distemperVaccineDate;
	}
	public boolean gotBordetellaVaccine() {
		return bordetellaVaccine;
	}
	public Calendar getBordetelaVaccineDate() {
		return bordetelaVaccineDate;
	}
	/**
	 * Represents the animal object as a string array so it can be written to the CSV file.
	 * @return a String[] that contains the data for this animal to be written to DOG_INVENTORY.csv*/
	@Override
	public String[] toStringArray(){
		return new String[]{caseNumber+"",name,cageNumber+"",breed+"",gender(),Inventory.booleantoInt(spayNeutered)+"",Inventory.printDate(spayNeuterDate),
				Inventory.booleantoInt(fleaTested)+"",Inventory.printDate(dateFirstFleaTreatment),Inventory.booleantoInt(heartworm)+"",
				Inventory.printDate(heartwormTestDate),Inventory.printDate(heartwormMedicationDate),Inventory.printDate(heartwormRetestDate),
				Inventory.printDate(rabiesVaccineDate),Inventory.printDate(distemperVaccineDate),Inventory.printDate(bordetelaVaccineDate),"\n"};
	}
	/**
	 * Takes a string array and parses it into an Animal object.  Meant to make objects out of entries in GENERAL_INVENTORY.csv
	 * Does this by reversing the toStringArray() method field by field.
	 * @param dataset the String array to be parsed
	 * @return an object representation of the data in the array.*/
	public static Dog parseArray(String[] animalDataSet,String[] dogDataSet){
		return new Dog(Animal.parseArray(animalDataSet),Integer.parseInt(dogDataSet[3]),extractGender(dogDataSet[4]),Inventory.booleanFromInt(Integer.parseInt(dogDataSet[5])),
				Inventory.extractDate(dogDataSet[6]),Inventory.booleanFromInt(Integer.parseInt(dogDataSet[7])),Inventory.extractDate(dogDataSet[8]),
				Inventory.booleanFromInt(Integer.parseInt(dogDataSet[9])),Inventory.extractDate(dogDataSet[10]),Inventory.extractDate(dogDataSet[11]),
				Inventory.extractDate(dogDataSet[12]),Inventory.booleanFromDate(Inventory.extractDate(dogDataSet[13])),Inventory.extractDate(dogDataSet[13]),
				Inventory.booleanFromDate(Inventory.extractDate(dogDataSet[14])),Inventory.extractDate(dogDataSet[14]),
				Inventory.booleanFromDate(Inventory.extractDate(dogDataSet[15])),Inventory.extractDate(dogDataSet[15]));
	}
	/*Subroutines for gender printing.  This information goes into the gender report, and these methods are also used to convert
	 * between animals and arrays.*/
	public String gender() {
		if(isMale) return "Male";
		return "Female";
	}
	private static boolean extractGender(String s){
		return s.equalsIgnoreCase("male");
	}
	/**
	 * Calculates the total cost of the animal's stay
	 * @return a decimal number that represents the total cost of the animal's stay*/
	@Override
	public double calculateCost(){
		totalCost=super.calculateCost();
		if(fleaTested) totalCost+=10;
		if(!spayNeutered) totalCost+=75;
		if(heartworm) totalCost+=10;
		if(!rabiesVaccine){
			totalCost+=15;
			totalCost+=30;
		}
		if(!distemperVaccine) totalCost+=15;
		if(!bordetellaVaccine) totalCost+=15;
		return totalCost;
	}
	/**
	 * Presents a breakdown of the costs for this animal.  This is the information that goes into the cost report for each animal
	 * @return a two-dimensional array for use as a TableModel*/
	@Override
	public String[][] tabulateCost(){
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		Calendar today = new GregorianCalendar();
		String microChippedCost,totalDailyCost,totalFleaPreventionCost,fleaTreatmentCost,spayNeuterCost,heartwormCost,rabiesVaccineCost,rabiesTreatmentCost,
		distemperVaccineCost,bordetelaVaccineCost;
		if(!microChipped) microChippedCost="$5.00";
		else microChippedCost="Already Microchipped";
		if(fleaTested) fleaTreatmentCost="$10.00";
		else fleaTreatmentCost="Not Applicable";
		if(!spayNeutered) spayNeuterCost="$75.00";
		else spayNeuterCost="Already Spayed/Neutered";
		if(heartworm) heartwormCost="$10.00";
		else heartwormCost="Not Applicable";
		if(!rabiesVaccine){
			rabiesVaccineCost="$15.00";
			rabiesTreatmentCost="$30.00";
		}else{
			rabiesVaccineCost="Administered";
			rabiesTreatmentCost="Not Applicable";
		}
		if(!distemperVaccine) distemperVaccineCost="$15.00";
		else distemperVaccineCost="Administered";
		if(!bordetellaVaccine) bordetelaVaccineCost="$15.00";
		else bordetelaVaccineCost="Administered";
		totalFleaPreventionCost=fmt.format((3*(12*(today.get(today.YEAR)-arrivalDate.get(arrivalDate.YEAR))+today.get(today.MONTH)-arrivalDate.get(arrivalDate.MONTH))));
		totalDailyCost=fmt.format((10*(365.25*(today.get(today.YEAR)-arrivalDate.get(arrivalDate.YEAR))+today.get(today.DAY_OF_YEAR)-arrivalDate.get(arrivalDate.DAY_OF_YEAR))));
		return new String[][]{
				{"Case Number",caseNumber+""},
				{"Name",name},
				{"Micro-chipped",microChippedCost},
				{"Flea Treatment",fleaTreatmentCost},
				{"Spayed/Neutered",spayNeuterCost},
				{"Heartworm",heartwormCost},
				{"Rabies Vaccine",rabiesVaccineCost},
				{"Rabies Treatment",rabiesTreatmentCost},
				{"Distemper Vaccine",distemperVaccineCost},
				{"Bordetela Vaccine",bordetelaVaccineCost},
				{"Total Daily Cost",totalDailyCost},
				{"Total Cost for Monthly Flea Prevention",totalFleaPreventionCost},
				{"Total Cost for Stay",fmt.format(calculateCost())}
				};
	}
	/**
	 * Presents a breakdown of general information for this animal.  This is the information that goes into the client report for each animal
	 * @return a two-dimensional array for use as a TableModel*/
	public String[][] getClientReport() throws IOException{
		String[] breeds=Inventory.getDogBreeds();
		String thisBreed = new String();
		for(String s:breeds){
			if(s.contains(breed+"")) thisBreed=s;
		}
		String breedName = thisBreed.substring(1+thisBreed.indexOf("-"));
		CSVReader reader = new CSVReader(new FileReader("DOGBREEDS.csv"));
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
	/*Subroutines that generate string data based on the fields for this animal.  This data goes into the gender report*/
	public String getAllVaccines(){
		String vaccines = new String();
		if(rabiesVaccine) vaccines+="Rabies, ";
		if(distemperVaccine) vaccines+="Distemper, ";
		if(bordetellaVaccine) vaccines+="Bordetela, ";
		if(vaccines.equals("")) vaccines="None";
		return vaccines;
	}
	public String getSpayedNeuteredStatus(){
		if(spayNeutered) return "Yes";
		else return "No";
	}
}
