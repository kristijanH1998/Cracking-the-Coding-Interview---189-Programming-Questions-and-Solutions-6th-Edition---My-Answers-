package objectOrientedDesign;
import java.util.ArrayList;
enum player{w, b, empty};


abstract class User{
	protected ArrayList<Square> validMoves;
	protected int score;
}

class White extends User{
	private boolean turn = false;
	public boolean playsNext() {
		return turn;
	}
}

class Black extends User{
	private boolean turn = true;
	public boolean playsNext() {
		return turn;
	}
}

//Attributes Square N - Square SE are pointers to squares up, down, to the right, to the left, and in diagonal directions
//from the given Square's perspective, on the grid(board)
class Square{
	private int row;
	private int col;
	private player disc;
	public Square N;
	public Square W;
	public Square S;
	public Square E;
	public Square NW;
	public Square NE;
	public Square SW;
	public Square SE;
	
	public Square(int row, int col, player disc) {
		this.row = row;
		this.col = col;
		if(disc != null) {
			this.disc = disc;
		}
	}
	public int getRow() {
		return this.row;
	}
	public int getCol() {
		return this.col;
	}
	public player getPlayer() {
		return this.disc;
	}
}

class GameOthello{
	public static final int boardLength = 8;
	public static final int boardHeight = 8;
	private ArrayList<Square> emptySquares;
	private ArrayList<Square> filledSquares;
	private User winner;
	public GameOthello(){
		ArrayList<Square> emptySquares = new ArrayList<Square>();
		ArrayList<Square> filledSquares = new ArrayList<Square>();
		this.emptySquares = emptySquares;
		this.filledSquares = filledSquares;
	}
	
	public ArrayList<Square> getEmptySquares() {
		return this.emptySquares;
	}
	
	public ArrayList<Square> getFilledSquares(){
		return this.filledSquares;
	}
	
	public User declareWinner() {
		return this.winner;
	}
	
	public void setAdjacents(Square s, Square[][] board, int i, int j) {
		s.N = (i-1 >= 0) ? board[i-1][j] : null;
		s.W = (j-1 >= 0) ? board[i][j-1] : null;
		s.S = (i+1 < board.length) ? board[i+1][j] : null;
		s.E = (j+1 < board[0].length) ? board[i][j+1] : null;
		s.NW = ((i-1 >= 0) && (j-1 >= 0)) ? board[i-1][j-1] : null;
		s.NE = ((i-1 >= 0) && (j+1 < board[0].length)) ? board[i-1][j+1] : null;
		s.SW = ((i+1 < board.length) && (j-1 >= 0)) ? board[i+1][j-1] : null;
		s.SE = ((i+1 < board.length) && (j+1 < board[0].length)) ? board[i+1][j+1] : null;
	}
	
	public Square[][] createStartPosition(){
		Square[][] board = new Square[boardLength][boardHeight];
		for (int i = 0; i < boardHeight; i++) {
			for(int j = 0; j < boardLength;j++) {
				Square s = new Square(i,j,player.empty);
				board[i][j] = s;
			}
		}
		Square s1 = new Square(3,3,player.w);
		board[3][3] = s1;
		Square s2 = new Square(3,4,player.b);
		board[3][4] = s2;
		Square s3 = new Square(4,3,player.b);
		board[4][3] = s3;
		Square s4 = new Square(4,4,player.w);
		board[4][4] = s4;
		
		for (int i = 0; i < boardHeight; i++) {
			for(int j = 0; j < boardLength;j++) {
				setAdjacents(board[i][j], board, i, j);
			}
		}
		setAdjacents(s1, board, 3, 3);
		setAdjacents(s2, board, 3, 4);
		setAdjacents(s3, board, 4, 3);
		setAdjacents(s4, board, 4, 4);
		return board;
	}
}

public class Othello {
	public static void playOthello(GameOthello game, White white, Black black) {
		Square[][] board = game.createStartPosition();
//		for (int i = 0; i < board.length; i++) {
//			for(int j = 0; j < board[0].length;j++) {
//				if(board[i][j].getPlayer() != null) {
//					System.out.print(board[i][j].getPlayer() + " "); 
//				} else {
//					System.out.print("null"); 
//
//				}
//			}
//			System.out.println();
//		}
		Square temp = board[0][7];
		while(temp != null) {
			System.out.print(temp.getPlayer() + " ");
			temp = temp.SW;
		}
	}
	public static void main(String[] args) {
		GameOthello g = new GameOthello();
		White w = new White();
		Black b = new Black();
		playOthello(g, w, b);
	}
}