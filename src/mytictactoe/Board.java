/*
 * A simple project application for the game tic tac toe.
 * Although tic tac toe is often played in a 3x3 board, my implementation
 * allows the board to be modified to any size.
 *
 */
package mytictactoe;
import java.util.*;

/**
 *
 * @author Michael
 */
public class Board {

    char [][] tiles; // main board
    
    public Board() //default
    {  this(3); }
    
    //constructor
    public Board(int lw)
    {
        tiles =  new char[lw][lw];
        
        for(int i = 0; i < lw; i++)
        {  
            for(int j = 0; j < lw; j++)
            {  tiles[i][j] = '_';     }
        }
    }
   
    public boolean isFull()
    {
        int numSpace = 0;
        for(int i = 0; i < Math.pow(tiles.length, 2); i++ )
        {      if(isValidMove(i)) numSpace++;  }
        return numSpace == 0;
    }
    
    //return char at int position. retfer to set(int pos) method.
    //returns '!' if pos is out of bound.
    private char get(int pos)
    {
        if(pos < 0 || Math.pow(tiles.length, 2) <=  pos ) 
            return '!';
        return tiles[pos/tiles.length][pos%tiles.length];
    }
    
    public boolean isValidMove(int i)
    {  return get(i) == '_'; }
    
    // each position location has been given 
    // unique integer value to make insertion and deletion simpler
    // below is the example of a 3x3 board
    // 0 1 2 
    // 3 4 5
    // 6 7 8
    // the position must be (0<=pos<length of tile^2)
    public boolean set(int pos, char c)
    { 
        if(!isValidMove(pos)) return false;
        tiles[(pos)/tiles.length][pos%tiles.length] = c; 
        return true;
    }
    
    //return true is the player char t has won.
    //return false otherwise.
    public boolean hasWon(char t)
    {
        int length = tiles.length;
        for(int i = 0; i < length; i++)
        {   
            if(i<1) // checks first line (eg. 0 1 2 in a 3x3 board)
            {
                for(int j = 0; j < length; j++)
                {
                    if(tiles[i][j] == t)
                    {
                        //variable counter for vertical, diagonal from left
                        //and diagonal from right.
                        int v = 0, dl = 0, dr = 0;                        
                        for(int k = 0; k < length; k++) //vertical
                            if(tiles[k][j]==t) v++;
                        if(i==j)
                        {
                            for(int k = 0; k < length; k++) // diagonal from left
                                if(tiles[k][k]==t) dl++;
                        }
                        if(j == length-1)
                        {
                            int temp = length-1;
                            for(int k = 0; k < length; k++) // diagonal from rigth
                            { if(tiles[temp][k] == t) dr ++; temp--; }                            
                        }
                        if(v == length ||dl == length || dr == length)                   
                            return true;                                      
                    }                
                }   
            }
            if(tiles[i][0]==t) // checks all horizontal.
            {
                int horizontal = 0;
                    for(int k = 0; k < tiles.length; k++)
                        if(tiles[i][k]==t) horizontal ++;
                if(horizontal == tiles.length) return true;
            }                       
        }
        return false;
    }
    
    //AI moves random unmarked spot.
    //char p is player, char b is bot.
    private void AIRandomMove(char p, char b)
    {
        int s = (int) Math.pow(tiles.length, 2), cur = 0; // s is the max location
        int [] possibleM = new int[s];
        
        for(int i = 0; i < s; i++) 
        {
            if(get(i)!=p && get(i)!=b) // insert unmarked spots in the array.
            {  possibleM[cur] = i; cur ++;}
        }
        
        set(possibleM[(int)(Math.random()* (cur))],b); // initate random move.       
    }
    
