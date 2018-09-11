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
public class PlayerBot extends Player{
    
    public PlayerBot(String playerName, Object marker, Board board) {
        super(playerName, marker, board);
    }
    
    
    public void botMove(Board board){
        Point point = board.getAutoMove(this);
        super.move(point.getX(), point.getY());
    }
    
}
