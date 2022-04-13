public enum PlayerType {
    Player("Player", 'X'),
    Computer("Computer", 'O');

    private final String name;
    private final char symbol;

    PlayerType(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public static PlayerType change(PlayerType pt) {
        if (pt.name().equals(PlayerType.Player.name())) return PlayerType.Computer;
        else return PlayerType.Player;
    }

    public String getName() {return name;}
    public char getSymbol() {return symbol;}
}
