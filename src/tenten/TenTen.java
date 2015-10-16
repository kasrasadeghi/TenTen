/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tenten;

import apcscvm.CVMProgram;
import java.util.Scanner;

/**
 *
 * @author DSTIGANT
 */
public class TenTen
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        //testPieceCreation();
        launchGame();
    }
    
    public static void launchGame()
    {
        TenTenGame g = new TenTenGame();
        TenTenDisplay d = new TenTenDisplay();
        
        new CVMProgram( "1010", 550, 550, d, d, g ).start();
    }
    
    public static void testPieceCreation()
    {
        TenTenPiece.testPieceCreation();
    }
    
}
