    import java.util.Scanner;


public class Pesten {
    private Deck deck;
    private boolean playsClockwise = true;
    private final Integer playerIndex = 1;

    public CardShape changedCardShape;
    public Integer hasToDraw = 0;
    public Boolean hasToSkip = false;

    Pesten(){
        this.deck = new Deck(4, 7, 2);
        this.deck.playCardFromMain();

        System.out.println(this.deck.getMainCard().getName());
    }

    public void playerTurn(){
        Scanner scanner = new Scanner(System.in);

        this.deck.showHand(this.playerIndex);
        System.out.println("What card do you want to play?");
    }

    // This function is meant for the player only
    public void attemptToPlayCard(Integer cardIndex){
        Card card = this.deck.getHand(this.playerIndex).get(cardIndex);
        Match match = this.deck.matches(card);


        if(match != Match.NOT && match != Match.COLOR){
            this.deck.playCard(this.deck.getHand(this.playerIndex), cardIndex);

            switch (card.getCardValue().getSymbol()){
                case "J":
                    this.setNewShape();
                    break;

                case "A":
                    this.playsClockwise = !this.playsClockwise;
                    break;

                case "2":
                    this.hasToDraw += 2;
                    break;

                case "7":
                    this.attemptToPlayCard(cardIndex);
                    break;

                case "8":
                    this.hasToSkip = true;
                    break;

                case "Jester":
                    this.hasToDraw += 5;
                    break;
            }
//        }else{
//            }
        }
    }

    public void setNewShape(){
        System.out.println("What shape do you want to change into? Spades, diamonds, hearths or clovers.");
        Scanner scanner = new Scanner(System.in);
        String newShape = scanner.next();

        switch (newShape){
            case "spades":
                this.changedCardShape = CardShape.SPADE;
                break;

            case "diamonds":
                this.changedCardShape = CardShape.DIAMOND;
                break;

            case "hearts":
                this.changedCardShape = CardShape.HEART;
                break;

            case "clovers":
                this.changedCardShape = CardShape.CLOVER;
                break;

            default:
                System.out.println("Non valid shape given. Please try again.");
                this.setNewShape();
                break;
        }
    }

}

    // 2 is the next player draws 2 extra cards
    // 7 is the player gets to play once again
    // 8 the next player cant play again
    // Jester the next player has to draw 5 but can choose the symbol
    // Ace player rotates the direction the game gets played in, if there are only 2 players he gets to play again
    // Jack choose a symbol of choice

