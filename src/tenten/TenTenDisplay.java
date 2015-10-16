/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenten;

import apcscvm.DefaultControl;
import apcscvm.GraphicsUtilityFunctions;
import apcscvm.Mouse;
import apcscvm.View;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author DSTIGANT
 */
public class TenTenDisplay extends DefaultControl<TenTenGame> implements View<TenTenGame>
{

    private int selectedPieceIndex;
    private int selectedRow, selectedCol;
    private int height, width;
    
    protected final static Color INDIGO = new Color( 128, 0, 255 );
    protected final static Color VIOLET = new Color( 128, 0, 128 );
    
    public TenTenDisplay()
    {
        selectedPieceIndex = -1;
        selectedRow = selectedCol = -1;
    }
    
    protected static Color getColor( int colorIndex )
    {
        switch( colorIndex )
        {
            case 0: return Color.RED;
            case 1: return Color.ORANGE;
            case 2: return Color.YELLOW;
            case 3: return Color.GREEN;
            case 4: return Color.BLUE;
            case 5: return INDIGO;
            case 6: return VIOLET;
            case 7: return Color.MAGENTA;
            default: return Color.CYAN;
        }
    }
    
    // paintBoard
    // paints the squares in the board.  For each square that is null, paint a gray square.  For each square that is
    // not null, use getColor to get the color for the square and paint a square of that color.
    // the other parameters indicate the top left corner of the board, the width and height of a square
    // and the vertical and horizontal spacing between squares.  For example, if sw and sh are 23 and shs and svs are 2
    // each square should be 23x23 pixels and there should be 2 pixels between each square
    // inputs:
    // board - the array of Integers which describe the squares to be painted
    // g - the Graphics object
    // ULCx, ULCy - the location on the window for the upper left hand corner of the board
    // sw, sh - the width and height of the squares
    // sws, shs - the horizontal and vertical spacing between the squares
    public void paintBoard( Integer [][] board, Graphics g, int tlx, int tly, int sw, int sh, int sws, int shs )
    {
        // the total horizontal distance bewteen the top left corner of one square and the TLC of the square to the right:
        int squareHOffset = sw + sws;  // replace the 0 with the correct calculation
        
        // the total vertical distance between the TLC of one square and the TLC of the next square down
        int squareVOffset = sh + shs;  // replace the 0 with the correct calculation
        
        // for each square in board, if the square is null, draw a grey square, if not, draw a colored square
        // outline any square drawn in black.
        
        for (int i = 0; i < board.length; i++) 
            for (int j = 0; j < board[i].length; j++) {
                g.setColor((board[i][j] == null)? Color.GRAY : getColor(board[i][j]));
                g.fillRect(tlx + i*sws, tly + i*shs, sw, sh);
            }
    }
    
    // paintPiece
    // like paintBoard, but null entries should not be displayed at all.
    public void paintPiece( Integer [][] piece, Graphics g, int ULCx, int ULCy, int sw, int sh, int shs, int svs )
    {
        // the total horizontal distance bewteen the top left corner of one square and the TLC of the square to the right:
        int squareHOffset = 0;  // replace the 0 with the correct calculation
        
        // the total vertical distance between the TLC of one square and the TLC of the next square down
        int squareVOffset = 0;  // replace the 0 with the correct calculation
        
        // for each non-null square in the piece, draw and outline it.
        
    }
    
