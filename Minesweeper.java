package objectOrientedDesign;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
enum CellContent{empty, bomb, number};

class Cell{
	boolean discovered = false;
	CellContent content;
	private int numOfAdjacentBombs = 0;
	ArrayList<Cell> adjacentCells = new ArrayList<Cell>();
	int row;
	int column;
	public Cell up;
	public Cell right;
	public Cell left;
	public Cell down;
	public Cell upRight;
	public Cell upLeft;
	public Cell downRight;
	public Cell downLeft;
	public Cell(int row, int col, CellContent content) {
		this.row = row;
		this.column = col;
		this.content = content;
	}
	public ArrayList<Cell> getAdjacents() {
		return this.adjacentCells;
	}
	public CellContent getContent() {
		return this.content;
	}
	public void increaseNumOfAdjBombs() {
		this.numOfAdjacentBombs++;
	}
	public void setContent(CellContent content) {
		this.content = content;
	}
	public boolean isDiscovered() {
		return this.discovered;
	}
	public void discover() {
		this.discovered = true;
	}
	public int numOfBombsNear() {
		if(this.content != CellContent.number) {
			return 0;
		}
		return this.numOfAdjacentBombs;
	}
}
class Board{
	static final int boardWidth = 7;
	static final int boardHeight = 7;
	Cell[][] grid = new Cell[boardWidth][boardHeight];
	public Board() {
	}
	public void setBoardAtStart(){
		for(int row = 0; row < this.boardHeight; row++) {
			for(int col = 0; col < this.boardWidth; col++) {
				Cell c = new Cell(row,col,CellContent.empty);
				grid[row][col] = c;
			}
		}
		for(int row = 0; row < this.boardHeight; row++) {
			for(int col = 0; col < this.boardWidth; col++) {
				setAdjacents(row, col);
			}
		}
	}
	public void printBoard() {
		for(int row = 0; row < 7; row++) {
			for(int col = 0; col < 7; col++) {
				if(!this.grid[row][col].isDiscovered()) {
					System.out.print("?" + "	");
				} else {
					if(this.grid[row][col].getContent() == CellContent.number) {
						System.out.print(this.grid[row][col].numOfBombsNear() + "	");
					}
					if(this.grid[row][col].getContent() == CellContent.empty) {
						System.out.print("-" + "	");
					}
				}
			}
			System.out.println();
		}
	}
	public Cell[][] getGrid(){
		return this.grid;
	}
	public void setAdjacents(int row, int column) {
		if(row-1 >= 0) {
			this.grid[row][column].up = this.grid[row-1][column];
		}
		if(column+1 < this.grid.length) {
			this.grid[row][column].right = this.grid[row][column+1];
		}
		if(column-1 >= 0) {
			this.grid[row][column].left = this.grid[row][column-1];
		}
		if(row+1 < this.grid.length) {
			this.grid[row][column].down = this.grid[row+1][column];
		}
		if(row-1 >= 0 && column-1 >= 0) {
			this.grid[row][column].upLeft = this.grid[row-1][column-1];
		}
		if(row-1 >= 0 && column+1 < this.grid.length) {
			this.grid[row][column].upRight = this.grid[row-1][column+1];
		}
		if(row+1 < this.grid.length && column-1 >= 0) {
			this.grid[row][column].downLeft = this.grid[row+1][column-1];
		}
		if(row+1 < this.grid.length && column+1 < this.grid.length) {
			this.grid[row][column].downRight = this.grid[row+1][column+1];
		}
		Collections.addAll(this.grid[row][column].adjacentCells, this.grid[row][column].up, this.grid[row][column].right, 
				this.grid[row][column].left, this.grid[row][column].down, this.grid[row][column].upRight, this.grid[row][column].upLeft, 
				this.grid[row][column].downRight, this.grid[row][column].downLeft);
	}
	public void changeContent(CellContent content, Cell[][] grid, int row, int col) {
		grid[row][col].setContent(content);
		if(content == CellContent.bomb) {
			if(grid[row][col].up != null) {
				grid[row][col].up.setContent(CellContent.number);
				grid[row][col].up.increaseNumOfAdjBombs();		
			}
			if(grid[row][col].right != null) {
				grid[row][col].right.setContent(CellContent.number);
				grid[row][col].right.increaseNumOfAdjBombs();		
			}
			if(grid[row][col].left != null) {
				grid[row][col].left.setContent(CellContent.number);
				grid[row][col].left.increaseNumOfAdjBombs();		
			}
			if(grid[row][col].down != null) {
				grid[row][col].down.setContent(CellContent.number);
				grid[row][col].down.increaseNumOfAdjBombs();		
			}
			if(grid[row][col].upRight != null) {
				grid[row][col].upRight.setContent(CellContent.number);
				grid[row][col].upRight.increaseNumOfAdjBombs();		
			}
			if(grid[row][col].upLeft != null) {
				grid[row][col].upLeft.setContent(CellContent.number);
				grid[row][col].upLeft.increaseNumOfAdjBombs();		
			}
			if(grid[row][col].downRight != null) {
				grid[row][col].downRight.setContent(CellContent.number);
				grid[row][col].downRight.increaseNumOfAdjBombs();		
			}
			if(grid[row][col].downLeft != null) {
				grid[row][col].downLeft.setContent(CellContent.number);
				grid[row][col].downLeft.increaseNumOfAdjBombs();		
			}
		}
	}
	public boolean click(int row, int col) {
		if(!this.grid[row][col].isDiscovered()) {
			if(this.grid[row][col].getContent() == CellContent.bomb) {
				this.grid[row][col].discover();
				System.out.println("Bomb! End game. You lose.");
				System.exit(0);
			} else if(this.grid[row][col].getContent() == CellContent.number) {
				this.grid[row][col].discover();
			} else {
				uncover(row,col, this.grid);
			}
		} else {
			System.out.println("This cell has already been discovered. Try again.");
			return false;
		}
		return true;
	}
	public void uncover(int row, int col, Cell[][] grid) {
		if(!grid[row][col].isDiscovered()) {
			grid[row][col].discover();
			if(grid[row][col].getContent() == CellContent.number) {
				return;
			} else if (grid[row][col].getContent() == CellContent.empty) {
				if(row-1 >= 0) {  
					uncover(row-1, col, grid);	//up
				}
				if(col-1 >= 0) { 
					uncover(row, col-1, grid);	//left
				}
				if(row+1 < boardHeight) {
					uncover(row+1,col, grid);		//down
				}
				if(col+1 < boardWidth) {
					uncover(row,col+1, grid);	//right
				}
				if(row-1 >= 0 && col-1 >= 0) {	//up left
					uncover(row-1, col-1, grid);
				}
				if(row-1 >= 0 && col+1 < boardWidth) {
					uncover(row-1,col+1,grid);	//up right
				}
				if(row+1 < boardHeight && col-1 >= 0) {		//down left
					uncover(row+1, col-1, grid);
				}
				if(row+1 < boardHeight && col+1 < boardWidth) {
					uncover(row+1, col+1, grid);	//down right
				}
			}
		} else {
			return;
		}
	}
}

public class Minesweeper {
	public static void main(String[] args) {
		Board board = new Board();
		board.setBoardAtStart();
		board.changeContent(CellContent.bomb, board.getGrid(), 1, 2);
		board.changeContent(CellContent.bomb, board.getGrid(), 3, 2);
		board.changeContent(CellContent.bomb, board.getGrid(), 6, 4);
		System.out.println("Start positions: ");
		board.printBoard();
		Scanner scanner = new Scanner(System.in);
		System.out.print("To end the game, enter '~'.");
		String input = "";
		while(input != "~") {
			System.out.println(" Enter row and column coordinates of the cell you want to explore: ");
			input = scanner.next();
			int row = Integer.valueOf(input);
			input = scanner.next();
			int col = Integer.valueOf(input);
			if(!board.click(row,col)) {
				System.out.println("Try again with different coordinates.");
				continue;
			};
			board.printBoard();
		}
//		for(Cell c: board.getGrid()[2][1].getAdjacents()) {
//			System.out.println(c.getContent());
//		}
	}
}
