import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class MCTS {
    private static final Random r = new Random();
    private static final int limit = 50042;

    public static Board run(Board b) {
        Node root = new Node(b, null, PlayerType.Player);
        root.generate();

        for (int i = 0; i < limit; i++) {
            Node cur = root;

            while(!cur.getSucc().isEmpty()) {
                cur = cur.getSucc().get(bestUCB(cur.getSucc()));
                cur.addN();
            }

            int v = rollout(cur);
            while(cur.getParent() != null) {
                cur.addT(v);
                cur.addN();

                cur = cur.getParent();
            }
            cur.addT(v);
            cur.addN();
        }

        return root.getSucc().get(bestUCB(root.getSucc())).getBoard();
    }

    public static int bestUCB(List<Node> desc) {		// from a list of nodes, it choses the one with
        int pos = 0;                                   // the best UCB or the first that hasn't been visited

        if (desc.get(0).getN() == 0) return pos;

        PlayerType pt = desc.get(0).getPt();

        double best = (double) (desc.get(0).getT()) / desc.get(0).getN() +
                (2 * Math.sqrt(Math.log(desc.get(0).getParent().getN()) / desc.get(0).getN()));

        for (int i = 1; i < desc.size(); i++) {
            if (desc.get(i).getN() == 0) return i;

            double ucb = (double) (desc.get(i).getT()) / desc.get(i).getN() +
                    (2 * Math.sqrt(Math.log(desc.get(i).getParent().getN()) / desc.get(i).getN()));

            if ((pt.name().equals(PlayerType.Player.name()) && ucb < best) ||
                    (pt.name().equals(PlayerType.Computer.name()) && ucb > best)) {
                best = ucb;
                pos = i;
            }
        }

        return pos;
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
