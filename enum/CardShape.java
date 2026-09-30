enum CardShape {
    DIAMOND("red", "♦"),
    HEARTH("red", "♥"),
    SPADE("black", "♠"),
    CLOVER("black", "♣"),
    NONE("", "");

    private final String color;
    private final String symbol;

    CardShape(String color, String symbol) {
        this.color = color;
        this.symbol = symbol;
    }

    public String getColor() {
        return this.color;
    }
    public String getSymbol() {
        return this.symbol;
    }
}