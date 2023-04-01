package objectOrientedDesign;
import java.util.ArrayList;
import java.util.Collections;

enum EdgeType{tab, blank, straight};

class JigsawPuzzle{
	Piece[][] image;
	ArrayList<Piece> pieceList;
	public void shuffle(ArrayList<Piece> pieces) {
		Collections.shuffle(pieces);
	}
	public void placePiece(Piece p, Piece[][] image, int i, int j) {
		image[i][j] = p;
	}
	public void solve() {
		for(Piece p: this.pieceList) {
			if(p.getTopEdge() == EdgeType.straight && p.getLeftEdge() == EdgeType.straight) {
				this.placePiece(p, this.image, 0, 0);
				continue;
			}
			if(p.getTopEdge() == EdgeType.straight && p.getRightEdge() == EdgeType.straight) {
				this.placePiece(p, this.image, 0, this.image.length - 1);
				continue;
			}
			if(p.getBottomEdge() == EdgeType.straight && p.getLeftEdge() == EdgeType.straight) {
				this.placePiece(p, this.image, this.image[0].length - 1, 0);
				continue;
			}
			if(p.getBottomEdge() == EdgeType.straight && p.getRightEdge() == EdgeType.straight) {
				this.placePiece(p, this.image, this.image.length - 1, this.image[0].length - 1);
				continue;
			}
			outerloop:
			for(int i = 0; i < image.length; i++) {
				for(int j = 0; j < image[0].length; j++) {
					if(image[i][j] != null) {
						continue;
					}
					if((j == 0 || j == (image[0].length - 1)) && p.fitsWith(image[i-1][j]) && p.fitsWith(image[i+1][j])) {
						this.placePiece(p, this.image, i, j);
					} else if ((i == 0 || i == (image.length - 1))  &&  p.fitsWith(image[i][j-1]) && p.fitsWith(image[i][j+1])) {
						this.placePiece(p, this.image, i, j);
					} else if(p.fitsWith(image[i][j-1]) && p.fitsWith(image[i][j+1]) && p.fitsWith(image[i-1][j]) && p.fitsWith(image[i+1][j])){
						this.placePiece(p, this.image, i, j);
					}
					break outerloop;
				}
			}
		}
	}
	
	public JigsawPuzzle(Piece[][] image, ArrayList<Piece> pieceList) {
		this.image = image;
		this.pieceList = pieceList;
	}
}
class Piece {
	int value = 0;
	private EdgeType top;
	private EdgeType bottom;
	private EdgeType right;
	private EdgeType left;	
	
	public Piece (int value, EdgeType top, EdgeType bottom, EdgeType right, EdgeType left){
		this.value = value;
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}
		
	public EdgeType getTopEdge() {
		return this.top;
	}
	
	public EdgeType getBottomEdge() {
		return this.bottom;
	}

	public EdgeType getLeftEdge() {
		return this.left;
	}

	public EdgeType getRightEdge() {
		return this.right;
	}
	
	public int getValue() {
		return this.value;
	}
	public boolean fitsWith(Piece p) {
		if(p == null) {
			return true;
		}
		if(this.right != EdgeType.straight && p.left != EdgeType.straight && this.right != p.left) {
			return true;
		}
		if(this.left != EdgeType.straight && p.right != EdgeType.straight && this.left != p.right) {
			return true;
		}
		if(this.top != EdgeType.straight && p.bottom != EdgeType.straight && this.top != p.bottom) {
			return true;
		}
		if(this.bottom != EdgeType.straight && p.top != EdgeType.straight && this.bottom != p.top) {
			return true;
		}
		return false;
	}
}

public class Jigsaw {

	public static void main(String[] args) {
		Piece[][] image = new Piece[3][3];
		ArrayList<Piece> listOfPieces = new ArrayList<Piece>();
		
		Piece p1 = new Piece(1, EdgeType.straight, EdgeType.tab, EdgeType.blank, EdgeType.straight);
		listOfPieces.add(p1);
		Piece p2 = new Piece(2, EdgeType.straight, EdgeType.blank, EdgeType.tab, EdgeType.tab);
		listOfPieces.add(p2);
		Piece p3 = new Piece(1, EdgeType.straight, EdgeType.tab, EdgeType.straight, EdgeType.blank);
		listOfPieces.add(p3);
		Piece p4 = new Piece(2, EdgeType.blank, EdgeType.tab, EdgeType.blank, EdgeType.straight);
		listOfPieces.add(p4);
		Piece p5 = new Piece(2, EdgeType.tab, EdgeType.tab, EdgeType.tab, EdgeType.tab);
		listOfPieces.add(p5);
		Piece p6 = new Piece(2, EdgeType.blank, EdgeType.tab, EdgeType.straight, EdgeType.blank);
		listOfPieces.add(p6);
		Piece p7 = new Piece(1, EdgeType.blank, EdgeType.straight, EdgeType.blank, EdgeType.straight);
		listOfPieces.add(p7);
		Piece p8 = new Piece(2, EdgeType.blank, EdgeType.straight, EdgeType.tab, EdgeType.tab);
		listOfPieces.add(p8);
		Piece p9 = new Piece(1, EdgeType.blank, EdgeType.straight, EdgeType.straight, EdgeType.blank);
		listOfPieces.add(p9);

		JigsawPuzzle puzzle = new JigsawPuzzle(image, listOfPieces);
		puzzle.solve();
		for(int i = 0; i < image.length; i++) {
			for(int j = 0; j < image[0].length; j++) {
				if(image[i][j] != null) {
					System.out.print(image[i][j].getValue() + " ");
				}
			}
			System.out.println();
		}
	}
}