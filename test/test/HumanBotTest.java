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
public class HumanBotTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board board = new Board();
        Player player = new Player("X", "", board);
        PlayerBot anotherPlayer = new PlayerBot("O", "", board);
        java.util.Scanner scan =  new Scanner(System.in);

        Player nextPlayer = player, curPlayer = anotherPlayer;
        boolean finished = false, isDraw = false;
        System.out.println("Human VS Bot tester : ");
        
        while (!finished && !isDraw) {
          if(nextPlayer instanceof PlayerBot){
            ((PlayerBot) nextPlayer).botMove(board);
              System.out.println("Bot moved: " + nextPlayer.getLastMove());
              System.out.println(board);
          }
          else{
             
              System.out.println(board);
            System.out.println("Eneter position (x, y) to move: ");
            nextPlayer.move(scan.nextInt(), scan.nextInt());
            System.out.println(board);
          }

            finished = nextPlayer.isWinner(board);
            isDraw = board.isDraw();
            
            if(finished)System.out.println(nextPlayer.getName() + " is the Winner!!");
           else if(isDraw)System.out.println("Game is Draw!!");
            
            Player temp = curPlayer;
            curPlayer = nextPlayer;
            nextPlayer = temp;
        }
        
    }
    
}