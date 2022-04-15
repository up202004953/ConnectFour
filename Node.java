import java.util.ArrayList;
import java.util.List;

class Node {
    private final Board board;
    private final Node parent;
    private int n = 0;						// nr of times node has been visited
    private int t = 0;						// value given by the rollouts
    private final PlayerType pt;
    private final List<Node> succ = new ArrayList<>();


    public Node(Board board, PlayerType pt) {
        this.board = board;
        this.parent = null;
        this.pt = pt;
    }

    public Node(Board board, Node parent, PlayerType pt) {
        this.board = board;
        this.parent = parent;
        this.pt = pt;
    }

    public Board getBoard() {return board;}
    public Node getParent() {return parent;}
    public PlayerType getPt() {return pt;}
    public int getN() {return n;}
    public int getT() {return t;}
    public void addN() {n++;}
    public void addT(int t) {this.t += t;}
    public List<Node> getSucc() {return succ;}

    public int terminal() {return board.terminal(pt);}
    public void generate() {
        if (!succ.isEmpty()) return;

        PlayerType nextPt = PlayerType.change(pt);
        for (Board b : board.getSucc(nextPt)) succ.add(new Node(b, this, nextPt));
    }

    @Override
    public String toString() {
        return board.toString();
    }
}