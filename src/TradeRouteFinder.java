import java.util.*;

public class TradeRouteFinder extends Thread {
    private final Province startingProvince;
    public Route bestRoute;

    private HashMap<Province, Route> bestRoutes;
    private HashSet<Province> seenProvinces;
    private LinkedList<Province> unseenProvinces;

    public TradeRouteFinder(Province startingProvince) {
        this.startingProvince = startingProvince;
    }

    public void run() {
        dijkstra();
    }

    private void addBestRoute(Province fromProvince, Province toProvince) {
        Route route;
        if (fromProvince == startingProvince) {
            route = new Route().addConnection(fromProvince).addConnection(toProvince);
        }
        else {
            route = new Route(bestRoutes.get(fromProvince)).addConnection(toProvince);
        }
        if (toProvince != startingProvince) {
            bestRoutes.put(toProvince, route);
            if (! seenProvinces.contains(toProvince)) {
                unseenProvinces.add(toProvince);
            }
        }
    }

    private void replaceBestRoute(Province fromProvince, Province toProvince) {
        bestRoutes.replace(toProvince, new Route(bestRoutes.get(fromProvince).addConnection(toProvince)));
    }

    private void dijkstra() {
        bestRoutes = new HashMap<>();
        seenProvinces = new HashSet<>();
        seenProvinces.add(startingProvince);
        unseenProvinces = new LinkedList<>(); // "Unseen" already has a best route defined
        unseenProvinces.add(startingProvince);

        while (!unseenProvinces.isEmpty()) {
            Province curr = unseenProvinces.pop();
            seenProvinces.add(curr);
            for (Province connectingProvince : curr.provinceConnections) {
                if (connectingProvince == this.startingProvince) {
                    continue;
                }
                if (bestRoutes.containsKey(connectingProvince)) {
                    int costDifference = bestRoutes.get(connectingProvince).totalCost - bestRoutes.get(curr).totalCost;
                    if (costDifference > 1) {
                        replaceBestRoute(connectingProvince, curr);
                    }
                    else if (costDifference * -1 > 1) {
                        replaceBestRoute(curr, connectingProvince);
                    }
                }
                else {
                    addBestRoute(curr, connectingProvince);
                }
            }
        }
        bestRoute = null;
        for (Route r : bestRoutes.values()) {
            if (bestRoute == null || bestRoute.totalCost > r.totalCost) {
                bestRoute = r;
            }
        }
    }
}
