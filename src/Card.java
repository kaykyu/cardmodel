import java.util.ArrayList;
import java.util.List;

public class Card {

    private String suit;
    private String num;

    private static final String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};
    public static String[] getSuits() {return suits;}
    
    private static final String[] nums = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    public static String[] getNums() {return nums;}

    public Card(String suit, String num) {
        this.suit = suit;
        this.num = num;
    }

    public String getSuit() {return suit;}
    public void setSuit(String suit) {this.suit = suit;}

    public String getNum() {return num;}
    public void setNum(String num) {this.num = num;}

    @Override
    public String toString() {
        return String.format("%s of %s", getNum(), getSuit());
    }

}