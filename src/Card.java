public class Card {
    private CardShape shape;
    private CardValue value;
    private String name;

    Card(CardShape shape, CardValue value) {
        this.setCardShape(shape);
        this.setCardValue(value);
        this.setName();
    }

    public void setName() {
        if(this.shape == CardShape.NONE){
            this.name = this.value.getSymbol();
        }else{
            this.name = this.shape.getSymbol() + this.value.getSymbol();
        }
    }

    public String getName() {
        return this.name;
    }

    public void setCardShape(CardShape shape) {
        this.shape = shape;
    }

    public CardShape getCardShape() {
        return this.shape;
    }

    public void setCardValue(CardValue value) {
        this.value = value;
    }

    public CardValue getCardValue() {
        return this.value;
    }

    public Match matches(Card newCard) {

        if (this.value.getValue().equals(newCard.value.getValue())) {
            return Match.VALUE;
            // Nothing for function just yet

        } else if (this.shape == newCard.shape) {
            return Match.SHAPE;

        } else if (this.shape.getColor().equals(newCard.shape.getColor())) {
            return Match.COLOR;

        } else {
            return Match.NOT;
        }
    }
}
