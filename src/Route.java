import java.util.LinkedList;

public class Route {
    LinkedList<ProvinceConnection> connections;
    int totalCost;

    public Route() {
        this.connections = new LinkedList<>();
        this.totalCost = 0;
    }

    public Route(Route other) {
        this.connections = new LinkedList<>(other.connections);
        this.totalCost = other.totalCost;
    }

    public Route addConnection(ProvinceConnection provinceConnection) {
        connections.add(provinceConnection);
        totalCost += provinceConnection.travelCost;
        return this;
    }
}
