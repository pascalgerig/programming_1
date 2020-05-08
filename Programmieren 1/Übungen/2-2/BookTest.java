//import Book.java;
public class BookTest
{
	public static void main(String[] args)
	{
		Book buch = new Book(2,"Harry Potter","J.K. Rowling","26.06.1997"); 
		System.out.println("Information about the Book: ");
		System.out.println(buch.toString());
		System.out.println("The book released " + buch.age() + " days ago.");
		buch.input();
		System.out.println("old book ID: " + buch.getID());
		System.out.println("new book ID: " + buch.setID(123456));
		System.out.println("old book title: " + buch.getTitle());
		System.out.println("new book title: " + buch.setTitle("Lord of the rings"));
		System.out.println("new book title: " + buch.setTitle("Lord of the rings"));
		System.out.println("old author: " + buch.getAuthor());
		System.out.println("new author: " + buch.setAuthor("J.R.R Tolkien"));
		System.out.println("old date of publication: " + buch.getDateOfPublication());
		System.out.println("new date of publication: " + buch.setDateOfPublication("01.01.1954"));
		
	}
}

