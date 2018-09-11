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
public class Point {
    
    private final int x,
                      y;
    private Player player;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public boolean isEqual(int x, int y){
        return this.x == x || this.y == y;
    }
    
    public void setPlayer(Player player){
        this.player = player;
    }
    
    public boolean isOccupied(){
        return player != null;
    }
    
    public Player getPlayer(){
        return player;
    }
    
    @Override
    public boolean equals(Object object){
        if(object instanceof Point){
            return ((Point)object).x == this.x && ((Point)object).y == this.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        return hash;
    }


    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}
