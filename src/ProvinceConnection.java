public class ProvinceConnection {
    Province p1;
    Province p2;
    int travelCost;

    public ProvinceConnection(Province p1, Province p2, int travelCost) {
        this.p1 = p1;
        this.p2 = p2;
        this.travelCost = travelCost;
    }

    // Returns the other province in the connection, or NULL if the given province is not in this connection.
    // == is appropriate since there should only be one copy of each province in memory.
    public Province getOtherProvince(Province p) {
        if (p == p1) {
            return p2;
        }
        else if (p == p2) {
            return p1;
        }
        else {
            return null;
        }
    }
}
