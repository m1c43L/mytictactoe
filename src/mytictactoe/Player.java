/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytictactoe;

import java.util.*;



/**
 *
 * @author Michael
 */
public class Player {
    
    private final String name;
    private Object legend;
    private int score;
    private Stack <Point> moves;
    private Board board;
    
    
    public Player(String playerName, Object legend, Board board){  
        this.legend = legend;
        name = playerName;
        score = 0;
        moves = new Stack();
        this.board = board;
    }
    
    public Point getLastMove(){
        if(moves.isEmpty()) return null;
        return moves.peek();
    }
    
    public boolean move(int x, int y){
        if(board.isPointFree(x, y)){          
           Point move = board.playerMove(x, y, this);
            moves.push(move);
            return true;
        }else
            return false;
    }
    
    public String getName(){
        return name;
    }
    
    public int getScore(){
        return score;
    }
  
    public void setScore(int score){
        this.score = score;
    }
    
    public boolean isWinner(Board board){
        return board.isWinner(this);
    }
    
    
    @Override
    public boolean equals(Object object){
        if(object instanceof Player){
             return name.equals(((Player)object).name);
        }
        else return false;
       
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public String toString(){
        return name;
    }
    
    

}

