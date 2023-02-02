public class SmallWorld {
    Province[] provinces;

    public SmallWorld(Province[] provinces) {
        this.provinces = provinces;
    }

    public void tick() {
        Province chosenOne = provinces[(int)(Math.random() * provinces.length)];
        TradeRouteFinder t = new TradeRouteFinder(chosenOne);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
