import java.io.*;
import java.util.*;
import java.util.Scanner;

public class AddressFile {

	String filename;

	
	AddressFile(String filename)
	{
		this.filename = filename;
	}
	
	/**
	 * Converts an Address obj into a String 
	 * 
	 * @param addr Obj of type Address
	 * @return String representing addr
	 */
	protected String toLine(Address addr)
	{
		return addr.toString();
	}
	
	/**
	 * Converts a String into an Address obj
	 * @param line the String to be converted
	 * @return Obj of type Address
	 */
	protected Address parseLine(String line) throws AddressFileException
	{
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		
		try
		{
			int id = Integer.parseInt(scan.next().trim());
			String name = scan.next().trim();
			String street = scan.next().trim();
			int zipCode = Integer.parseInt(scan.next().trim());
			String city = scan.next().trim();
			
			Address addr = new Address(id, name, street, zipCode, city);
			scan.close();
			return addr;
		
		}
		catch(NumberFormatException | NoSuchElementException e)
		{
			throw new AddressFileException("Error");
		}
	}
	
	/**
	 * Saves the Obj. in the ArrayList into @filename
	 * @param addresses the ArrayList
	 */
	public void save (ArrayList<Address> addresses)
	{
		try
		{
			PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		
			for(int i = 0; i<addresses.size() - 1 ; i++)
			{
				file.println(toLine(addresses.get(i)));
			}
			file.close();
		}	
		catch (IOException e)
		{
			System.out.println("Couldn't save Objects in " + filename);
		}
	}
	
	/**
	 * reads @filename and converts each line into an Address Obj
	 * @return ArrayList containing all the Generated Address Obj.
	 */
	public ArrayList<Address> load() throws AddressFileException
	{
		try
		{
		Scanner scan = new Scanner(new File(filename));
		ArrayList<Address> list = new ArrayList<Address>();
		String line;
		int count = 0;
		while (scan.hasNextLine())
		{
			line = scan.nextLine();
			list.add(count, parseLine(line));
			count++;
		}
		scan.close();
		return list;
		}
		catch (FileNotFoundException e)
		{
			throw new AddressFileException("Error");
		}
	}
}
