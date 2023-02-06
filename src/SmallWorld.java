public class SmallWorld {
    Province[] provinces;

    public SmallWorld(Province[] provinces) {
        this.provinces = provinces;
    }

    public void tick() {
        //Province chosenOne = provinces[(int)(Math.random() * provinces.length)];
        for (Province chosenOne : provinces) {
            System.out.println(chosenOne.name);
            if (chosenOne.name.equals(("Boston"))) {


                TradeRouteFinder t = new TradeRouteFinder(chosenOne);
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
