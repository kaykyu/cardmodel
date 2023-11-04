// Value object = object which attributes cannot be changed = record
public class Card {

    private String suit;
    private String num;
    private int value;

    private static final String[] suits = {"Spades", "Hearts", "Clubs", "Diamonds"};
    public static String[] getSuits() {return suits;}
    
    private static final String[] nums = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    public static String[] getNums() {return nums;}

    public Card(String suit, String num) {
        this.suit = suit;
        this.num = num;
        if (num.equals("Jack") || num.equals("Queen") || num.equals("King")) {
            this.value = 10;
        } else if (num.equals("Ace")) {
            this.value = 1;
        } else {
            this.value = Integer.parseInt(num);
        }
    }

    public String getSuit() {return suit;}

    public String getNum() {return num;}

    public int getValue() {return value;}

    @Override
    public String toString() {
        return String.format("%s of %s", getNum(), getSuit());
    }

}