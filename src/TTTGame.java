import java.util.Scanner;

/**
 * 
 * @author Michael 'LSx' Castillo
 * 
 * I had help with Michael Pascale for checking the win cases
 * the reason why is because I was hard coding  most of my win cases
 * and he helped me out in understanding
 *
 */
public class TTTGame 
{
	//the variables we will need
	static int input;
	static int p1 = 0;
	static int p2 = 1;
	static int turns = 0;
	static String symbol;
	static String winner;
	static Scanner scan = new Scanner(System.in);
	static boolean win = false;
	
	//the tic tac toe gameboard
	static String[][] gameBoard = 
		{
			{"1","2","3"},
			{"4","5","6"},
			{"7","8","9"}	
		};
	
	//here we will have Intro post for the game, we do this
	//because we would like to make main look as clean as possible
	public static void Intro()
	{
		//here we will have the intro stuff print out 
		System.out.println( "To place your Symbol in a open spot "
						  + "please type in the number you would like to take over\n"
						  + "EX: type 1 for the top left spot");
		
	}
	
	//here we will have a print statement for the board
	public static void PrintBoard()
	{
		System.out.println();
		System.out.println("_"+ gameBoard[0][0] + "_|_"+gameBoard[0][1] + "_|_" + gameBoard[0][2] + "_ \n"
				        +  "_"+ gameBoard[1][0] + "_|_"+gameBoard[1][1] + "_|_" + gameBoard[1][2] + "_ \n"
				        +  " "+ gameBoard[2][0] + " | "+gameBoard[2][1] + " | " + gameBoard[2][2] + "  ");
		System.out.println();
	}
	
	//here we will have the clearBoard method to make the board go back to what it should be 
	public static void clearBoard()
	{
		int num = 1;
		for(int i = 0; i < gameBoard.length; i++)
		{
			for(int j = 0; j < gameBoard.length; j++)
			{
				gameBoard[i][j] = ""+num+"";
				num++;
			}
		}
	}
	
	//here we will have the code that will play the game and print stuff out!
	public static void PlayGame()
	{
		 
		 while(turns <= 8)
		 {
			 if(p1 < p2)
			 {
				 System.out.println("It's Player 1's Turn!");
				 input = scan.nextInt();
				 if(input < 10 && input > 0)
				 {
					 symbol = "X";
					 SwitchCheck();
					 p1 += 2;
					 turns += 1;
					 CheckWin();
				 }
				 else
				 {
					 TryAgain();
				 }
			 }
			 else
			 {
				System.out.println("Bots Turn!");
				BotTurn();
			 }
		 }
		 EndGame();
	}
	
	
	
		//here we will have a catch if someone is trying to replace
		//a spot that is already taken 
		public static void TryAgain()
		{
			System.out.println("Sorry cant play there try again");
			TTTGame.PrintBoard();
			TTTGame.PlayGame();
			
		}
		
		//here we will have the end game
		public static void CheckWin()
		{
			
			//here we have the horizontal and vertical win checks
			for(int i = 0; i < gameBoard.length; i++)
			{
				if(gameBoard[0][i] == "X" && gameBoard[1][i] == "X" && gameBoard[2][i] == "X")
				{
					win = true;
					winner = "p1";
					
				}
				if(gameBoard[i][0] == "X" && gameBoard[i][1] == "X" && gameBoard[i][2] == "X")
				{
					win = true;
					winner = "p1";
				}
				if(gameBoard[0][i] == "O" && gameBoard[1][i] == "O" && gameBoard[2][i] == "O")
				{
					win = true;
					winner = "p2";
				}
				if(gameBoard[i][0] == "O" && gameBoard[i][1] == "O" && gameBoard[i][2] == "O")
				{
					win = true;
					winner = "p2";
				}
			}
			
			//here we will have the diagonal win checks
			if(gameBoard[0][0] == "X" && gameBoard[1][1] == "X" && gameBoard[2][2] == "X")
			{
				win = true;
				winner = "p1";
			}
			if(gameBoard[2][0] == "X" && gameBoard[1][1] == "X" && gameBoard[0][2] == "X")
			{
				win = true;
				winner = "p1";
			}
			if(gameBoard[0][0] == "O" && gameBoard[1][1] == "O" && gameBoard[2][2] == "O")
			{
				win = true;
				winner = "p2";
				
			}
			if(gameBoard[2][0] == "O" && gameBoard[1][1] == "O" && gameBoard[0][2] == "O")
			{
				win = true;
				winner = "p2";
			}
			
			//if win is true while its early we just end the game!
			if(win == true)
			{
				TTTGame.EndGame();
			}
			else
			{
				TTTGame.PlayGame();
			}

			
		}
		
