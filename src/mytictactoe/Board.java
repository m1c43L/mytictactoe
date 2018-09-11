/*
 TO DO .....
deny and win autoMove.

 *
 */
package mytictactoe;
import java.util.*;

/**
 *
 * @author Michael
 */
public class Board {

    
    private Point [][] board;
    private Player lastPlayer;
    private ArrayList <Point> freePoints;
    private int lenToWin;
    
    
    public Board(int xLen, int yLen, int lenToWin){
        freePoints = new ArrayList();
        board = new Point[xLen][yLen];
        this.lenToWin = lenToWin;
        for(int x = 0; x < xLen; x++){
            for(int y = 0; y < yLen; y++){
                board[x][y] = new Point(x,y);
                freePoints.add(board[x][y]);
            }
        }
    }
    
    public Board(){
         this(3,3,3);
    }
    
    public boolean isDraw(){
        return freePoints.isEmpty();
    }
    
    public boolean isPointFree(int x, int y){
        return !board[x][y].isOccupied();
    }
    
    public Point getAutoMove(Player player){
        System.out.println("Player : " + player.getName() + " moved");//***********debug*******
        if(player.getLastMove() == null){ 
            System.out.println("INIt");
            return freePoints.get((int) (Math.random() 
                    * (freePoints.size() - 1)));
        }
        Point point;
        if((point = getWinPoint(player)) != null){
            System.out.println("Win");//***********debug*******
            return point;
        } 
        if((point = getWinPoint(lastPlayer)) != null){
            System.out.println("Deny");//***********debug*******
            return point;
        }
         System.out.println("Ramdomed");//***********debug*******
         return freePoints.remove((int) (Math.random() * (freePoints.size() - 1)));
        
    }  
    
    private Point getWinPoint(Player player){
        System.out.println("check player : "+player.getName());
        if(countVertical(player) == lenToWin - 1){
            System.out.println("Found Vertical");//***********debug*******
           Point winningMove = getFreePoint(player, player.getLastMove(), 1, 0);
            winningMove =  winningMove != null ?  winningMove : getFreePoint(player, player.getLastMove(), -1, 0);
            if(winningMove != null) return winningMove;
        } 
        if(countLeftDiagonal(player) == lenToWin - 1){
            System.out.println("Found LDIAG");//***********debug*******
           Point winningMove = getFreePoint(player, player.getLastMove(), 1, 1);
            winningMove =  winningMove != null ?  winningMove : getFreePoint(player, player.getLastMove(), -1, -1);
            if(winningMove != null) return winningMove;
        }
        if(countRightDiagonal(player) == lenToWin - 1){
            System.out.println("Found RDIAG");//***********debug*******
           Point winningMove = getFreePoint(player, player.getLastMove(), -1, 1);
             winningMove =  winningMove != null ?  winningMove : getFreePoint(player, player.getLastMove(), 1, -1);
              if(winningMove != null) return winningMove;
        }
        if(countHorizontal(player) == lenToWin - 1){
            System.out.println("Found HORIZONTAL");//***********debug*******
           Point winningMove = getFreePoint(player, player.getLastMove(), 0, 1);
            winningMove =  winningMove != null ?  winningMove : getFreePoint(player, player.getLastMove(), 0, -1);    
             if(winningMove != null) return winningMove;
        }   
        
        return null; // no free point found
    }
    
    
    private Point getFreePoint(Player player, Point point, int xModifier, int yModifier){
        try{
            Point newPoint = board[point.getX() + xModifier][point.getY() + yModifier];
            if(!newPoint.isOccupied()){
                return newPoint;                               
            }else{ 
                return getFreePoint(player, newPoint, xModifier, yModifier);
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    
    public Point playerMove(int x, int y, Player player){
        board[x][y].setPlayer(player);
        lastPlayer = player;
        freePoints.remove(board[x][y]);
        System.out.println("freepoints :" + freePoints.size()); //***********debug*******
        return board[x][y];
    }
    
    protected boolean isWinner(Player player){
      return countVertical(player) == lenToWin  || 
              countLeftDiagonal(player) == lenToWin  ||
              countRightDiagonal(player) == lenToWin  ||
              countHorizontal(player) == lenToWin ;
    }             
    
    private int countVertical(Player player){
        return 1 + count(player.getLastMove(), player, -1, 0) 
                + count(player.getLastMove(), player, 1, 0); 
    }
    
    private int countLeftDiagonal(Player player){
        return 1 + count(player.getLastMove(), player, -1, -1) 
                + count(player.getLastMove(), player, 1, 1); 
    }
    
    private int countRightDiagonal(Player player){
        return 1 + count(player.getLastMove(), player, -1, 1) 
                + count(player.getLastMove(), player, 1, -1); 
    }
    
    private int countHorizontal(Player player){
        return 1 + count(player.getLastMove(), player, 0, -1) 
                + count(player.getLastMove(), player, 0, 1); 
    }
    
    private int count(Point point, Player player, int xModifier, int yModifier){
        try{
            Point newPoint = board[point.getX() + xModifier][point.getY() + yModifier];
            if(!newPoint.isOccupied()){
                return count(newPoint, player ,xModifier , yModifier);
            }else if(newPoint.getPlayer().equals(player)){
                return 1 + count(newPoint, player ,xModifier , yModifier);
            }else{
                return -1 + count(newPoint, player ,xModifier , yModifier);
            }
        }catch(ArrayIndexOutOfBoundsException e){
            return 0;
        }
    }
    
    
   
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board.length; j++){
                builder.append((board[i][j].getPlayer() == null)?
                        "_":board[i][j].getPlayer() );
            }
            builder.append("\n");
        }
        return builder.toString();
    }
   
}

/*
class Lister{
    private Point [] points;
    private int capacity;
    private int size;
    
    Lister(int cap){
        capacity = cap;
        points = new Point [cap];
        size = 0;
    }
    
    void add(Point p){
        
    }
    
}




///////////////////////////////////////////////////////////////
///////*****Old source Code. TO be deleted***//////////////////
///////////////////////////////////////////////////////////////
   /*
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
    
    /*
    
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
    
    */