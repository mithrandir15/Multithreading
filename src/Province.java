import java.util.ArrayList;

public class Province {

    String name;
    ArrayList<ProvinceConnection> provinceConnections;
    int numPops;

    public Province(String name, int numPops) {
        this.name = name;
        this.numPops = numPops;
        provinceConnections = new ArrayList<>();
    }

    public void addProvinceConnection(ProvinceConnection p) {
        this.provinceConnections.add(p);
    }
}
