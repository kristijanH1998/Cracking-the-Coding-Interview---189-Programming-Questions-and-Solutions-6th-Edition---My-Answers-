package objectOrientedDesign;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
enum player{w, b, empty};
enum Direction{N,W,S,E,NW,NE,SW,SE};
abstract class User{
	protected ArrayList<Square> validMoves;
	protected int score;
	protected boolean turn;
	public int getScore() {
		return this.score;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	public boolean getTurn() {
		return this.turn;
	}
}
class White extends User{
	public White(boolean turn) {
		this.turn = turn;
		this.score = 2;
	}
}
class Black extends User{
	public Black(boolean turn) {
		this.turn = turn;
		this.score = 2;
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
	public Square() {
	}
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
	public void setPlayer(player p) {
		this.disc = p;
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
	public boolean checkCurrent(Square pointed) {
		return pointed == null || pointed.getPlayer() == player.empty;
	}
	public boolean placeDisc(Square[][] board, int i, int j, player p, User user1, User user2) {
		user1.score++;
		Square current = board[i][j];
		if(		(checkCurrent(current.N) && 
				checkCurrent(current.W) && 
				checkCurrent(current.E) && 
				checkCurrent(current.S) &&
				checkCurrent(current.NW) && 
				checkCurrent(current.NE) && 
				checkCurrent(current.SW) && 
				checkCurrent(current.SE)) || board[i][j].getPlayer() != player.empty) {
			System.out.println("Error. Illegal move.");
			return false;
		} 
		board[i][j].setPlayer(p);
		HashMap<Integer, ArrayList<Square>> lineSizes = new HashMap<Integer, ArrayList<Square>>();
		HashMap<Direction, ArrayList<Square>> directions = new HashMap<Direction, ArrayList<Square>>();		

		ArrayList<Square> nlist = getNList(board, i, j);
		lineSizes.put(nlist.size(), nlist);
		directions.put(Direction.N, nlist);
		ArrayList<Square> wlist = getWList(board, i, j);
		lineSizes.put(wlist.size(), wlist);
		directions.put(Direction.W, wlist);
		ArrayList<Square> elist = getEList(board, i, j);
		lineSizes.put(elist.size(), elist);
		directions.put(Direction.E, elist);
		ArrayList<Square> slist = getSList(board, i, j);
		lineSizes.put(slist.size(), slist);
		directions.put(Direction.S, slist);
		ArrayList<Square> nwlist = getNWList(board, i, j);
		lineSizes.put(nwlist.size(), nwlist);
		directions.put(Direction.NW, nwlist);
		ArrayList<Square> nelist = getNEList(board, i, j);
		lineSizes.put(nelist.size(), nelist);
		directions.put(Direction.NE, nelist);
		ArrayList<Square> swlist = getSWList(board, i, j);
		lineSizes.put(swlist.size(), swlist);
		directions.put(Direction.SW, swlist);
		ArrayList<Square> selist = getSEList(board, i, j);
		lineSizes.put(selist.size(), selist);
		directions.put(Direction.SE, selist);
		int maxLineSize = Collections.max(lineSizes.keySet());
		Direction dir = null;
		for(Direction d: directions.keySet()) {
			if(directions.get(d).size() == maxLineSize) {
				dir = d;
				break;
			}
		}
		Square temp = new Square();
		switch(dir) {
			case N:	
				temp = board[i][j].N;
				while(temp != null && temp.getPlayer() != p) {
					temp.setPlayer(p);
					user1.score++;
					user2.score--;
					temp = temp.N;
				}
				break;
			case W:				
				temp = board[i][j].W;
				while(temp != null && temp.getPlayer() != p) {
					temp.setPlayer(p);
					user1.score++;
					user2.score--;
					temp = temp.W;
				}
				break;
			case E:				
				temp = board[i][j].E;
				while(temp != null && temp.getPlayer() != p) {
					temp.setPlayer(p);
					user1.score++;
					user2.score--;
					temp = temp.E;
				}
				break;
			case S:				
				temp = board[i][j].S;
				while(temp != null && temp.getPlayer() != p) {
					temp.setPlayer(p);
					user1.score++;
					user2.score--;
					temp = temp.S;
				}
				break;
			case NW:				
				temp = board[i][j].NW;
				while(temp != null && temp.getPlayer() != p) {
					temp.setPlayer(p);
					user1.score++;
					user2.score--;
					temp = temp.NW;
				}
				break;
			case NE:				
				temp = board[i][j].NE;
				while(temp != null && temp.getPlayer() != p) {
					temp.setPlayer(p);
					user1.score++;
					user2.score--;
					temp = temp.NE;
				}
				break;
			case SW:				
				temp = board[i][j].SW;
				while(temp != null && temp.getPlayer() != p) {
					temp.setPlayer(p);
					user1.score++;
					user2.score--;
					temp = temp.SW;
				}
				break;
			case SE:				
				temp = board[i][j].SE;
				while(temp != null && temp.getPlayer() != p) {
					temp.setPlayer(p);
					user1.score++;
					user2.score--;
					temp = temp.SE;
				}
				break;
		}
		return true;
	}
	public ArrayList<Square> getNList(Square[][] board, int i, int j){
		ArrayList<Square> nlist = new ArrayList<Square>();
		if(i-1 < 0) {
			return null;
		}
		Square temp = board[i-1][j];
		while(temp != null && temp.getPlayer() != board[i][j].getPlayer()) {
			nlist.add(temp); 
			temp = temp.N;
		}
		if(temp == null) {
			nlist.clear();
		}
		return nlist;
	}
	public ArrayList<Square> getWList(Square[][] board, int i, int j){
		ArrayList<Square> wlist = new ArrayList<Square>();
		if(j-1 < 0) {
			return null;
		}
		Square temp = board[i][j-1];
		while(temp != null && temp.getPlayer() != board[i][j].getPlayer()) {
			wlist.add(temp); 
			temp = temp.W;
		}
		if(temp == null) {
			wlist.clear();
		}
		return wlist;
	}
	public ArrayList<Square> getEList(Square[][] board, int i, int j){
		ArrayList<Square> elist = new ArrayList<Square>();
		if(j+1 > board.length-1) {
			return null;
		}
		Square temp = board[i][j+1];
		while(temp != null && temp.getPlayer() != board[i][j].getPlayer()) {
			elist.add(temp); 
			temp = temp.E;
		}
		if(temp == null) {
			elist.clear();
		}
		return elist;
	}
	public ArrayList<Square> getSList(Square[][] board, int i, int j){
		ArrayList<Square> slist = new ArrayList<Square>();
		if(i+1 > board.length-1) {
			return null;
		}
		Square temp = board[i+1][j];
		while(temp != null && temp.getPlayer() != board[i][j].getPlayer()) {
			slist.add(temp); 
			temp = temp.S;
		}
		if(temp == null) {
			slist.clear();
		}
		return slist;
	}
	public ArrayList<Square> getNWList(Square[][] board, int i, int j){
		ArrayList<Square> nwlist = new ArrayList<Square>();
		if(i-1 < 0 || j-1 < 0) {
			return null;
		}
		Square temp = board[i-1][j-1];
		while(temp != null && temp.getPlayer() != board[i][j].getPlayer()) {
			nwlist.add(temp); 
			temp = temp.NW;
		}
		if(temp == null) {
			nwlist.clear();
		}
		return nwlist;
	}
	public ArrayList<Square> getNEList(Square[][] board, int i, int j){
		ArrayList<Square> nelist = new ArrayList<Square>();
		if(i-1 < 0 || j+1 > board.length-1) {
			return null;
		}
		Square temp = board[i-1][j+1];
		while(temp != null && temp.getPlayer() != board[i][j].getPlayer()) {
			nelist.add(temp); 
			temp = temp.NE;
		}
		if(temp == null) {
			nelist.clear();
		}
		return nelist;
	}
	public ArrayList<Square> getSWList(Square[][] board, int i, int j){
		ArrayList<Square> swlist = new ArrayList<Square>();
		if(i+1 > board.length-1 || j-1 < 0) {
			return null;
		}
		Square temp = board[i+1][j-1];
		while(temp != null && temp.getPlayer() != board[i][j].getPlayer()) {
			swlist.add(temp); 
			temp = temp.SW;
		}
		if(temp == null) {
			swlist.clear();
		}
		return swlist;
	}
	public ArrayList<Square> getSEList(Square[][] board, int i, int j){
		ArrayList<Square> selist = new ArrayList<Square>();
		if(i+1 > board.length-1 || j+1 > board.length-1) {
			return null;
		}
		Square temp = board[i+1][j+1];
		while(temp != null && temp.getPlayer() != board[i][j].getPlayer()) {
			selist.add(temp); 
			temp = temp.SE;
		}
		if(temp == null) {
			selist.clear();
		}
		return selist;
	}
	public static void printBoard(Square[][] board) {
		for (int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length;j++) {
				if(board[i][j].getPlayer() != null) {
					System.out.print(board[i][j].getPlayer() + "	"); 
				} else {
					System.out.print("null"); 
				}
			}
			System.out.println();
		}
	}
}
public class Othello {
	public static void playOthello(GameOthello game, White white, Black black) {
		Square[][] board = game.createStartPosition();
		game.printBoard(board);
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Othello. To exit, enter '~'.");
		String userInput = "";
		String firstCoordinate = "";
		String secondCoordinate = "";
		
		while(!userInput.equals("~")) {
			System.out.println("Black's score: " + black.score);
			System.out.println("White's score: " + white.score);
			System.out.print("Enter the row and column of the field to place the disk in. ");
			if(black.getTurn() == true) {
				System.out.println("Black's turn:");
				userInput = input.nextLine();
				firstCoordinate = userInput;
				userInput = input.nextLine();
				secondCoordinate = userInput;
				if(game.placeDisc(board, Integer.valueOf(firstCoordinate), Integer.valueOf(secondCoordinate), player.b, black, white)) {
					white.setTurn(true);
					black.setTurn(false);
				} else{
					white.setTurn(false);
					black.setTurn(true);
				};
				
			} else {
				System.out.println("White's turn:");
				userInput = input.nextLine();
				firstCoordinate = userInput;
				userInput = input.nextLine();
				secondCoordinate = userInput;
				if(game.placeDisc(board, Integer.valueOf(firstCoordinate), Integer.valueOf(secondCoordinate), player.w, white, black)) {
					white.setTurn(false);
					black.setTurn(true);
				} else{
					white.setTurn(true);
					black.setTurn(false);
				};
			}
			game.printBoard(board);
		}
	}
	public static void main(String[] args) {
		GameOthello g = new GameOthello();
		White w = new White(false);
		Black b = new Black(true);
		playOthello(g, w, b);
	}
}