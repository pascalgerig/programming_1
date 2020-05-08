/* ************************************************************************* *\
*                Programmierung 1 HS 2016 - Serie 2-1                         * 
\* ************************************************************************* */

import java.text.DecimalFormat;
import java.util.Random;
import java.lang.String;

public class RandomISBN
{

	public static void main( String args[] )
	{
		// TODO: Insert your code here!
		String ISBN1 = RandomISBN.makeISBN();
		String ISBN2 = RandomISBN.makeISBN();
		String ISBN3 = RandomISBN.makeISBN();
		System.out.println(ISBN1);
		System.out.println(ISBN2);
		System.out.println(ISBN3);
	}

	/** generates and returns a random ISBN in the format XX-XXX-XX-C */
	public static String makeISBN()
	{
		// Do NOT change the declaration of the following variables!
		String laendercode;
		String bandnr;
		String verlagsnr;
		String checksum;
		// TODO: Insert your code here!
		// I add some more variables!
		int lcode;
		int bandnummer;
		int verlagsnummer;		
		int checksumme;
		int l1;
		int l2;
		int b1;
		int b2;
		int b3;
		int v1;
		int v2;
		String land1;
		String land2;
		String band1;
		String band2;
		String band3;
		String verlag1;
		String verlag2;

		//Generate the random numbers
		Random generator = new Random();
		lcode = generator.nextInt(98)+1;
		bandnummer = generator.nextInt(900)+100;
		verlagsnummer = generator.nextInt(98)+1;
		
		//formatting of the random numbers
		DecimalFormat einstellig = new DecimalFormat ("#0");
		DecimalFormat zweistellig = new DecimalFormat ("#00");
		DecimalFormat dreistellig = new DecimalFormat ("#00");
		
		laendercode = zweistellig.format(lcode);
		verlagsnr = zweistellig.format(verlagsnummer);
		bandnr = dreistellig.format(bandnummer);
		
		//Calculating checksum
		l1 = Character.getNumericValue(laendercode.charAt(0));
		l2 = Character.getNumericValue(laendercode.charAt(1));
		b1 = Character.getNumericValue(bandnr.charAt(0));
		b2 = Character.getNumericValue(bandnr.charAt(1));
		b3 = Character.getNumericValue(bandnr.charAt(2));
		v1 = Character.getNumericValue(verlagsnr.charAt(0));
		v2 = Character.getNumericValue(verlagsnr.charAt(1));
		
		checksumme = 	(RandomISBN.hashOp(l1) + l2 + RandomISBN.hashOp(b1) + b2 + RandomISBN.hashOp(b3) 
						+ v1 + RandomISBN.hashOp(v2))%10;
		
		checksum = einstellig.format(checksumme);
		// Do not change the following line
		return laendercode + "-" + bandnr + "-" + verlagsnr + "-" + checksum;
	}

	/** multiplies i with 2 and subtracts 9 if result is >= 10 */
	public static int hashOp( int i )
	{
		// Do NOT change this method!
		int doubled = 2 * i;
		if ( doubled >= 10 ) {
			doubled = doubled - 9;
		}
		return doubled;
	}
}
