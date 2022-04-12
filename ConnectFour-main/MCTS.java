import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Math;

class MCTS {

	public static Node bestUCB(ArrayList<Node> desc, PlayerType pt) {		// from a list of nodes, it choses the one with the best UCB (or the first that
											// hasn't been visited
		Node solution = desc.get(0);
		double max = 0;
		for (int i = 0; i < desc.size(); i++) {
			Node cur = desc.get(i);
			double t = cur.getT();
			double n = cur.getN();
			int N = cur.getParent().getN();
//			System.out.println("teste");
			if (cur.getN() == 0) { return cur; }
			double ucb = t/n + (2 * Math.sqrt(Math.log(N)/n));
			System.out.println(ucb);
			if (ucb > max) {
				max = ucb;
				solution = cur;
			}
		}
		return solution;
	}

	public static int rollout(Node pick, PlayerType pt) {				// after specified node, it does a rollout
		Random r = new Random();
		Node cur = pick;
		while(true) {
			ArrayList<Node> desc = cur.getSucc(pt);
			pt.change();
			int rand = r.nextInt(desc.size());
//			System.out.println(rand);
			cur = desc.get(rand);
			if (cur.getBoard().isFinal()) {
				return cur.getBoard().getUtility();
			}
		}
	}

	public static Board runMCTS (Board b, PlayerType pt, int limit) {
		Node root = new Node(b, null);
		ArrayList<Node> firstDesc = root.getSucc(pt);
		root.setDesc(firstDesc);
		Node cur = root;
		PlayerType tempPT;
		for (int i = 0; i < limit; i++) {
			tempPT = pt;
//			System.out.println(cur.getN());
			while(cur.getDesc() != null) {
				cur = bestUCB(cur.getSucc(tempPT), tempPT);
//				System.out.println(cur.getN());
//				System.out.println(cur.getBoard());
				tempPT.change();
			}
			if (cur.getBoard().isFinal()) {
				if (cur.getBoard().getUtility() == 512) { return cur.getBoard(); }
			}
			cur.setDesc(cur.getSucc(tempPT));
			int v = rollout(cur, tempPT);
			while(cur.getParent() != null) {
				cur.setT(v+cur.getT());
				cur.setN(cur.getN()+1);
//				System.out.println(cur.getN());
				cur = cur.getParent();
				for (int j = 0; j < cur.getDesc().size(); j++) {
					System.out.print(cur.getDesc().get(j).getN() + " ");
					System.out.print(cur.getDesc().get(j).getT() + " ");
				}
				System.out.println();
			}
		}
		return bestUCB(firstDesc, pt).getBoard();
	}

/*	public static void doMCTS(Board b, PlayerType pt, int limit) {
		Board result = runMCTS(b, pt, limit);
		System.out.println(result);
	}
*/
}
