/* program will use a backtracking algorithm to find every possible solution for a 
 * sudoku board
 **/
public class SudokuSolver {
	
	public static void displayBoard(int[][] board) { /* creates sudoku board */
		for(int r = 0; r < board.length; r++) {
			for(int c = 0; c < board[r].length; c++) {
				if(c % 3 == 0 && c!= 0)	System.out.print(" | "); 
					
				System.out.print(board[r][c] + " ");
			}
			if(r % 3 == 2 && r != 8)	System.out.print("\n----------------------"); 
			System.out.print("\n");
		}
	}
	
	public static boolean valid(int[][] board, int num, int row, int col) {/* checks if a number in a position is valid */
		
		
		for(int i = 0; i < board.length; i++) { /* checks that no two numbers are in the same row */
			if(board[i][col] == num) return false;
		}
		
		for(int i = 0; i < board[row].length; i++) { /* checks that no two numbers are in the same col */
			if(board[row][i] == num) return false;
		}
		
		for(int r = row / 3 * 3; r < (row / 3 + 1)* 3; r++) { /* checks 3x3 quadrant */
			for(int c = col / 3 * 3; c < (col / 3 + 1)* 3; c++) {
				if(board[r][c] == num) return false;
			}
		}
		return true;
	}
	
	public static int empty(int[][] board) {
		
		for(int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 0) { 
					return i * 9 + j;
				}
			}
		}
		
		return -1;
	} 
	
	public static boolean solver(int[][] board) {
		int row, col;
		
		if(empty(board) == -1) {	
			return true;
		}
		else {
			row = empty(board) / 9;
			col = empty(board) % 9;
		}
		
		for(int i = 1; i < 10; i++) {
			if(valid(board,i,row,col)) {	
				board[row][col] = i;
			
				if (solver(board))	return true;
			
				board[row][col] = 0;
			}
		}
		
		
		return false;
	}
	
	public static void main(String[] args) {
		
		int[][] board = { /* any valid sudoku board with one solution */	
							{4,0,0,9,0,0,0,0,0},
					 		{0,2,9,0,0,0,1,0,0},
					 		{7,0,0,0,5,0,3,0,0},
					 		{0,0,0,7,3,0,8,0,0},
					 		{5,0,0,8,0,1,0,0,2},
					 		{0,0,3,0,6,5,0,0,0},
					 		{0,0,2,0,4,0,0,0,3},
					 		{0,0,1,0,0,0,7,4,0},
					 		{0,0,0,0,0,6,0,0,9},
						};
		
		System.out.println("Starting Board:\n");
		displayBoard(board);
		solver(board);
		System.out.println("\nSolved Board:\n");
		displayBoard(board);
	}
}