		//here we will have a method on if 1 player wins or if the game ties
		public static void EndGame()
		{
			if(win == true)
			{
				System.out.println("Congrats!! " + winner + " Wins!!");
				TTTGame.PlayAgain();
			}

			System.out.println("OH NO its a tie :(");		
			TTTGame.PlayAgain();
		}
		
		//here we will ask the player if they would like to play again or if they would like to turn the program off
		public static void PlayAgain()
		{
			System.out.println("would you like to play again?? (y/n)");
			String PAInput = scan.next();
			if(PAInput.equals("y"))
			{
				p1 = 0;
				p2 = 1;
				turns = 0;
				TTTGame.clearBoard();
				TTTGame.Intro();
				TTTGame.PrintBoard();
				TTTGame.PlayGame();			
			}
			else if(PAInput.equals("n"))
			{
				System.exit(0);
			}
			else
			{
				System.out.println("Sorry that answer was not valid, please type either 'y' or 'n' ");
				System.out.println();
				TTTGame.PlayAgain();
			}
			
		}
		
		
		/**
		 * here we will make the AI inside the game class
		 * The Computer (AI) will have to wait for its turn to play 
		 * but once it is its turn then it will play for player 2!!
		 */
		
		public static void BotTurn()
		{
			
			int max = 9;
			int min = 1;
			int range = max - min + 1;
			
			TTTGame.win();
			TTTGame.block();
			
			input = (int)(Math.random() * range + min);
			if(input < 10 && input > 0)
			 {
				 symbol = "O";
			 	TTTGame.SwitchCheck();
			 	p2 += 2;
			 	turns += 1;
			 	TTTGame.CheckWin();
			 }
			else
			 {
				 TTTGame.TryAgain();
			 }
			
		}
		
		//this is the method for the bot to block
		//the bot will check every vertical and horizontal and diagonal
		public static void block ()
		{
			//here we have the horizonal
			for(int i = 0; i < gameBoard.length; i++)
			{
				int counter = 0;
				for(int j = 0; j < gameBoard.length; j++)
				{ 
					if(gameBoard[i][j].equals("X"))
					{
						counter++;
					}
				}
				if(counter==2)
				{
					for(int j = 0; j < gameBoard.length; j++)
					{
						if(!gameBoard[i][j].equals("X"))
						{
							gameBoard[i][j] = "O";
							p2 += 2;
						 	turns += 1;
						 	TTTGame.PrintBoard();
						 	TTTGame.CheckWin();
						}
					}
				}
			}
			
			//here we have the vertical
			for(int i = 0; i < gameBoard.length; i++)
			{
				int counter = 0;
				for(int j = 0; j < gameBoard.length; j++)
				{ 
					if(gameBoard[j][i].equals("X"))
					{
						counter++;
					}
				}
				if(counter==2)
				{
					for(int j = 0; j < gameBoard.length; j++)
					{
						if(!gameBoard[j][i].equals("X"))
						{
							gameBoard[j][i] = "O";
							p2 += 2;
						 	turns += 1;
						 	TTTGame.PrintBoard();
						 	TTTGame.CheckWin();
						}
					}
				}
			}
			
			
			//here we will have the diaonals
			//this counter is for the diagonals
			int counter = 0;
			
			//the down diagonal from left to right
			for(int i = 0, j = 0; i < gameBoard.length; i++, j++)
			{
				{
					if(gameBoard[i][i].equals("X"))
					{
						counter++;
					}
					if(counter == 2)
					{
						for(i = 0, j = 0; i < gameBoard.length; i++, j--)
						{
							if(!gameBoard[i][j].equals("X") && !gameBoard[i][j].equals("O"))
							{
								gameBoard[i][j] = "O";
								p2 += 2;
							 	turns += 1;
							 	TTTGame.PrintBoard();
							 	TTTGame.CheckWin();
							}
						}
					}
					
				}
			}
			
			//the down diagonal from right to left 
			for(int i= 0, j = 2; i < gameBoard.length ; i++, j--)
			{
				
				if(gameBoard[i][j].equals("X")) 
				{
					counter++;
				}
				if(counter == 2)
				{
					for(i = 0, j = 2; i < gameBoard.length; i++, j--)
					{
						if(!gameBoard[i][j].equals("X") && !gameBoard[i][j].equals("O"))
						{
							gameBoard[i][j] = "O";
							p2 += 2;
						 	turns += 1;
						 	TTTGame.PrintBoard();
						 	TTTGame.CheckWin();
						}
					}
				}
				
			}
		}
		
