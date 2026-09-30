enum CardValue {
    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(10, "J"),
    QUEEN(10, "Q"),
    KING(10, "K"),
    JESTER(0, "Jester");

    private final Integer value;
    private final String symbol;

    CardValue(Integer value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getSymbol(){
        return this.symbol;
    }
}