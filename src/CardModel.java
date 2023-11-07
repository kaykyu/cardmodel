import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardModel {

    private static final List<Card> deck = new ArrayList<>();
    public static Console cons = System.console();
    private static int count = 0;
    private static String input = "";
    private static List<Card> dealer = new ArrayList<>();
    private static List<Card> player = new ArrayList<>();
    private static boolean gameOver;

    public static boolean getRandomBoolean(float probability) {
        double randomValue = Math.random()*100;  //0.0 to 99.9
        return randomValue <= probability;
    }

    public static void blackjack() {  
        Collections.shuffle(deck); 
        gameOver = false;
        dealer.add(draw());
        player.add(draw());
        dealer.add(draw());
        player.add(draw());
        System.out.printf("You drew %s and %s\n", player.get(0).toString(), player.get(1).toString());
    }

    public static Integer playerHandValue() {
        int sum = 0;
        for (Card cards : player) {
            sum += cards.getValue();
        }

        if (sum < 12 && containsAce(player)) {
            sum += 10;
        }
        return sum;
    }

    public static Integer dealerHandValue() {
        int sum = 0;
        for (Card cards : dealer) {
            sum += cards.getValue();
        }
        
        if (sum < 12 && containsAce(dealer)) {
            sum += 10;
        }
        return sum;
    }

    private static boolean notBust() {
        if (playerHandValue() <= 21) {
            return true;
        }
        return false;
    }

    public static boolean containsAce(List<Card> hand) {
        for (Card cards : hand) {
            if (cards.getNum().equals("Ace")) {
                return true;
            }
        }
        return false;
    }

    public static Card draw() {
        Card card = deck.get(count);
            if (count > 52) {
                count = 0;
                System.out.println("Out of cards, reshuffling");
                Collections.shuffle(deck);
                System.out.println("Reshuffled");
            }
        count++;
        return card;
    }

    public static void main(String[] args) {
        
        for (String s : Card.getSuits()) {
            for (String n : Card.getNums()) {
                deck.add(new Card(s, n));
            }    
        }

        Collections.shuffle(deck);

        System.out.println("Playing Cards - draw, shuffle, blackjack, quit");
        input = cons.readLine("> ");

        while (!input.trim().equals("quit")) {

            if (input.trim().equals("draw")) {
                System.out.printf("You drew %s\n", draw().toString());
                input = cons.readLine("> ");

            } else if (input.trim().equals("shuffle")) {
                System.out.println("Shuffling");
                System.out.println("Shuffled");
                Collections.shuffle(deck);
                count = 0;
                input = cons.readLine("> ");

            } else if (input.trim().equals("blackjack")) {
                                
                player.clear();
                dealer.clear();
                blackjack();                    

                while (!gameOver) {

                    if (player.size() == 2 && playerHandValue() == 21) {
                    System.out.println("Blackjack!");
                    gameOver = true;

                    } else if (notBust()) {
                        input = cons.readLine("hit or stay > ");

                        if (input.trim().equals("hit")) {
                            player.add(draw());
                            System.out.printf("You drew %s\n", player.getLast());

                        } else if (input.trim().equals("stay")) {
                            if (playerHandValue() < 16) {
                                System.out.println("Hand value less than 16, please draw");
                                break;
                            }
                            
                            while (dealerHandValue() < 16 ) {
                                dealer.add(draw());
                            }

                            if (dealerHandValue() == 16) {
                                if (getRandomBoolean(42.0f)) {
                                    dealer.add(draw());
                                }

                            } else if (dealerHandValue() == 17) {
                                if (getRandomBoolean(33.0f)) {
                                    dealer.add(draw());
                                }
                            } else if (dealerHandValue() == 18) {
                                if (getRandomBoolean(25.0f)) {
                                    dealer.add(draw());
                                }
                            }

                            System.out.println("Dealer has ");
                            for (int i = 0; i < dealer.size(); i++) {
                                System.out.println(dealer.get(i).toString());
                            }

                            if (dealerHandValue() > 21) {
                                System.out.println("Dealer bust, you win!");
                            } else if (playerHandValue().compareTo(dealerHandValue()) < 0) {
                                System.out.println("You lost");
                            } else if (playerHandValue().compareTo(dealerHandValue()) == 0) {
                                System.out.println("Tie");
                            } else {
                                System.out.println("You win!");
                                }

                            gameOver = true;
                        } else {
                            input = cons.readLine("Invalid command\n> ");
                        }

                    } else {
                    System.out.println("Bust!");
                    gameOver = true;
                    }
                }
                input = cons.readLine("> ");
            } else {
                System.out.println("Invalid command");
                input = cons.readLine("> ");
            }
        }
        System.out.println("Thank you for playing");
    }
}
