import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Deck {
    ArrayList<Card> mainStack = new ArrayList<>();
    ArrayList<Card> discardStack = new ArrayList<>();
    ArrayList<ArrayList<Card>> hands = new ArrayList<>();

    Deck(Integer amountHands, Integer handSize, Integer jesters) {
        for (CardShape shape : CardShape.values()) {
            if (shape == CardShape.NONE) {
                continue;
            }

            for (CardValue value : CardValue.values()) {
                if (value == CardValue.JESTER) {
                    continue;
                }

                this.mainStack.add(new Card(shape, value));
            }
        }

        if (jesters > 0) {
            for (int index = 0; index < jesters; index++) {
                this.mainStack.add(new Card(CardShape.NONE, CardValue.JESTER));
            }
        }

        this.shuffle();
        this.deal(amountHands, handSize);
    }

    public void playCardFromMain() {
        int firstCardIndex = 0;

        if (this.cardsSize() == 0) {
            System.out.println("No more cards on the mainstack.");

            if (this.refillCards()) {
                this.playCardFromMain();

            } else {
                System.out.println("Discard stack is empty, can't refill and deal to main.");
            }
        } else {
            Card firstCard = this.mainStack.getFirst();
            this.discardStack.add(firstCard);
            this.mainStack.removeFirst();
        }
    }

    public void shuffle() {
        Collections.shuffle(this.mainStack);
    }

    public Integer cardsSize() {
        return this.mainStack.size();
    }

    private void addHand() {
        this.hands.add(new ArrayList<>());
    }

    private void deal(int hands, int handSize) {
        for (int index = 0; index < hands; index++) {
            this.addHand();
            this.drawCards(index, handSize);
        }
    }

    private boolean refillCards() {
        if (this.discardStack.isEmpty()) {
            System.out.println("No more cards to add the mainstack");
            return false;
        }

        List<Card> cardsToAdd = this.discardStack.subList(1, this.discardStack.size() - 1);
        this.mainStack.addAll(cardsToAdd);
        cardsToAdd.clear();

        this.shuffle();
        return true;
    }

    public void drawCards(int handIndex, int amountOfCards) {
        if (this.mainStack.size() >= amountOfCards) {
            int lastCardsIndex = cardsSize() - 1;

            List<Card> cards = this.mainStack.subList((lastCardsIndex - amountOfCards), lastCardsIndex);

            this.hands.get(handIndex).addAll(cards);
            cards.clear();

        } else {
            if (!this.refillCards()) {
                return;
            }
            this.drawCards(handIndex, amountOfCards);
        }
    }

    public boolean playCard(ArrayList<Card> cardsStack, int cardIndex) {
        Card card = cardsStack.get(cardIndex);
        Match match = card.matches(this.discardStack.getLast());

        if (match.getMatchInt() != 0) {
            this.discardStack.add(card);
            cardsStack.remove(cardIndex);
            return true;

        } else {
            return false;
        }
    }

    public ArrayList<Card> getHand(int handIndex) {
        return this.hands.get(handIndex);
    }

    public void showHand(int handIndex) {
        ArrayList<Card> hand = this.hands.get(handIndex);
        System.out.println("\n");

        for (Card card : hand) {
            System.out.print(card.getName() + "  ");
        }

        System.out.print("\n");
    }

    public Card getMainCard(){

        if (this.discardStack.isEmpty()) {
            System.out.println("The discard stack didn't have any cards left");
            return null;
        }

        return this.discardStack.getFirst();
    }

    public List<Card> playableCards(int handIndex, ArrayList<Match> hasToMatch){
        Card mainCard = this.getMainCard();
        ArrayList<Card> hand = this.getHand(handIndex);

        return hand.stream().filter( card -> hasToMatch.contains(this.matches(card))).toList();
    }

    public Match matches(Card playedCard){
        Card mainCard = this.getMainCard();

        if(mainCard == null){
            return Match.NOT;
        }

        if (mainCard.getCardValue().getValue().equals(playedCard.getCardValue().getValue())) {
            return Match.VALUE;

        } else if (mainCard.getCardShape() == playedCard.getCardShape()) {
            return Match.SHAPE;

        } else if (mainCard.getCardShape().getColor().equals(playedCard.getCardShape().getColor())) {
            return Match.COLOR;

        } else {
            return Match.NOT;
        }
    }

}