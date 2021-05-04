import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        System.out.println("\n................WELCOME TO TICTACTOE GAME................\n");

        //creating scanner object to get user input
        Scanner input = new Scanner(System.in);

        //creating object of tictactor class
        TicTacToe game = new TicTacToe();

        game.start();

    }
}
