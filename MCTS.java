import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class MCTS {
    private static final Random r = new Random();
    private static final int limit = 10042;

    public static Board run(Board b) {
        Node root = new Node(b, null, PlayerType.Player);
        root.generate();

        for (int i = 0; i < limit; i++) {
            Node cur = root;

            while(!cur.getSucc().isEmpty()) {
                cur = cur.getSucc().get(bestUCB(cur.getSucc()));
            }

            int v = rollout(cur);

            cur.delete();

            while(cur.getParent() != null) {
                if (v == 512) cur.addT();
                cur.addN();

                cur = cur.getParent();
            }

            if (v == 512) cur.addT();
            cur.addN();
        }

        return root.getSucc().get(bestUCB(root.getSucc())).getBoard();
    }

    public static int bestUCB(List<Node> desc) {		// from a list of nodes, it choses the one with
        int pos = 0;                                   // the best UCB or the first that hasn't been visited

        if (desc.get(0).getN() == 0) return pos;

        double best = ucb(desc.get(0));

        for (int i = 1; i < desc.size(); i++) {
            if (desc.get(i).getN() == 0) return i;

            double ucb = ucb(desc.get(i));

            if (ucb > best) {
                best = ucb;
                pos = i;
            }
        }

        return pos;
    }

    private static double ucb(Node n) {
        return (double) (n.getT()) / n.getN() + Math.sqrt(2 * Math.log(n.getParent().getN()) / n.getN());
    }

    public static int rollout(Node pick) {				// after specified node, it does a rollout
        Node cur = pick;

        while(true) {
            int t = cur.terminal();
            if (t == -512 || t == 0 || t == 512) return t;

            cur.generate();
            ArrayList<Node> desc = (ArrayList<Node>) cur.getSucc();

            cur = desc.get(r.nextInt(desc.size()));
        }
    }
}
