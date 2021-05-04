import java.util.Scanner;

public class TicTacToe extends Game{

    //creating the scanner object to take user input
    Scanner input = new Scanner(System.in);



    //variable to store which player's turn it is
    public static int turn;

    //creating a table of order 3x3 to store game status
    private Value[][] table = new Value[3][3];

    //  variable to keep count of moves that have been played in the game
    private static int moveCounter = 0;



    //setting all the values to EMPTY in the constructor
    public TicTacToe(){
        for(int i = 0; i < getTable().length; i++){
            for(int j = 0; j < getTable()[i].length; j++){
                getTable()[i][j] = Value.EMPTY;
            }
        }
    }



    // overriding the start method of parent class "Game" for the logic of TicTacToes
    @Override
    public void start(){

        //  Getting player 1's name
        System.out.print("Enter Player 1 Name> ");
        player1 = input.nextLine();

        //  Getting player 2's name
        System.out.print("Enter Player 2 Name> ");
        player2 = input.nextLine();



        //  print welcome to players
        System.out.println("\nHello ["+player1+"] and ["+player2+"]");
        System.out.println("Let's Play!");


        //  Showing game guidelines to the players
        System.out.println("\n......Guidelines......\n");
        System.out.println("Valid Moves: \n1, 2, 3\n4, 5, 6\n7, 8, 9");
        System.out.println("\nEnter any number which is empty.");
        System.out.println("If entered number is filled, you have to enter again.\n");

        System.out.println("X is for "+player1+" (Player 1)");
        System.out.println("O is for "+player2+" (Player 2)");



        //  printing the current game status
        System.out.println("\n......Game Status......\n");

        //  this loop will print all the values of game status table
        for (int i = 0; i < getTable().length; i++) {
            for (int j = 0; j < getTable()[i].length; j++) {
                System.out.print(getTable()[i][j]+", \t");
            }
            System.out.println();
        }
        System.out.println();



        //  ..................................Game Logic following.........................................


        //  initial turn will be player 1's
        turn = 1;

        //  move store the value where the player wants to mark
        int move = 0;

        //  moveType stores the mark i.e. either X or O depending on the player
        Value moveType = Value.EMPTY;

        //validity tells if the move entered by player is valid or not
        boolean validity = true;



        //  loop runs until the game is finished
        while(validity){

            if(turn == 1){

                System.out.println(player1+" make your move> ");
                move = input.nextInt();
                moveType = Value.X;

            }else if(turn == 2){

                System.out.println(player2+" make your move> ");
                move = input.nextInt();
                moveType = Value.O;

            }


            int row = 0, col = 0;

            /*
            *   changing the row and column value so that they correspond the move_position entered by player.
            *   1 -> (0,0) | 2 -> (0,1) | 3 -> (0,2)
            *   ____________________________________
            *   4 -> (1,0) | 5 -> (1,1) | 6 -> (1,2)
            *   ____________________________________
            *   7 -> (2,0) | 8 -> (2,1) | 9 -> (2,2)
            */

            switch(move){
                case 1:
                    row=0;
                    col=0;
                    break;
                case 2:
                    row=0;
                    col=1;
                    break;
                case 3:
                    row=0;
                    col=2;
                    break;
                case 4:
                    row=1;
                    col=0;
                    break;
                case 5:
                    row=1;
                    col=1;
                    break;
                case 6:
                    row=1;
                    col=2;
                    break;
                case 7:
                    row=2;
                    col=0;
                    break;
                case 8:
                    row=2;
                    col=1;
                    break;
                case 9:
                    row=2;
                    col=2;
                    break;
                default:
                    System.out.println();
                    break;
            }


            //  validity tells if the move value entered was EMPTY or not
            validity = checkMoveValidity(getTable(), row, col);


            if(validity == false){
                System.out.println("Not Valid Move, try again...");
                System.out.println();

                validity = true;
                continue;
            }

            //  updating the table of game status with the value associated with the player whose turn it was.
            //  X for Player 1 AND O for Player 2
            table[row][col] = moveType;

            //  setting and increasing the moveCounter with every loop iteration
            moveCounter++;

            //  printing the current game status
            System.out.println("\n......Game Status......\n");

            //  this loop will print all the values of game status table
            for (int i = 0; i < getTable().length; i++) {
                for (int j = 0; j < getTable()[i].length; j++) {
                    System.out.print(getTable()[i][j]+", \t");
                }
                System.out.printf("\n");
            }
            System.out.println();



            //  HER WE WILL CHECK IF A PLAYER HAS WON THE GAME------------------------------
            int stopCondition;

            //  current game status (table) along with current moves counter is passed.
            //  stop() returns 1 if player 1 has won
            //  stop() returns 2 if player 2 has won
            //  stop() returns 3 if game has been drawn
            //  stop() returns 0 if game can be player further
            stopCondition = stop(table, moveCounter);

            if(stopCondition == 1){
                System.out.println("................CONGRATULATIONS "+player1+"! YOU HAVE WON................");
                break;
            }

            if(stopCondition == 2){
                System.out.println("................CONGRATULATIONS "+player2+"! YOU HAVE WON................");
                break;
            }

            if(stopCondition == 3){
                System.out.println("................GAME HAS BEEN DRAWN................");
                break;
            }



            //  if current turn was of player 1 then next turn should be of player 2 and vice versa.
            if(turn == 1){
                turn = 2;
            }else if(turn == 2) {
                turn = 1;
            }
        }

        //  calling restart() to ask if player wants restart or exit the game.
        restart();
    }



