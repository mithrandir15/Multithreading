import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;


public class Simulator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("src/ProvinceInput.csv"));
        sc.useDelimiter("\n");
        String[] provinceArgs;
        LinkedList<Province> provinces = new LinkedList<>();
        HashMap<String, Integer> provinceNameToIndex = new HashMap<>();
        int i = 0;
        while (sc.hasNext()) {
            provinceArgs = sc.next().split(",");
            String provinceName = provinceArgs[0].strip();
            int numPops = Integer.parseInt(provinceArgs[1].strip());
            provinces.add(new Province(provinceName, numPops));
            provinceNameToIndex.put(provinceName, i);
            i += 1;
        }
        Province[] provinceArr = new Province[provinces.size()];
        provinceArr = provinces.toArray(provinceArr);

        sc.close();

        sc = new Scanner(new File("src/ProvinceConnectionInput.csv"));
        sc.useDelimiter("\n");
        String[] provinceConnectionArgs;
        while (sc.hasNext()) {
            provinceConnectionArgs = sc.next().split(",");
            String p1name = provinceConnectionArgs[0].strip();
            int p1index = provinceNameToIndex.get(p1name);
            String p2name = provinceConnectionArgs[1].strip();
            int p2index = provinceNameToIndex.get(p2name);
            provinceArr[p1index].addProvinceConnection(provinceArr[p2index]);
            provinceArr[p2index].addProvinceConnection(provinceArr[p1index]);
        }

        SmallWorld smallWorld = new SmallWorld(provinceArr);
        smallWorld.tick();
    }
}
