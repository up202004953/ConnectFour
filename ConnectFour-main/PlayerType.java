public enum PlayerType {
    Player("Player", 'X'),
    Computer("Computer", 'O');

    private String name;
    private char symbol;

    PlayerType(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {return name;}
    public char getSymbol() {return symbol;}

    public void change() {
        if (this.getName().equals("Player")) {
            this.name = "Computer";
            this.symbol = 'O';
        } else {
            this.name = "Player";
            this.symbol = 'X';
        }
    }
}

