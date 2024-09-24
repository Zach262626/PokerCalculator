import java.util.*;

/**
 * A poker hand is a list of cards, which can be of some "kind" (pair, straight, etc.)
 *
 */
public class Hand implements Comparable<Hand> {

    public enum Kind {HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT,
        FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH}

    private final List<Card> cards;

    /**
     * Create a hand from a string containing all cards (e,g, "5C TD AH QS 2D")
     */
    public Hand(String c) {
        cards = new ArrayList<>();
        for (String i: c.split(" ")) {
            cards.add(new Card(i));
        }
    }
    /**
     * Returns the sorted rank of this hand
     */
    protected List<Card.Rank> getRanks() {
        List<Card.Rank> ranks = new ArrayList<Card.Rank>();
        for (Card c: cards) {
            ranks.add(c.getRank());
        }
        Collections.sort(ranks);
        return ranks;
    }

    /**
     * @returns true if the hand has n cards of the same rank
     * e.g., "TD TC TH 7C 7D" returns True for n=2 and n=3, and False for n=1 and n=4
     */
    protected boolean hasNKind(int n) {
        List<Card.Rank> ranks = getRanks();
        for (Card c: cards) {
            if (Collections.frequency(ranks, c.getRank()) == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * Optional: you may skip this one. If so, just make it return False
     * @returns true if the hand has two pairs
     */
    public boolean isTwoPair() {
        return false;
    }

    /**
     * @returns true if the hand is a straight
     */
    public boolean isStraight() {
        List<Card.Rank> ranks = getRanks();
        List<Card.Rank> allRanks = new ArrayList<Card.Rank>();

        if (ranks.equals((new Hand("AC 2C 3C 4C 5C")).getRanks())) return true;

        int lastOrdinal = ranks.getFirst().ordinal();
        for (int i = 1; i < ranks.size(); i++) {
            if (!(ranks.get(i).ordinal()-1 == lastOrdinal)) {
                return false;
            }else {
                lastOrdinal = ranks.get(i).ordinal();
            }
        }
        return true;
    }

    /**
     * @returns true if the hand is a flush
     */
    public boolean isFlush() {
        Card.Suit suit = cards.getFirst().getSuit();
        for (Card c: cards) {
            if (!(c.getSuit() == suit)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int compareTo(Hand h) {
        //hint: delegate!
        //and don't worry about breaking ties
        return this.kind().compareTo(h.kind());
    }

    /**
     * This method is already implemented and could be useful!
     * @returns the "kind" of the hand: flush, full house, etc.
     */
    public Kind kind() {
        if (isStraight() && isFlush()) return Kind.STRAIGHT_FLUSH;
        else if (hasNKind(4)) return Kind.FOUR_OF_A_KIND;
        else if (hasNKind(3) && hasNKind(2)) return Kind.FULL_HOUSE;
        else if (isFlush()) return Kind.FLUSH;
        else if (isStraight()) return Kind.STRAIGHT;
        else if (hasNKind(3)) return Kind.THREE_OF_A_KIND;
        else if (isTwoPair()) return Kind.TWO_PAIR;
        else if (hasNKind(2)) return Kind.PAIR;
        else return Kind.HIGH_CARD;
    }

}
