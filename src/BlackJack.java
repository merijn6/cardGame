import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    private final int dealerHandIndex;
    private final int playerHandIndex;

    BlackJack() {
        this.dealerHandIndex = 0;
        this.playerHandIndex = 1;
    }

    public void play() {
        Deck deck = new Deck(2, 1, 0);
        int playerPoints = this.playerDraws(deck);
        int dealerPoints = this.dealerDraws(deck);

        this.decideWin(playerPoints, dealerPoints);
    }

    private int playerDraws(Deck deck) {
        int maxPoints = 21;
        int handIndex = this.playerHandIndex;
        boolean isPlaying = true;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Card> hand = deck.getHand(handIndex);

        while (isPlaying) {
            deck.showHand(handIndex);
            System.out.println("Hit or stay: ");
            String action = scanner.next();

            switch (action) {
                case "hit":
                    deck.drawCards(handIndex, 1);
                    int playersPoints = this.totalPoints(hand);

                    if (playersPoints > maxPoints || playersPoints == maxPoints) {
                        deck.showHand(handIndex);
                        isPlaying = false;
                        break;
                    }
                    break;

                case "stay":
                    isPlaying = false;
                    break;

                default:
                    System.out.println("Non valid input found pls try again.");
                    break;
            }
        }
        return this.totalPoints(hand);
    }

    private int dealerDraws(Deck deck) {
        int dealerLimit = 17;
        int handIndex = this.dealerHandIndex;
        boolean isDrawing = true;
        int dealerPoints = 0;
        ArrayList<Card> hand = deck.getHand(handIndex);

        while (isDrawing) {
            dealerPoints = this.totalPoints(hand);

            if (dealerPoints < dealerLimit) {
                deck.drawCards(handIndex, 1);
            } else {
                isDrawing = false;
            }
        }
        deck.showHand(handIndex);
        return dealerPoints;
    }

    private void decideWin(int playerPoints, int dealerPoints) {
        int maxPoints = 21;
        String result;

        if (playerPoints > maxPoints) {
            result = "You've lost. Better luck next time.";
        } else if (playerPoints == maxPoints) {
            result = "Congrats, you won. Maybe you should go to the casino tonight:)";
        } else if (dealerPoints > maxPoints) {
            result = "You won!!!!";
        } else if (dealerPoints == maxPoints) {
            result = "The dealer got lucky T-T.";
        } else if (playerPoints > dealerPoints) {
            result = "You won!!!";
        } else {
            result = "You lost.";
        }

        System.out.println(result);
    }

    private Integer totalPoints(ArrayList<Card> hand) {
        int points = 0;
        int maxPoints = 21;
        ArrayList<Card> aces = new ArrayList<>();

        for (Card card : hand) {
            if (card.getCardValue() == CardValue.ACE) {
                aces.add(card);
            } else {
                points += card.getCardValue().getValue();
            }
        }

        if (aces.isEmpty()) {
            return points;
        }

        int acesSize = aces.size();
        int aceMaxSize = 10;
        int aceMinSize = 1;
        for (Card ace : aces) {
            if (points + aceMaxSize + acesSize - 1 <= maxPoints) { // The -1 serves the purpose of making the size equivelent
                points += aceMaxSize;
            } else {
                points += aceMinSize;
            }
        }
        return points;
    }
}
