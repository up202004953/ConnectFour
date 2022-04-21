import java.util.ArrayList;

public class MinMax {
    private static final int limit = 6;
    private static final int pos_inf = 999;
    private static final int neg_inf = -999;

    public static Board run(Board cur) {
        Board next = null;

        int v = neg_inf;
        for (Board s : cur.getSucc(PlayerType.Computer)) {
            int n = Min(s, 0);
            if (v < n) {
                v = n;
                next = new Board(s.getMatrix(), s.getHigh(), s.getWidth(), s.getHeight());
            }
        }

        return next;
    }

    private static int Max(Board cur, int depth) {
        if (depth == limit) return cur.getUtility();

        int t = cur.terminal(PlayerType.Computer);
        if (t == -512 || t == 0 || t == 512) return t;

        ArrayList<Board> succ = (ArrayList<Board>) cur.getSucc(PlayerType.Computer);

        int v = neg_inf;
        for (Board b : succ) {
            v = Math.max(v, Min(b,depth+1));
        }

        return v;
    }

    private static int Min(Board cur, int depth) {
        if (depth == limit) return cur.getUtility();

        int t = cur.terminal(PlayerType.Player);
        if (t == -512 || t == 0 || t == 512) return t;

        ArrayList<Board> succ = (ArrayList<Board>) cur.getSucc(PlayerType.Player);

        int v = pos_inf;
        for (Board b : succ) {
            v = Math.min(v, Max(b, depth+1));
        }

        return v;
    }
}
