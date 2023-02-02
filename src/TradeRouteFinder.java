import java.util.*;

public class TradeRouteFinder extends Thread {
    private final Province startingProvince;
    public Route bestRoute;

    private HashMap<Province, Route> bestRoutes;
    private LinkedList<ProvinceConnection> currentConnections;

    public TradeRouteFinder(Province startingProvince) {
        this.startingProvince = startingProvince;
    }

    public void run() {
        dijkstra();
    }

    private void addBestRoute(Province fromProvince, Province toProvince, ProvinceConnection pc) {
        Route route;
        if (fromProvince == startingProvince) {
            route = new Route().addConnection(pc);
        }
        else {
            route = new Route(bestRoutes.get(fromProvince)).addConnection(pc);
        }
        if (toProvince != startingProvince) {
            bestRoutes.put(toProvince, route);
            currentConnections.addAll(toProvince.provinceConnections);
        }
    }

    private void replaceBestRoute(Province fromProvince, Province toProvince, ProvinceConnection pc) {
        bestRoutes.replace(toProvince, new Route(bestRoutes.get(fromProvince).addConnection(pc)));
    }

    private void dijkstra() {
        bestRoutes = new HashMap<>();
        currentConnections = new LinkedList<>(startingProvince.provinceConnections);
        while (!currentConnections.isEmpty()) {
            ProvinceConnection pc = currentConnections.pop();
            Province startingProvinceCounterpart = pc.getOtherProvince(startingProvince);
            if (startingProvinceCounterpart != null && ! bestRoutes.containsKey(startingProvinceCounterpart)) {
                addBestRoute(startingProvince, pc.getOtherProvince(startingProvince), pc);
            }

            else if (bestRoutes.containsKey(pc.p1)) {
                if (bestRoutes.containsKey(pc.p2)) {
                    int costDifference = bestRoutes.get(pc.p1).totalCost - bestRoutes.get(pc.p2).totalCost;
                    if (costDifference > pc.travelCost) {
                        replaceBestRoute(pc.p2, pc.p1, pc);
                    }
                    else if (costDifference * -1 > pc.travelCost) {
                        replaceBestRoute(pc.p1, pc.p2, pc);
                    }
                }
                else {
                    addBestRoute(pc.p1, pc.p2, pc);
                }
            }
            else if (bestRoutes.containsKey(pc.p2)) {
                addBestRoute(pc.p2, pc.p1, pc);
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
