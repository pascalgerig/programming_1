/* ************************************************************************* *\
*                Programmierung 1 HS 2016 - Serie 4-1                         * 
\* ************************************************************************* */

import java.util.Arrays;
import java.util.Scanner;


public class VierGewinnt
{

	public static final int COLS = 7;
	public static final int ROWS = 6;

	private Token[][] board = new Token[ COLS ][ ROWS ]; // 7 columns with 6 fields each
	private IPlayer[] players = new IPlayer[ 2 ]; //�two players

	/** initialize board and players and start the game */
	public void play()
	{
		// initialize the board
		for ( Token[] column : this.board ) {
			Arrays.fill( column, Token.empty );
		}

		/* initialize players */
		players[ 0 ] = new HumanPlayer();
		System.out.print( "Play against a human opponent? (y / n) " );
		String opponent = new Scanner( System.in ).nextLine().toLowerCase();
		while ( ( 1 != opponent.length() ) || ( -1 == ( "yn".indexOf ( opponent ) ) ) ) {
			System.out.print( "Can't understand your answer. Play against a human opponent? (y / n) " );
			opponent = new Scanner( System.in ).nextLine().toLowerCase();
		}
		if ( opponent.equals( "y" ) ) {
			players[ 1 ] = new HumanPlayer();
		} else {
			players[ 1 ] = new ComputerPlayer();
		}
		players[ 0 ].setToken( Token.player1 );
		players[ 1 ].setToken( Token.player2 );

		/* play... */
		boolean solved = false;
		int currentPlayer = ( new java.util.Random() ).nextInt( 2 );  //choose randomly who begins
		System.out.println( "current player: " + currentPlayer );
		int insertCol, insertRow; // starting from 0
		while ( !solved && !this.isBoardFull() ) {
			// get player's next "move"
			// note that we pass only a copy of the board as an argument,
			// otherwise the player would be able to manipulate the board and cheat!
			insertCol = players[ currentPlayer ].getNextColumn( getCopyOfBoard() );
			// insert the token and get the row where it landed
			insertRow = this.insertToken( insertCol, players[ currentPlayer ].getToken() );
			//�check if the game is over
			solved = this.checkVierGewinnt( insertCol, insertRow );
			//switch to other player
			if ( !solved )
				currentPlayer = ( currentPlayer + 1 ) % 2;
		}
		System.out.println( displayBoard( this.board ) );
		if ( solved )
			System.out.println( "Player " + players[ currentPlayer ].getToken() + " wins!" );
		else
			System.out.println( "Draw! Game over." );
	}


	/**
	 * Inserts the token at the specified column (if possible)
	 * @param column the column to insert the token
	 * @param token the players token
	 * @return the row where the token landed 
	 */
	private int insertToken( int column, Token tok )
	{
		//TODO: Your code goes here
		if(column < 0 || column >= COLS)
			System.exit(1);
		int row = 0;
		while (board[column][row] != Token.empty)
			row++;
		if(row >= ROWS)
			System.exit(1);
		
		board[column][row] = tok;
		return row; 
	}


	/**
	 * Checks if every position is occupied 
	 * @returns true, iff the board is full.
	 */
	private boolean isBoardFull()
	{
		int col = 0;
		int counter = 0;
		boolean[] full = new boolean [COLS];
		for (int i = 0; i < COLS; i++)
			full[i] = false;
		for (int j = 0; j < COLS; j++) {
			if (board[j][ROWS-1] != Token.empty)
			{
				full[j] = true;
			}	
		}
		while (col < COLS)
		{
			if (!full[col])
			{
				counter ++;
			}
			col++;
		}
		
		if (counter > 0)
			return false;
		else
			return true;
	}


	/**
	 * Checks for at least four equal tokens in a row in
	 * either direction, starting from the given position. 
	 */
	private boolean checkVierGewinnt( int col, int row )
	{
		//TODO: Your code goes here
		if(checkVierGewinntColumn(col, row) || checkVierGewinntRow(col, row) || checkVierGewinntDiagonalTop(col, row) || checkVierGewinntDiagonalBottom(col, row))
			return true;
		else
			return false;
	}

	private boolean checkVierGewinntColumn(int col, int row)
	{
		if(row < 3)
		{
			return false;
		}
		else
		{
			boolean check = true;
			int i = 1;
			while (i < 4 && check)
			{
				if(board[col][row] != board[col][row - i])
					check = false;
				i++;
			}
			return check;
		}
	}

	
	private boolean checkVierGewinntRow(int col, int row)
	{
		int i = 1;
		while(col - i >= 0 && board[col][row] == board[col - i][row])
		{
			i++;
		}
		i--;
		
		int j = 1;
		while(col + j < 7  && board[col][row] == board[col + j][row])
		{
			j++;
		}
		
		if( i + j >= 4)
			return true;
		else
			return false;
	}
	
	private boolean checkVierGewinntDiagonalTop(int col, int row)
	{
		int i = 1;
		while(row - i >= 0 && col + i < 7 && board[col][row] == board[col + i][row - i])
		{
			i++;
		}
		i--;
		
		int j = 1;
		while(row + j < 6 && col - j >= 0  && board[col][row] == board[col - j][row + j])
		{
			j++;
		}
		
		if( i + j >= 4)
			return true;
		else
			return false;
	}
	
	private boolean checkVierGewinntDiagonalBottom(int col, int row)
	{
		int i = 1;
		while(row + i < 6 && col + i < 7 && board[col][row] == board[col + i][row + i])
		{
			i++;
		}
		i--;
		
		int j = 1;
		while(row - j >= 0 && col - j >= 0  && board[col][row] == board[col - j][row - j])
		{
			j++;
		}
		
		if( i + j >= 4)
			return true;
		else
			return false;
	}
	
	/** Returns a (deep) copy of the board array */
	private Token[][] getCopyOfBoard()
	{
		Token[][] copiedBoard = new Token[ COLS ][ ROWS ];
		for ( int i = 0; i < copiedBoard.length; i++ ) {
			for ( int j = 0; j < copiedBoard[ i ].length; j++ ) {
				copiedBoard[ i ][ j ] = this.board[ i ][ j ];
			}
		}
		return copiedBoard;
	}


	/** returns a graphical representation of the board */
	public static String displayBoard( Token[][] myBoard )
	{
		String rowDelimiter = "+";
		String rowNumbering = " ";
		for ( int col = 0; col < myBoard.length; col++ ) {
			rowDelimiter += "---+";
			rowNumbering += " " + ( col + 1 ) + "  ";
		}
		rowDelimiter += "\n";

		String rowStr;
		String presentation = rowDelimiter;
		for ( int row = myBoard[ 0 ].length - 1; row >= 0; row-- ) {
			rowStr = "| ";
			for ( int col = 0; col < myBoard.length; col++ ) {
				rowStr += myBoard[ col ][ row ].toString() + " | ";
			}
			presentation += rowStr + "\n" + rowDelimiter;
		}
		presentation += rowNumbering;
		return presentation;
	}



	/** main method, starts the program */
	public static void main( String args[] )
	{
		VierGewinnt game = new VierGewinnt();
		game.play();
	}
}