    //  method to check if the move entered by player was on empty place or not.
    //  this method will return FALSE if it had any other value than EMPTY.
    //  otherwise it will return TRUE.
    public boolean checkMoveValidity(Value[][] temp, int row, int col){
        if(temp[row][col] != Value.EMPTY){
            return false;
        }
        return true;
    }


    @Override
    public int stop(Value[][] table, int moveCounter){


        //  checking if player with "X" has won
        //  will return 1 for player number 1 if he has won the game
        if(table[0][0] == Value.X && table[0][1] == Value.X && table[0][2] == Value.X){
            return 1;
        }
        if(table[0][0] == Value.X && table[1][0] == Value.X && table[2][0] == Value.X){
            return 1;
        }
        if(table[2][0] == Value.X && table[2][1] == Value.X && table[2][2] == Value.X){
            return 1;
        }
        if(table[0][2] == Value.X && table[1][2] == Value.X && table[2][2] == Value.X){
            return 1;
        }
        if(table[0][0] == Value.X && table[1][1] == Value.X && table[2][2] == Value.X){
            return 1;
        }
        if(table[0][2] == Value.X && table[1][1] == Value.X && table[2][0] == Value.X){
            return 1;
        }
        if(table[0][1] == Value.X && table[1][1] == Value.X && table[2][1] == Value.X){
            return 1;
        }if(table[1][0] == Value.X && table[1][1] == Value.X && table[1][2] == Value.X){
            return 1;
        }



        //  checking if player with "O" has won
        //  will return 2 for player number 2 if he has won the game
        if(table[0][0] == Value.O && table[0][1] == Value.O && table[0][2] == Value.O){
            return 2;
        }
        if(table[0][0] == Value.O && table[1][0] == Value.O && table[2][0] == Value.O){
            return 2;
        }
        if(table[2][0] == Value.O && table[2][1] == Value.O && table[2][2] == Value.O){
            return 2;
        }
        if(table[0][2] == Value.O && table[1][2] == Value.O && table[2][2] == Value.O){
            return 2;
        }
        if(table[0][0] == Value.O && table[1][1] == Value.O && table[2][2] == Value.O){
            return 2;
        }
        if(table[0][2] == Value.O && table[1][1] == Value.O && table[2][0] == Value.O){
            return 2;
        }
        if(table[0][1] == Value.O && table[1][1] == Value.O && table[2][1] == Value.O){
            return 2;
        }if(table[1][0] == Value.O && table[1][1] == Value.O && table[1][2] == Value.O){
            return 2;
        }



        //  checks if 9 moves have been played and as the upper conditions suggest at the moment
        //  that no player has won then the game is drawn
        //  3 will be returned in case of drawn game
        if(moveCounter == 9){
            return 3;
        }



        //  will return 0 if neither player has won the game YET
        return 0;

    }


    // overriding the restart method of parent class Game
    // to restart the game of TicTacToes
    @Override
    public void restart(){

        int restart;
        input.nextLine();

        System.out.println();
        System.out.println("Do you want to restart the game?");
        System.out.println("Enter 1 for Yes");
        System.out.println("Enter 0 for No");
        restart = input.nextInt();
        System.out.println();


        //  resetting the game status table and displaying it
        if(restart == 1) {
            for (int i = 0; i < getTable().length; i++) {
                for (int j = 0; j < getTable()[i].length; j++) {
                    getTable()[i][j] = Value.EMPTY;
                }
            }


            //  this loop will print all the values of game status table
            for (int i = 0; i < getTable().length; i++) {
                for (int j = 0; j < getTable()[i].length; j++) {
                    System.out.print(getTable()[i][j]+", \t");
                }
                System.out.printf("\n");
            }

            // resetting the move counter and turn is set to player 1's turn
            moveCounter = 0;
            turn = 1;

            input.nextLine();

            //  calling start() to restart the game
            start();
        }

        System.out.println("TERMINATING GAME!");
    }




    //  getter for table array
    public Value[][] getTable() {
        return table;
    }

    //  setter for table array
    public void setTable(Value[][] table) {
        this.table = table;
    }
}
