import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Pesten {
    private Deck deck;
    private boolean playsClockwise = true;
    private final Integer playerIndex = 1;
    public Integer hasToDraw = 0;
    public Boolean hasToSkip = false;

    Pesten(){
        this.deck = new Deck(4, 7, 2);
        this.deck.playCardFromMain();

        System.out.println(this.deck.getMainCard().getName());

        List<Card> playableCards = this.deck.playableCards(1);
    }

    public void playerTurn(){
        Scanner scanner = new Scanner(System.in);

        this.deck.showHand(this.playerIndex);
        System.out.println("What card do you want to play?");
    }

    public void AttemptToPlayCard(Integer cardIndex){
        Card card = this.deck.getHand(this.playerIndex).get(cardIndex);
        Match match = this.deck.matches(card);
        Scanner scanner = new Scanner(System.in);

        if(match != Match.NOT && match != Match.COLOR){
            switch (card.getCardValue().getSymbol()){
                case "J":
                    scanner
                    break;

                case "A":
                    this.playsClockwise = !this.playsClockwise;
                    break;

                case "2":
                    this.hasToDraw += 2;
                    break;

                case "7":
                    break;

                case "8":
                    this.hasToSkip = true;
                    break;

                case "Jester":
                    this.hasToDraw += 5;
                    break;
            }
        }else{
            if(this.hasToDraw != 0 && pestCard.contains(CardValue.ACE)){

            }
        }

    }


    // 2 is the next player draws 2 extra cards
    // 7 is the player gets to play once again
    // 8 the next player cant play again
    // Jester the next player has to draw 5 but can choose the symbol
    // Ace player rotates the direction the game gets played in, if there are only 2 players he gets to play again
    // Jack choose a symbol of choice
}
