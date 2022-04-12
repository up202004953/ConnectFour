import java.util.Arrays;
import java.util.ArrayList;

class Node {

	private int n = 0;						// nr of times node has been visited
	private int t = 0;						// value given by the rollouts
	private Node parent;
	private Board b;
	private ArrayList<Node> desc = null;				// list of descendent nodes

	public Node(Board b, Node parent) {
		this.b = b;
		this.parent = parent;
	}

	public void setN(int n) { this.n = n; }
	public void setT(int t) { this.t = t; }
	public void setBoard(Board b) { this.b = b; }
	public void setParent(Node parent) {this.parent = parent; }
	public void setDesc(ArrayList<Node> desc) { this.desc = desc; }


	public int getN() { return n; }
	public int getT() { return t; }
	public Board getBoard() {return b; }
	public Node getParent() { return parent; }
	public ArrayList<Node> getDesc() { return desc; }

	public ArrayList<Node> getSucc(PlayerType pt) {
		ArrayList<Node> succ = new ArrayList<>();

		int width = b.getWidth();
		int[] high = b.getHigh();
		int height = b.getHeight();
		char[][] matrix = b.getMatrix();

		for (int n = 0; n < width; n++) {
			if (high[n] < height) {
				char[][] curMatrix = new char[width][height];
				int[] curHigh = new int[width];

				for (int i = 0; i < width; i++) {
					curHigh[i] = high[i];
					for (int j = 0; j < height; j++) {
						if (n == i && j == high[i]) {
							curMatrix[i][j] = pt.getSymbol();
							curHigh[i]++;
						}
						else curMatrix[i][j] = matrix[i][j];
					}
				}
				Board b = new Board(curMatrix, curHigh, width, height);
				succ.add(new Node(b, this));
			}
		}
		return succ;

	}
}
