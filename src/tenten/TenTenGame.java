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
        pieces = new TenTenPiece[3];
        
        // 6. Initialize each piece to a new TenTenPiece
        for (int i = 0; i < pieces.length; i++)
            pieces[i] = new TenTenPiece();
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
        Integer[][] pieceMap = pieces[index].getGrid();
        
        boolean output = true;
        // look at each entry in the piece grid
        for (int i = 0; i < pieceMap.length; i++) 
            for (int j = 0; j < pieceMap[i].length; j++) {
                // based on the row and column index in the piece grid
                // and the ULCr and ULCc, calculate the board row and column
                // where this entry will land.
                
                // k: boardrow of piece = ULCr + i;
                // k: boardcol of piece = ULCc + j;
                
                // make sure the board coordinates are in bounds
                if ( ULCr + i > board.length || ULCc + j > board[i].length)
                    output = false;
                
                
                // make sure that if the piece entry is non-null the board entry
                // is also non-null 
                // k: ...it returns false
                if ( pieceMap[i][j] != null && board[ULCr + i][ULCc + j] != null)
                    output = false;
            }
        //System.out.println("canplay:" + output);
        return output;
    }
    
    // playPiece
    // plays the indicated piece so that its ULC lands at the indicated position on the board
    // inputs:
    // index - the index of the piece to be played (0, 1, or 2)
    // ULCr, ULCc - the location on the board that the upper left corner of the piece will be played on
    public void playPiece( int index, int ULCr, int ULCc )
    {
        System.out.println("playpiece is running");
        
        // first validate that this move is legal
        // first, check to see if:
        // 1. The piece index is valid (ie 0, 1, or 2)
        // 2. The indicated piece hasn't already been played (ie the piece isn't null)
        // 3. The piece CAN be played at the given location
        if (index != 0 && index != 1 && index != 2) return;
        if ( pieces[index] == null) return;
        if ( !canPlay(index, ULCr, ULCc)) return;
        System.out.println("playpiece is running because all of the conditions checked out");
        // Then:
        // 1. copy each non-null entry in the piece grid onto the corresponding place on the board
        // 2. set the entry in the pieces array to null to indicate that the piece has been played
        // 3. check to see if we need to get three new pieces (ie all current pieces have been played)
        // 4. check to see if the game is over
        // 5. remove any completed rows and/or columns
        
            // 1. copy each non-null entry in the piece grid onto the corresponding place on the board
        // get the grid from the piece
        Integer[][] pieceMap = pieces[index].getGrid();
        // run through all the entries of the piece grid, placing 
        for (int i = 0; i < pieceMap.length; i++) {
            for (int j = 0; j < pieceMap[i].length; j++) {
                if (pieceMap[i][j] != null) {
                    board[ULCr + i][ULCc + j] = pieceMap[i][j];
                    ++score;
                }
            }
        }
        
            // 2. set the entry in the pieces array to null to indicate that the piece has been played
        // set the played piece to be played (ie null)
        pieces[index] = null;
        
            // 3. check to see if we need to get three new pieces (ie all current pieces have been played)
        // check if we need to get new pieces
        if (allPiecesPlayed()) getThreeNewPieces();
        
            // 4. check to see if the game is over
        // check if the game is over (set gameOver to the result of function detectGameOver()
        gameOver = detectGameOver();
        
            // 5. remove any completed rows and/or columns
        // check to see if any rows or columns are complete
        clearFinishedRowsAndColumns();
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
        boolean[] rowCompleteChecks = new boolean[board.length];
        boolean[] colCompleteChecks = new boolean[board[0].length];
        
        // check each row and column to see if it's complete.  If it is, mark the corresponding entry
        // in the array(s) that you created above.
        for (int i = 0; i < board.length; i++) 
            if ( rowComplete(i)) { rowCompleteChecks[i] = true; score+= 10; }
        for (int j = 0; j < board[0].length; j++)
            if ( colComplete(j)) { colCompleteChecks[j] = true; score+= 10; }
        
        // for each row (and column), if it is marked as complete, remove it
        for (int i = 0; i < board.length; i++) 
            if (rowCompleteChecks[i]) removeRow(i);
        for (int j = 0; j < board[0].length; j++) 
            if (colCompleteChecks[j]) removeCol(j);
    }
    
    // allPiecesPlayed
    // checks to see if all three current pieces have been played
    // inputs: none
    // output: true if all pieces have been played, false otherwise
    protected boolean allPiecesPlayed()
    {
       for (int i = 0; i < 3; i++)
           if (pieces[i] != null) return false;
       return true;
    }
    
    // getThreeNewPieces
    // creates three new pieces and stores them in the pieces array.
    protected void getThreeNewPieces()
    {
        for (int i = 0; i < 3; i++) 
            pieces[i] = new TenTenPiece();
    }
    
    public boolean isGameOver() 
    { 
//        System.out.println("gameover is:" + gameOver);
        return gameOver; 
    }
    
    // detectGameOver
    // figures out if the game is over - that is, none of the remaining pieces can be placed
    // anywhere in the grid
    // input: none
    // output:
    // true if there are no non-null pieces that can be placed somewhere in the grid
    // hint: use function canPlay to help you decide if ones of the pieces can be played somewhere
    protected boolean detectGameOver()
    {
        boolean output = true;
        for (int k = 0; k < 3; k++)
            if (pieces[k] != null)
                for (int i = 0; i < board.length; i++)
                    for (int j = 0; j < board[i].length; j++)
                        if(canPlay(k,i,j)) output = false;
        return output;
    }
    
    public Integer [][] getBoard() { return board; }
    
    public TenTenPiece getPiece( int i )
    {
        return pieces[i];
    }

    // rowComplete
    // checks to see if the indicated row is complete (ie no null values in the row)
    // input:
    // rowIndex - the row to check
    // output:
    // true if every square in row i is filled, false otherwise
    private boolean rowComplete(int rowIndex)
    {
        for( int c = 0; c < board[0].length; c++)
            if (board[rowIndex][c] == null) return false;
        return true;
    }

    // colComplete
    // like rowComplete, but checks column instead of a row
    private boolean colComplete(int colIndex)
    {
        for( int r = 0; r < board[0].length; r++)
            if (board[r][colIndex] == null) return false;
        return true;
    }

    // removeRow
    // removes the contents of every square in a row
    // input:
    // rowIndex - the row to remove
    private void removeRow(int rowIndex)
    {
        if (rowComplete(rowIndex)) 
            for (int i = 0; i < board[0].length; i++) 
                board[rowIndex][i] = null;
    }

    // removeCol
    // like removeRow but for a column instead of a row
    private void removeCol(int colIndex)
    {
        if (colComplete(colIndex)) 
            for (int i = 0; i < board.length; i++) 
                board[i][colIndex] = null;
    }
}
