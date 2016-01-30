package self.AnujBhyravabhotla.FBLA2014.Data;

import java.text.NumberFormat;
import java.util.GregorianCalendar;
/**
 * FBLA Desktop Application Programming Project 2014-2015 
 * @author Anuj Bhyravabhotla, Northview High School
 * @category Data file used for storage and retrieval of data on animals in the shelter
 * */
public class Animal extends Object{
	public int caseNumber;
	protected String name;
	protected String type;
	protected int age;
	protected GregorianCalendar birthdate;
	protected GregorianCalendar arrivalDate=new GregorianCalendar();
	protected boolean microChipped;
	protected long microchipID;
	protected String owner;
	protected GregorianCalendar microChipDate;
	protected String relinquishingParty;
	protected int cageNumber;
	protected double totalCost=0;
	/**
	 * Constructs a new animal based on data it receives from the general inventory form.*/
	public Animal(int caseNumber, String name, String type,int age, GregorianCalendar birthdate, GregorianCalendar arrivalDate,boolean microchipped, 
			long microchipID, GregorianCalendar microChipDate, String owner,
			String relinquishingparty, int cageNumber){
		this.caseNumber=caseNumber;
		this.name=name;
		this.type=type;
		this.age=age;
		this.birthdate=birthdate;
		this.arrivalDate=arrivalDate;
		this.microChipped=microchipped;
		this.microchipID=microchipID;
		this.owner=owner;
		this.microChipDate=microChipDate;
		this.relinquishingParty=relinquishingparty;
		this.cageNumber=cageNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result
				+ ((arrivalDate == null) ? 0 : arrivalDate.hashCode());
		result = prime * result
				+ ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime * result + cageNumber;
		result = prime * result + caseNumber;
		result = prime * result
				+ ((microChipDate == null) ? 0 : microChipDate.hashCode());
		result = prime * result + (microChipped ? 1231 : 1237);
		result = prime * result + (int) (microchipID ^ (microchipID >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		result = prime
				* result
				+ ((relinquishingParty == null) ? 0 : relinquishingParty
						.hashCode());
		long temp;
		temp = Double.doubleToLongBits(totalCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	/**
	 * Compares this animal to another animal on a field-by-field basis
	 * @return whether or not the animals are the same in every way.*/
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		if (age != other.age)
			return false;
		if (arrivalDate == null) {
			if (other.arrivalDate != null)
				return false;
		} else if (!arrivalDate.equals(other.arrivalDate))
			return false;
		if (birthdate == null) {
			if (other.birthdate != null)
				return false;
		} else if (!birthdate.equals(other.birthdate))
			return false;
		if (cageNumber != other.cageNumber)
			return false;
		if (caseNumber != other.caseNumber)
			return false;
		if (microChipDate == null) {
			if (other.microChipDate != null)
				return false;
		} else if (!microChipDate.equals(other.microChipDate))
			return false;
		if (microChipped != other.microChipped)
			return false;
		if (microchipID != other.microchipID)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (relinquishingParty == null) {
			if (other.relinquishingParty != null)
				return false;
		} else if (!relinquishingParty.equals(other.relinquishingParty))
			return false;
		if (Double.doubleToLongBits(totalCost) != Double
				.doubleToLongBits(other.totalCost))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	/*
	 * Getter methods for every field.*/
	public int getCaseNumber() {
		return caseNumber;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public GregorianCalendar getBirthdate() {
		return birthdate;
	}
	public GregorianCalendar getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(GregorianCalendar arrivalDate) {
		this.arrivalDate=arrivalDate;
	}
	public boolean isMicroChipped() {
		return microChipped;
	}
	public long getMicrochipID() {
		return microchipID;
	}
	public String getOwner() {
		return owner;
	}
	public GregorianCalendar getMicroChipDate() {
		return microChipDate;
	}
	public String getRelinquishingParty() {
		return relinquishingParty;
	}
	public int getCageNumber() {
		return cageNumber;
	}
	public String getType(){
		return type;
	}
	/**
	 * Represents the animal object as a string array so it can be written to the CSV file.
	 * @return a String[] that contains the data for this animal to be written to GENERAL_INVENTORY.csv*/
	public String[] toStringArray(){
		return new String[]{caseNumber+"",name,type,age+"",Inventory.printDate(birthdate),Inventory.printDate(arrivalDate),Inventory.booleantoInt(microChipped)+"", microchipID+"",
				Inventory.printDate(microChipDate),owner,relinquishingParty,cageNumber+"","\n"};
	}
	/**
	 * Takes a string array and parses it into an Animal object.  Meant to make objects out of entries in GENERAL_INVENTORY.csv.
	 * It does this by reversing the toStringArray() method field for field.
	 * @param dataset the String array to be parsed
	 * @return an object representation of the data in the array.*/
	public static Animal parseArray(String[] dataset){
		return new Animal(Integer.parseInt(dataset[0]),dataset[1],dataset[2],Integer.parseInt(dataset[3]),Inventory.extractDate(dataset[4]),Inventory.extractDate(dataset[5]),Inventory.booleanFromInt(Integer.parseInt(dataset[6])),
				Long.parseLong(dataset[7]),Inventory.extractDate(dataset[8]),dataset[9],dataset[10],Integer.parseInt(dataset[11]));
	}
	/**
	 * Calculates the total cost of the animal's stay
	 * @return a decimal number that represents the total cost of the animal's stay*/
	public double calculateCost(){
		totalCost=0;
		GregorianCalendar today = new GregorianCalendar();
		if(!microChipped) totalCost+=5;
		//Calculates total daily and monthly costs.  The daily cost is calculated to account for leap years.
		totalCost+=3*(12*(today.get(today.YEAR)-arrivalDate.get(arrivalDate.YEAR))+today.get(today.MONTH)-arrivalDate.get(arrivalDate.MONTH));
		totalCost+=10*(365.25*(today.get(today.YEAR)-arrivalDate.get(arrivalDate.YEAR))+today.get(today.DAY_OF_YEAR)-arrivalDate.get(arrivalDate.DAY_OF_YEAR));
		return totalCost;
	}
	/**
	 * Presents a breakdown of the costs for this animal.  This is the information that goes into the cost report for each animal
	 * @return a two-dimensional array for use as a TableModel*/
	public String[][] tabulateCost(){
		NumberFormat fmt=NumberFormat.getCurrencyInstance();
		GregorianCalendar today = new GregorianCalendar();
		String microChippedCost,totalDailyCost,totalFleaPreventionCost;
		if(!microChipped) microChippedCost="$5.00";
		else microChippedCost="Already Microchipped";
		totalFleaPreventionCost=fmt.format((3*(12*(today.get(today.YEAR)-arrivalDate.get(arrivalDate.YEAR))+today.get(today.MONTH)-arrivalDate.get(arrivalDate.MONTH))));
		totalDailyCost=fmt.format((10*(365.25*(today.get(today.YEAR)-arrivalDate.get(arrivalDate.YEAR))+today.get(today.DAY_OF_YEAR)-arrivalDate.get(arrivalDate.DAY_OF_YEAR))));
		return new String[][]{{"Case Number",caseNumber+""},
				{"Name",name},
				{"Micro-chipped",microChippedCost},
				{"Total Daily Cost",totalDailyCost},
				{"Total Cost for Monthly Flea Prevention",totalFleaPreventionCost},
				{"Total Cost for Stay",fmt.format(calculateCost())}};
	}
}