    //returns true if AI moved was win
    //returns false if not.
    private boolean AIwinMove(char p, char b)
    {
        int horizontal = 0, vertical = 0, diagl= 0, diagr = 0, temp = 0;
        
        //check for horizontal fields.
        for(int i = 0; i < Math.pow(tiles.length, 2); i++)
        {            
            if(get(i)== p) horizontal++;
            if(horizontal == tiles.length-1)
                for(int j = temp; j < temp+tiles.length; j++)
                    if (isValidMove(j)) { set(j,p); return true; }                          
            if(i%tiles.length == tiles.length-1){ horizontal = 0; temp = i+1;}          
        }        
        temp = 0;
        
        //diagonal from left
        for(int i = 0; i < Math.pow(tiles.length, 2); i+=1+tiles.length)
        {  if(get(i) == p) diagl++; else temp = i;  }
        
        if(diagl == tiles.length-1 && isValidMove(temp)){ set(temp,p); return true; }
        temp = 0;
        
        //diagonal from right       
        for(int i = tiles.length-1; i <= Math.pow(tiles.length, 2) - tiles.length; i+=tiles.length-1)
        {   if(get(i) == p) diagr++; else temp = i; }
        
        if(diagr == tiles.length-1 && isValidMove(temp)){ set(temp,p); return true;}
        
        //vertical       
        for(int i = 0; i < tiles.length; i++)
        {
            temp = 0;
            for(int j = 0; j < tiles.length; j++)
            {
                if(tiles[j][i] == p) vertical ++;
                else temp = (j*tiles.length) + (i%tiles.length);
            }
            if(vertical == tiles.length-1 && isValidMove(temp)) 
                { set(temp,p); return true; } 
            vertical = 0;
        }
        return false;
    }
    
    public void AImove(char p, char b)
    {
        if( !AIwinMove(b,p) ) // see first if it cannot win yet deny human or random move
        {           
        int horizontal = 0, vertical = 0, diagl= 0, diagr = 0, temp = 0;
        
        //check for horizontal fields.
        for(int i = 0; i < Math.pow(tiles.length, 2); i++)
        {            
            if(get(i)== p) horizontal++;
            if(horizontal == tiles.length-1)
                for(int j = temp; j < temp+tiles.length; j++)
                    if (get(j) != p && get(j) != b) { set(j,b);  return; }                          
            if(i%tiles.length == tiles.length-1){ horizontal = 0; temp = i+1;}          
        }        
        temp = 0;
        //diagonal from left
        for(int i = 0; i < Math.pow(tiles.length, 2); i+=1+tiles.length)
        {
            if(get(i) == p) diagl++;
            else temp = i;          
        }
        if(diagl == tiles.length-1 && get(temp) != b){ set(temp,b); return; }
        temp = 0;
        //diagonal from right       
        for(int i = tiles.length-1; i <= Math.pow(tiles.length, 2) - tiles.length; i+=tiles.length-1)
        {
            if(get(i) == p) diagr++;
            else temp = i;          
        }
        if(diagr == tiles.length-1 && get(temp) != b){ set(temp,b); return;}
        for(int i = 0; i < tiles.length; i++)
        {
            temp = 0;
            for(int j = 0; j < tiles.length; j++)
            {
                if(tiles[j][i] == p) vertical ++;
                else temp = (j*tiles.length) + (i%tiles.length);
            }
            if(vertical == 2 && get(temp)!= b) { set(temp,b); return; }   
            vertical = 0;
        }       
        AIRandomMove(p,b);
        }
        
    }
    
    
   
    @Override
    public String toString()
    {
        for (char[] tile : tiles) {
            for (int j = 0; j < tiles.length; j++) {
                System.out.print(tile[j] + " ");
            }
            System.out.println("\n");
        }
        
        System.out.println();
        return "";
    }
    
    
    /**
     * @param args the command line arguments
     */
    
    
    
    //Tester
    public static void main(String[] args) {
        
        Board test = new Board(3);
     
        char p = 'O', bot = 'X';
        test.toString();
        Scanner input = new Scanner(System.in);
        int mymove;
        

       while(true)
       {
           
           test.AImove(p, bot);
           System.out.print(test);          
           if(test.hasWon(bot)){ System.out.println("You lost!"); break;}
           if(test.isFull()) { System.out.println("Draw!"); break;}
           System.out.println("Your turn : ");
           
           mymove = input.nextInt();
           if(mymove < 0) System.exit(0);
           boolean move = test.isValidMove(mymove);
           while(!move)
           {              
               System.out.println("Invalid. Try again.");
               mymove = input.nextInt();
               if(mymove < 0) System.exit(1);
               move = test.isValidMove(mymove);
           }
           test.set(mymove, p);
           System.out.print(test);
           
           if(test.hasWon(p)){ System.out.println("You Won!!"); break;}
                    
       }
          
    }
    
}
