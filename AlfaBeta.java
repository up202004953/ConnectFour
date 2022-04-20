import java.util.ArrayList;

public class AlfaBeta {
    private static final int limit = 8;
    private static final int pos_inf = 999;
    private static final int neg_inf = -999;

    public static Board run(Board cur) {
        Board next = null;

        int v = neg_inf;
        for (Board s : cur.getSucc(PlayerType.Computer)) {
            int n = Min(s, 0, neg_inf, pos_inf);
            if (v < n) {
                v = n;
                next = new Board(s.getMatrix(), s.getHigh(), s.getWidth(), s.getHeight());
            }
        }

        return next;
    }

    private static int Max(Board cur, int depth, int alfa, int beta) {
        if (depth == limit) return cur.getUtility();

        int t = cur.terminal(PlayerType.Computer);
        if (t == -512 || t == 0 || t == 512) return t;

        ArrayList<Board> succ = (ArrayList<Board>) cur.getSucc(PlayerType.Computer);

        int v = neg_inf;
        for (Board b : succ) {
            v = Math.max(v, Min(b,depth+1, alfa, beta));

            if (v >= beta) return v;

            alfa = Math.max(alfa, v);
        }

        return v;
    }

    private static int Min(Board cur, int depth, int alfa, int beta) {
        if (depth == limit) return cur.getUtility();

        int t = cur.terminal(PlayerType.Player);
        if (t == -1 || t == 0 || t == 1) return t;

        ArrayList<Board> succ = (ArrayList<Board>) cur.getSucc(PlayerType.Player);

        int v = pos_inf;
        for (Board b : succ) {
            v = Math.min(v, Max(b, depth+1, alfa, beta));
            if (v <= alfa) return v;

            beta = Math.min(beta, v);
        }

        return v;
    }
}
