package ConnectFour;

public enum PlayerType {
    Player("Player", 'X'),
    Computer("Computer", 'O');

    private final String name;
    private final char symbol;

    PlayerType(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {return name;}
    public char getSymbol() {return symbol;}
}
