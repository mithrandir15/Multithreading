import java.util.LinkedList;

public class Route {
    LinkedList<Province> provinces;
    int totalCost;

    public Route() {
        this.provinces = new LinkedList<>();
        this.totalCost = 0;
    }

    public Route(Route other) {
        this.provinces = new LinkedList<>(other.provinces);
        this.totalCost = other.totalCost;
    }

    public Route addConnection(Province province) {
        provinces.add(province);
        totalCost += 1; //TODO: replace with better heuristic
        return this;
    }
}
