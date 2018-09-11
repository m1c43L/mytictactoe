/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Michael
 */
public class Rand {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int x = 0;
        while(x  != 15){
            x = (int)(Math.random() * 20);
            System.out.println(x);
        }
    }
    
}
