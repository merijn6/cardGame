import java.util.List;

public class Pesten {
    private Deck deck;
    private boolean playsClockwise = true;

    Pesten(){
        this.deck = new Deck(4, 7, 2);
        this.deck.playCardFromMain();

        System.out.println(this.deck.getMainCard().getName());

//        List<Card> playableCards = this.deck.playableCards(1);
//        for(Card card : playableCards){
//            System.out.println(card.getName());
//        }
    }
}
