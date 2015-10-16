/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenten;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DSTIGANT
 */
public class TenTenGame
{
    private TenTenPiece [] pieces;
    private Integer [][] board;
    private boolean gameOver;
    private int score;
    
    // TenTenGame - initializes a new 1010! game
    // To do:
    // 1. Set the score to 0
    // 2. Set gameOver to false
    // 3. Create a new board (an array of Integers) that is 10x10
    // 4. Initialize all entries in the new board to be empty (ie set them to null)
    // 5. Create a new array of 3 TenTenPieces
    // 6. Initialize each piece to a new TenTenPiece
    public TenTenGame()
    {
        // 1. Set the score to 0
        score = 0;
        
        // 2. Set gameOver to false
        gameOver = false;
        
        // 3. Create a new board (an array of Integers) that is 10x10
        board = new Integer[10][10];
        
        // 4. Initialize all entries in the new board to be empty (ie set them to null)
        for (int i = 0; i < board.length; i++) 
            for (int j = 0; j < board[i].length; j++) 
                board[i][j] = null;
        
        // 5. Create a new array of 3 TenTenPieces
        
        
        // 6. Initialize each piece to a new TenTenPiece
        
    }
    
    public int getScore() { return score; }
    
    public void loadSampleBoard( String file )
    {
        try {
            Scanner in = new Scanner( new File( file ) );
            for ( int i = 0; i < 10; i++ )
            {
                for ( int j = 0; j < 10; j++ )
                {
                    if ( in.hasNextInt() )
                    {
                        board[i][j] = in.nextInt();
                    }
                    else
                    {
                        in.next();
                        board[i][j] = null;
                    }
                }
            }
            clearFinishedRowsAndColumns();
            gameOver = detectGameOver();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TenTenGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void saveSampleBoard()
    {
        try {
            PrintStream out = new PrintStream( new File( "SampleBoard.txt" ) );
            for ( int i = 0; i < 10; i++ )
            {
                for ( int j = 0; j < 10; j++ )
                {
                    if ( board[i][j] == null )
                    {
                        out.print( ". " );
                    }
                    else
                    {
                        out.print( board[i][j] + " " );
                    }
                }
                out.println();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TenTenGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // canPlay - determines if a given piece can be played at a given location
    // inputs:
    // the index of the piece to play
    // the row and column within the board to play the top left corner of the piece
    // ie piece[0][0] should go at board[ULCr][ULCc]
    //
    // the piece can be placed at this location if:
    // no entry in the piece array would land out of the bounds of the board
    // no non-null entry in the piece array would fall on a non-null entry of the board
    // 
    // output:
    // true if the piece can be placed at this location, false otherwise
    public boolean canPlay( int index, int ULCr, int ULCc )
    {
        // get the 2d grid from the piece
        
        
        // look at each entry in the piece grid
        
                // based on the row and column index in the piece grid
                // and the ULCr and ULCc, calculate the board row and column
                // where this entry will land.
                
                
                // make sure the board coordinates are in bounds
                
                
                // make sure that if the piece entry is non-null the board entry
                // is also non-null
                
            
        
        return true;
    }
    
    // playPiece
    // plays the indicated piece so that its ULC lands at the indicated position on the board
    // first, check to see if:
    // 0. The piece index is valid (ie 0, 1, or 2)
    // 1. The indicated piece hasn't already been played (ie the piece isn't null)
    // 2. The piece CAN be played at the given location
    // Then:
    // 1. copy each non-null entry in the piece grid onto the corresponding place on the board
    // 2. set the entry in the pieces array to null to indicate that the piece has been played
    // 3. check to see if we need to get three new pieces (ie all current pieces have been played)
    // 4. check to see if the game is over
    // 5. remove any completed rows and/or columns
    // inputs:
    // index - the index of the piece to be played (0, 1, or 2)
    // ULCr, ULCc - the location on the board that the upper left corner of the piece will be played on
    public void playPiece( int index, int ULCr, int ULCc )
    {
        // first validate that this move is legal
        
            // get the grid from the piece
            
            // run through all the entries of the piece grid, placing 
            
            
            // set the played piece to be played (ie null)
            
            // check if we need to get new pieces
            
            
            // check if the game is over (set gameOver to the result of function detectGameOver()
            
        
        
        // check to see if any rows or columns are complete
        
    }
    
    // clearFinishedRowsAndColumns()
    // checks to see if any rows or columns are finished and clears them
    // Note: You must figure out which columns you are going to clear before you clear
    // any ROWS because clearing a row will make all columns incomplete (even if they were complete before
    // you cleared the row)
    protected void clearFinishedRowsAndColumns()
    {
        // declare and create two arrays of booleans, one to store which rows have been completed and one to
        // store which columns have been completed.
        
        
        // check each row and column to see if it's complete.  If it is, mark the corresponding entry
        // in the array(s) that you created above.
        
        
        // for each row (and column), if it is marked as complete, remove it
        
    }
    
    // allPiecesPlayed
    // checks to see if all three current pieces have been played
    // inputs: none
    // output: true if all pieces have been played, false otherwise
    protected boolean allPiecesPlayed()
    {
       return false;
    }
    
    // getThreeNewPieces
    // creates three new pieces and stores them in the pieces array.
    protected void getThreeNewPieces()
    {
        
    }
    
    
    
    public boolean isGameOver() { return gameOver; }
    
    // detectGameOver
    // figures out if the game is over - that is, none of the remaining pieces can be placed
    // anywhere in the grid
    // input: none
    // output:
    // true if there are no non-null pieces that can be placed somewhere in the grid
    // hint: use function canPlay to help you decide i
    protected boolean detectGameOver()
    {
        return false;
    }
    
    public Integer [][] getBoard() { return board; }
    
    public TenTenPiece getPiece( int i )
    {
        return pieces[i];
    }

    // rowComplete
    // checks to see if the indicated row is complete (ie no null values in the row)
    // input:
    // i - the row to check
    // output:
    // true if every square in row i is filled, false otherwise
    private boolean rowComplete(int i)
    {
        return false;
    }

    // colComplete
    // like rowComplete, but checks column instead of a row
    private boolean colComplete(int i)
    {
        return false;
    }

    // removeRow
    // removes the contents of every square in a row
    // input:
    // i - the row to remove
    private void removeRow(int i)
    {
        
    }

    // removeCol
    // like removeRow but for a column instead of a row
    private void removeCol(int i)
    {
        
    }
}
