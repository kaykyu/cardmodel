import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardModel {

    private static final List<Card> deck = new ArrayList<>();
    public static Console cons = System.console();

    public static void main(String[] args) {
        
        for (String s : Card.getSuits()) {
            for (String n : Card.getNums()) {
                deck.add(new Card(s, n));
            }    
        }

        String input = "";
        int i = 0;
        Collections.shuffle(deck);

        System.out.println("Playing Cards - draw, shuffle, quit");
        input = cons.readLine("> ");

        while (!input.trim().equals("quit")) {
            if (input.trim().equals("draw")) {
                System.out.println(deck.get(i).toString());
                if (i > 52) {
                    i = 0;
                    Collections.shuffle(deck);

                }
                i++;
                input = cons.readLine("> ");
            } else if (input.trim().equals("shuffle")) {
                System.out.println("Shuffling");
                System.out.println("Shuffled");
                Collections.shuffle(deck);
                i = 0;
                input = cons.readLine("> ");
            } else {
                System.out.println("Invalid command");
                input = cons.readLine("> ");
            }
        }
        System.out.println("Thank you for playing");
    }
}