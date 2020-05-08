import java.util.*;
import java.io.*;

public class AddressFileLabelled extends AddressFile{
	AddressFileLabelled(String filename)
	{
		super(filename);
	}
	
	/**
	 * Converts an Address obj into a String 
	 * 
	 * @param addr Obj of type Address
	 * @return String representing addr
	 */
	protected String toLine(Address addr) 
	{
		int id = addr.getId();
		String name = addr.getName();
		String street = addr.getStreet(); 
		String city = addr.getCity();
		int zipCode = addr.getZipCode();
		
		String answer = "id: " + id + "; name: " + name + "; street: " + street + "; zip: " + zipCode + "; city: " + city + ";";
		return answer;
	}
	
	/**
	 * Converts a String into an Address obj
	 * @param line the String to be converted
	 * @return Obj of type Address
	 */
	protected Address parseLine(String line) throws AddressFileException
	{
		try
		{
			int id = Integer.parseInt(returnValue(line, "id"));
			String name = returnValue(line, "name");
			String street = returnValue(line, "street");
			int zipCode = Integer.parseInt(returnValue(line, "zip"));
			String city = returnValue(line, "city");
			Address addr = new Address(id, name, street, zipCode, city);
			return addr;
		}
		catch (NumberFormatException | NoSuchElementException e)
		{
			throw new AddressFileException("Error");
		}
	}
	
	private String returnValue(String line, String label)
	{
		Scanner scan = new Scanner(line); 
		scan.findInLine(label+"[\\s]*:[\\s]*([^;]*)"); 
		return scan.match().group(1).trim();
	}
}
