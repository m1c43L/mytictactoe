/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Scanner;
import mytictactoe.Board;
import mytictactoe.Player;
import mytictactoe.PlayerBot;

/**
 *
 * @author Michael
 */
public class BotTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        PlayerBot player = new PlayerBot("X", "", board);
        PlayerBot anotherPlayer = new PlayerBot("O", "", board);
       

        PlayerBot nextPlayer = anotherPlayer, curPlayer = player;
        boolean finished = false , draw = false;
        
        while (!finished && !draw) {
            curPlayer.botMove(board);
            System.out.println(board);
            PlayerBot temp = curPlayer;
            curPlayer = nextPlayer;
            nextPlayer = temp;


            finished = nextPlayer.isWinner(board);
            draw = board.isDraw();
           
            if(finished)System.out.println(nextPlayer.getName() + " is the Winner!!");
           else if(draw)System.out.println("Game is Draw!!");
        }
        
    }
    
}