    @Override
    public void paint(TenTenGame m, Graphics g, int w, int h)
    {
        height = h;
        width = w;
        
        Integer [][] board = m.getBoard();
        
        int squareHOffset = (int)(w/20.0);
        int squareHSpacing = (int)(w/250.0);
        int squareVOffset = (int)(h/20.0);
        int squareVSpacing = (int)(h/250.0);
        int squareWidth = squareHOffset - squareHSpacing;
        int squareHeight = squareVOffset - squareVSpacing;
        
        int ULCxBoard = w/2 - 5*squareHOffset;
        int ULCyBoard = h/2 - 7*squareVOffset;
        
        paintBoard( m.getBoard(), g, ULCxBoard, ULCyBoard, squareWidth, squareHeight, squareHSpacing, squareVSpacing );
        
        for ( int i = 0; i < 3; i++ )
        {
            
            if ( m.getPiece(i) != null )
            {
                Integer [][] piece = m.getPiece( i ).getGrid();
                if ( i == selectedPieceIndex )
                {
                    int x = Mouse.getX();
                    int y = Mouse.getY();
                    
                    int ULCx = x - squareHOffset/2 - squareHOffset*selectedCol;
                    int ULCy = y - squareVOffset/2 - squareVOffset*selectedRow;
                    
                    paintPiece( piece, g, ULCx, ULCy, squareWidth, squareHeight, squareHSpacing, squareVSpacing );
                }
                else 
                {
                    paintPiece( piece, g, w/2 - 17*squareHOffset/2 + i*6*squareHOffset, 7*h/10,
                            squareWidth, squareHeight, squareHSpacing, squareVSpacing );
                    
                }
            }
        }
        
        if ( m.isGameOver() ) 
        {
            g.setColor( Color.BLACK );
            for ( int i = -2; i <= 2; i++ )
            {
                for ( int j = -2; j <= 2; j++ )
                {
                    GraphicsUtilityFunctions.drawStringWithFontInRectangle(
                    g,
                    "Game Over",
                    GraphicsUtilityFunctions.getFont( 50 ),
                    w/2-125-i, 0-j, 250, 100
                    );
                }
            }
            g.setColor( Color.RED );
            GraphicsUtilityFunctions.drawStringWithFontInRectangle(
                    g,
                    "Game Over",
                    GraphicsUtilityFunctions.getFont( 50 ),
                    w/2-125, 0, 250, 100
            );
        }
        
         g.setColor( Color.BLACK );
         for (int i = -2; i <= 2; i++) 
         {
            for (int j = -2; j <= 2; j++) 
            {
                GraphicsUtilityFunctions.drawStringWithFontInRectangle(
                        g,
                        "" + m.getScore(),
                        GraphicsUtilityFunctions.getFont(50),
                        w / 2 - 250 - i, 0 - j, 100, 100
                );
            }
        }
        g.setColor( Color.GREEN );
        GraphicsUtilityFunctions.drawStringWithFontInRectangle(
            g,
            "" + m.getScore(),
            GraphicsUtilityFunctions.getFont( 50 ),
            w/2-250, 0, 100, 100
        );
    }
    
    @Override
    public void handleMouseClick( TenTenGame g, int eventAge, MouseEvent me )
    {
        if ( g.isGameOver() ) return;
        
        double squareHOffset = width/20.0;
        double squareHSpacing = width/250.0;
        double squareVOffset = height/20.0;
        double squareVSpacing = height/250.0;
        
        int x = me.getX();
        int y = me.getY();
        
        if ( selectedPieceIndex == -1 )
        {
            if ( y < .7*height || y > .7*height + 5*squareVOffset || x < width/2 - 8.5*squareHOffset || x > width/2 + 8.5*squareHOffset )
            {
                return;
            }
            
            int i = (int)(x - width/2 + 8.5*squareHOffset)/(int)(6*squareHOffset);            // which piece
            int j = (int)((x - width/2 + 8.5*squareHOffset)/squareHOffset) % 6;       // which column
            int k = (int)((y - .7*height)/squareVOffset);            // which row
            
            if ( j == 5 ) return;
            
            Integer [][] piece = g.getPiece( i ).getGrid();
            if ( piece == null || k >= piece.length || j >= piece[k].length ) return;
            if ( piece[k][j] == null ) return;
            
            selectedPieceIndex = i;
            selectedRow = k;
            selectedCol = j;
            
            
        }
        else
        {
            int i = (int)((x - width/2 + 5*squareHOffset)/(squareHOffset)) - selectedCol;
            int j = (int)((y - height/2 + 7*squareVOffset)/(squareVOffset)) - selectedRow;
        
            if ( g.canPlay( selectedPieceIndex, j, i) )
            {
                g.playPiece( selectedPieceIndex, j, i );
            }
            selectedPieceIndex = -1;
        }
    }
    
    @Override
    public void handleKeyPress( TenTenGame g, int ea, KeyEvent ke )
    {
        if ( ke.getKeyChar() == 'l' )
        {
            g.loadSampleBoard( "SampleBoard.txt");
        }
        else if ( ke.getKeyChar() == 'e' )
        {
            g.loadSampleBoard( "GameOverBoard.txt" );
        }
        else if ( ke.getKeyChar() == 's' )
        {
            g.saveSampleBoard();
        }
    }
    
}
