public class Main {
    static Poker poker1;
    static Hand sf, sa, fk, fh, pair, high, tp;

    public static void main(String[] args) {
    poker1 = new Poker();
    sf = new Hand("6C 7C 8C 9C TC");
    sa = new Hand("2D 3D 4D 5D AD");
    fk = new Hand("9D 9H 9S 9C 7D");
    fh = new Hand("TD TC TH 7C 7D");
    pair = new Hand("2C 3C 4D 6D 2D");
    high = new Hand("2D 3C 4D 6D JH");
    tp = new Hand("JD 3C JH AD 3S");
    System.out.println(sf.isStraight());
    }
}