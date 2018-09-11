/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytictactoe;

/**
 *
 * @author Michael
 */
public class MyTicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Board b = new Board();
        Player x = new Player("x", 1, b);
        Player y = new Player("y", 2, b);
        /*
        
        b.playerMove(0, 0, y);
        System.out.println(b);
        b.playerMove(1, 0, y);
        System.out.println(b);
        b.playerMove(2, 0, y);
        System.out.println(b);
        b.playerMove(0, 1, y);
        System.out.println(b);
        b.playerMove(1, 1, y);
        System.out.println(b);
        b.playerMove(2, 1, y);
        System.out.println(b);
        b.playerMove(1, 2, y);
        System.out.println(b);
        b.playerMove(2, 2, y);
        System.out.println(b);
        b.playerMove(0, 2, y);
        System.out.println(b);
         */
        b.playerMove(2, 0, y);
        b.playerMove(0, 2, y);
        b.playerMove(1, 1, y);

        System.out.println(b.isWinner(y));
    }

}
