/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import mytictactoe.*;
import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class MyTicTacToeTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        Player player = new Player("X", "", board);
        PlayerBot anotherPlayer = new PlayerBot("O", "", board);
       

        Player nextPlayer = anotherPlayer, curPlayer = player;
        boolean finished = false;
        Scanner scan = new Scanner(System.in);
        System.out.print(board);
        while (!finished) {
            System.out.println("Player " + curPlayer.getName() + "'s turn");
            System.out.println("Enter your Move x y (use space) : ");
            while(!curPlayer.move(scan.nextInt(), scan.nextInt())){
                System.out.println("Invalid Move!!");
            }
            System.out.println(board);
            
            Player temp = curPlayer;
            curPlayer = nextPlayer;
            nextPlayer = temp;


            finished = nextPlayer.isWinner(board);
        }
        System.out.println(nextPlayer.getName() + " is the Winner!!");
    }

}