		//here we will have the check statement for the AI to see if it has won
		public static void win()
		{
			//here we have the horizonal
			for(int i = 0; i < gameBoard.length; i++)
			{
				int counter = 0;
				for(int j = 0; j < gameBoard.length; j++)
				{ 
					if(gameBoard[i][j].equals("O"))
					{
						counter++;
					}
				}
				if(counter == 2)
				{
					for(int j = 0; j < gameBoard.length; j++)
					{
						if(!gameBoard[i][j].equals("X"))
						{
							gameBoard[i][j] = "O";
							p2 += 2;
						 	turns += 1;
						 	TTTGame.PrintBoard();
						 	TTTGame.CheckWin();
						}
					}
				}
			}
			
			//here we have the Vertical
			for(int i = 0; i < gameBoard.length; i++)
			{
				int counter = 0;
				for(int j = 0; j < gameBoard.length; j++)
				{ 
					if(gameBoard[j][i].equals("O"))
					{
						counter++;
					}
				}
				if(counter==2)
				{
					for(int j = 0; j < gameBoard.length; j++)
					{
						if(!gameBoard[j][i].equals("X") && !gameBoard[j][i].equals("O"))
						{
							gameBoard[j][i] = "O";
							p2 += 2;
						 	turns += 1;
						 	TTTGame.PrintBoard();
						 	TTTGame.CheckWin();
						}
					}
				}
			}
			
			//this counter is for the diagonals
			int counter = 0;
			
			//the down diagonal from left to right
			for(int i = 0, j = 0; i < gameBoard.length; i++, j++)
			{
				{
					if(gameBoard[i][i].equals("0"))
					{
						counter++;
					}
					if(counter == 2)
					{
						for(i = 0, j = 0; i < gameBoard.length; i++, j--)
						{
							if(!gameBoard[i][j].equals("O") && !gameBoard[i][j].equals("X"))
							{
								gameBoard[i][j] = "O";
								p2 += 2;
							 	turns += 1;
							 	TTTGame.PrintBoard();
							 	TTTGame.CheckWin();
							}
						}
					}
					
				}
			}
			
			//the down diagonal from right to left 
			for(int i= 0, j = 2; i < gameBoard.length ; i++, j--)
			{
				
				if(gameBoard[i][j].equals("O")) 
				{
					counter++;
				}
				if(counter == 2)
				{
					for(i = 0, j = 2; i < gameBoard.length; i++, j--)
					{
						if(!gameBoard[i][j].equals("O") && !gameBoard[i][j].equals("X"))
						{
							gameBoard[i][j] = "O";
							p2 += 2;
						 	turns += 1;
						 	TTTGame.PrintBoard();
						 	TTTGame.CheckWin();
						}
					}
				}
				
			}
		}
		
		
		
		//The SwitchCheck method is used to get all the ugly and long code
		//out of the PlayGame Method, I plan on working on it to see if
		//I can get it to not look so long
		public static void SwitchCheck() 
		{
			switch (input) 
			{
			case 1: 
				if(gameBoard[0][0] == "1")
				{
					gameBoard[0][0] = symbol;
					TTTGame.PrintBoard();
				}
				else 
				{
					TTTGame.TryAgain();
				}		
				break;
			case 2: 
				if(gameBoard[0][1] == "2")
				{
					gameBoard[0][1] = symbol;
					TTTGame.PrintBoard();
				}
				else 
				{
					TTTGame.TryAgain();
				}		
				break;
			case 3: 
				if(gameBoard[0][2] == "3")
				{
					gameBoard[0][2] = symbol;
					TTTGame.PrintBoard();
				}
				else 
				{
					TTTGame.TryAgain();
				}		
				break;
			case 4: 
				if(gameBoard[1][0] == "4")
				{
					gameBoard[1][0] = symbol;
					TTTGame.PrintBoard();
				}
				else 
				{
					TTTGame.TryAgain();
				}		
				break;
			case 5: 
				if(gameBoard[1][1] == "5")
				{
					gameBoard[1][1] = symbol;
					TTTGame.PrintBoard();
				}
				else 
				{
					TTTGame.TryAgain();
				}		
				break;
			case 6: 
				if(gameBoard[1][2] == "6")
				{
					gameBoard[1][2] = symbol;
					TTTGame.PrintBoard();
				}
				else 
				{
					TTTGame.TryAgain();
				}		
				break;
			case 7: 
				if(gameBoard[2][0] == "7")
				{
					gameBoard[2][0] = symbol;
					TTTGame.PrintBoard();
				}
				else 
				{
					TTTGame.TryAgain();
				}		
				break;
			case 8: 
				if(gameBoard[2][1] == "8")
				{
					gameBoard[2][1] = symbol;
					TTTGame.PrintBoard();
				}
				else 
				{
					TTTGame.TryAgain();
				}		
				break;
			case 9: 
				if(gameBoard[2][2] == "9")
				{
					gameBoard[2][2] = symbol;
					TTTGame.PrintBoard();
				}
				else 
				{
					TTTGame.TryAgain();
				}		
				break;
			}
			
		}
		
	

}
