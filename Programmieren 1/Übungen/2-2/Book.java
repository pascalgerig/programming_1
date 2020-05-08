/* ************************************************************************* *\
*                Programmierung 1 HS 2016 - Serie 2-2                         * 
\* ************************************************************************* */

import java.util.Date;
import java.util.Scanner;
import java.text.*;

public class Book
{
	private int id;
	private String title;
	private String author;
	private Date dateOfPublication;

	public static final String DATE_FORMAT = "dd.MM.yyyy";

	//--- constructors ---
	// TODO: Insert your code here!
	public Book(int buchid, String buchtitel, String buchautor, String publikationsdatum) 
	{
		id = buchid;
		title = buchtitel;
		author = buchautor;
		dateOfPublication = stringToDate(publikationsdatum);
	}
	
	/** Returns the age of the book in days since publication */
	public int age()
	{
		// TODO: Insert your code here!
		Date current = new Date();
		long time;
		int timePassed;
		time = current.getTime() - dateOfPublication.getTime();
		time = time/86400000;
		timePassed = (int) time;
		
		return timePassed;
	}	
	
	
	/** Returns a String representation of the book */
	public String toString()
	{
		// TODO: Insert your code here!
		String result;
		String resultId = Integer.toString(id);
		String resultDateOfPublication = dateToString(dateOfPublication);
		result = resultId + ", " + title + ", " + author + ", " + resultDateOfPublication;
		return result;
	}

	/** Reads all book data from user input */
	public void input() 
	{
		Scanner scn = new Scanner( System.in );
		System.out.print( "Please enter id: " );

		// TODO: Insert your code here!
		id = scn.nextInt();
		System.out.print( "Please enter title: " );
		title = scn.next();
		System.out.print( "Please enter author: " );
		author = scn.next();
		System.out.print( "Please enter date of publication: " );
		dateOfPublication = stringToDate(scn.next());
	}

	//--- Get-/Set-methods ---
	// TODO: Insert your code here!
	public int getID ()
	{
		return id;
	}
	
	public int setID (int i)
	{
		id = i;
		return id;
	}
	
	
	public String getTitle()
	{
		return title;
	}
	
	public String setTitle (String i)
	{
		title = i;
		return title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public String setAuthor(String i)
	{
		author = i;
		return author;
	}
	
	public Date getDateOfPublication()
	{
		return dateOfPublication;
	}
	
	public Date setDateOfPublication(String i)
	{
		dateOfPublication = stringToDate(i);
		return dateOfPublication;
	}
	

	//--- helper methods -- DO NOT CHANGE ------------------------------------
	/** Converts the Date object d into a String object */
	public static String dateToString( Date d )
	{
		SimpleDateFormat fmt = new SimpleDateFormat( DATE_FORMAT );
		return fmt.format( d );
	}

	/** Converts the String object s into a Date object */
	public static Date stringToDate( String s ) 
	{
		Date r = null;
		try {
			SimpleDateFormat fmt = new SimpleDateFormat( DATE_FORMAT );
			r = fmt.parse( s );
		} catch ( ParseException e ){
			System.err.println( e );
			System.exit(1);
		}
		return r;
	}
}
