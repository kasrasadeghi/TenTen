/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenten;

/**
 *
 * @author DSTIGANT
 */
public class TenTenPiece
{
    private Integer [][] grid;
    
    public static final int LINE_PIECE = 0;
    public static final int CORNER_PIECE = 1;
    public static final int SQUARE_PIECE = 2;
    
    public TenTenPiece()
    {
        int type = (int)(Math.random() * 3);
        
        int size;
        if ( type == LINE_PIECE )
        {
            size = (int)(Math.random() * 5 ) + 1;
        }
        else
        {
            size = (int)(Math.random() * 2) + 2;
        }
        
        int colorIndex = getColorIndex( size, type );
        
        if ( type == LINE_PIECE )
        {
            int orient = (int)(Math.random() * 2 );
            grid = createLinePiece( size, orient == 1, colorIndex );
        }
        else if ( type == SQUARE_PIECE )
        {
            grid = createSquarePiece( size, colorIndex );
        }
        else 
        {
            boolean topRow = Math.random() * 2 >= 1;
            boolean leftColumn = Math.random() * 2 >= 1;
            
            grid = createCornerPiece( size, topRow, leftColumn, colorIndex );
        }
        
        
    }
    
    // createLinePiece
    // creates a grid that is either 1xN or Nx1 filled in with a given fill number
    // inputs:
    // size - the length of the piece
    // horiz - whether the piece should be horizontal or vertical
    // fill - the number to place in each entry of the grid
    // output:
    // either a 1xsize or sizex1 array filled with fill
    private static Integer [][] createLinePiece( int size, boolean horiz, int fill )
    {
        Integer [][] pieceMap = (horiz)? new Integer[1][size] : new Integer[size][1];
        for (int i = 0; i < pieceMap.length; i++)
            for (int j = 0; j < pieceMap[0].length; j++) 
                pieceMap[i][j] = fill;
        return pieceMap;
    }
    
    // createCornerPiece
    // creates a corner piece - that is, a square grid with one row (either the top row or the bottom row)
    // and one column (either the left column or the right column) filled in.  For example:
    // XXX      XXX     X..     ..X
    // X..      ..X     X..     ..X
    // X..      ..X     XXX     XXX
    // inputs:
    // size - the length and width of the piece (it's square, so the length and width will be the same)
    // top - whether to fill the top row (true) or bottom row (false)
    // left - whether to fill the left column (true) or right column (false)
    // fill - what to fill in the filled spaces (the X's above).  All other spaces should be null
    private static Integer [][] createCornerPiece( int size, boolean top, boolean left, int fill )
    {
        Integer[][] pieceMap = new Integer[size][size];
        for ( int i = 0; i < pieceMap.length; i++)
            for ( int j = 0; j < pieceMap.length; j++) {
                pieceMap[i][j] = 
                        (top && i==0)? fill :
                        (!top && i==pieceMap.length-1)? fill :
                        (left && j==0)? fill : 
                        (!left && j==pieceMap.length-1)? fill: -1;
                if (pieceMap[i][j] == -1) pieceMap[i][j] = null;
            }
        return pieceMap;
    }
    
    // createSquarePiece
    // creates a square grid filled in with the given fill number.
    // For example, if size is 3 and fill is 2 then the function should return
    // 2 2 2
    // 2 2 2
    // 2 2 2
    // inputs:
    // size - the size of the square
    // fill - the number to fill the square with
    // output - a new size x size array filled with fill
    private static Integer [][] createSquarePiece( int size, int fill )
    {
        Integer[][] pieceMap = new Integer[size][size];
        for(int i = 0; i < pieceMap.length; i++)
            for(int j = 0; j < pieceMap.length; j++) 
                pieceMap[i][j] = fill;
        return pieceMap;
    }
    
    protected static Integer getColorIndex( int size, int type )
    {
        if ( type == LINE_PIECE ) return size-1;
        else if ( type == SQUARE_PIECE ) {
            if ( size == 1 ) return 0;
            else return size + 3;
        }
        else {
            if ( size == 1 ) return 0;
            else return size + 5;
        }
    }
    
    public Integer [][] getGrid() { return grid; }
    
    private static void printPiece( Integer [][] piece )
    {
        for ( int i = 0; i < piece.length; i++ )
        {
            for ( int j = 0; j < piece[i].length; j++ )
            {
                if ( piece[i][j] != null )
                    System.out.print( piece[i][j] );
                else
                    System.out.print( "." );
            }
            System.out.println();
        }
    }
    
    public static void testPieceCreation()
    {
        Integer [][] p = createLinePiece( 5, false, 4 );
        printPiece( p );
        System.out.println("");
        
        p = createLinePiece( 4, true, 3 );
        printPiece( p );
        System.out.println("");
        
        p = createLinePiece( 1, false, 0 );
        printPiece( p );
        System.out.println("");
        
        
        p = createSquarePiece( 2, 5 );
        printPiece( p );
        System.out.println("");
        
        p = createSquarePiece( 3, 6 );
        printPiece( p );
        System.out.println("");
        
        for ( int k = 2; k < 4; k++ )
        {
            for ( int i = 0; i < 2; i++ )
            {
                for ( int j = 0; j < 2; j++ )
                {
                    p = createCornerPiece( k, i%2 == 0, j%2 == 0, 7+k-2 );
                    printPiece( p );
                    System.out.println("");
                }
            }
        }
    }
}
