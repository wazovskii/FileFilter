import java.util.ArrayList;
import java.util.List;

public class StringProcessor {
    private List<String> integers;
    private List<String> floats;
    private List<String> strings;
    private long intMin;
    private long intMax;
    private long intSum;
    private float floatMin;
    private float floatMax;
    private float floatSum;
    private float intAvg;
    private float floatAvg;
    private int shortestStr;
    private int longestStr;

    StringProcessor() {
        integers = new ArrayList<String>();
        floats = new ArrayList<String>();
        strings = new ArrayList<String>();
    }
    public void process(List<String> input) {
        for (var it : input) {
            if (hasDot(it)) {
                try {
                    float tmp = Float.parseFloat(it);
                    if (floats.isEmpty()) {
                        floatMax = tmp;
                        floatMin = tmp;
                    }
                    floats.add(it);
                    floatSum += tmp;
                    floatMax = Math.max(tmp, floatMax);
                    floatMin = Math.min(tmp, floatMin);
                } catch (NumberFormatException ex) {
                    if (strings.isEmpty()) {
                        shortestStr = it.length();
                        longestStr = it.length();
                    }
                    strings.add(it);
                    shortestStr = Math.min(it.length(), shortestStr);
                    longestStr = Math.max(it.length(), longestStr);

                }
            } else {
                try {
                    long tmp = Long.parseLong(it);
                    if (integers.isEmpty()) {
                        intMax = tmp;
                        intMin = tmp;
                    }
                    integers.add(it);
                    intSum += tmp;
                    intMax = Math.max(tmp, intMax);
                    intMin = Math.min(tmp, intMin);

                } catch (NumberFormatException ex) {
                    if (strings.isEmpty()) {
                        shortestStr = it.length();
                        longestStr = it.length();
                    }
                    strings.add(it);
                    shortestStr = Math.min(it.length(), shortestStr);
                    longestStr = Math.max(it.length(), longestStr);
                }
            }
        }
        intAvg = intSum;
        floatAvg = floatSum;
        if (integersNotEmpty()) {
            intAvg /= integers.size();
        }
        if (floatsNotEmpty()) {
            floatAvg /= floats.size();
        }
    }
    public List<String> getIntegers() {
        return integers;
    }
    public List<String> getFloats() {
        return floats;
    }
    public List<String> getStrings() {
        return strings;
    }

    public Long getIntegersMin() {
        return intMin;
    }
    public Long getIntegersMax() {
        return intMax;
    }
    public float getIntegersAvg() {
        return intAvg;
    }
    public Long getIntegersSum() {
        return intSum;
    }
    public float getFloatsMin() {
        return floatMin;
    }

    public float getFloatsMax() {
        return floatMax;
    }

    public float getFloatsAvg() {
        return floatAvg;
    }

    public float getFloatsSum() {
        return floatSum;
    }

    public int getStringsShortest() {
        return shortestStr;
    }

    public int getStringsLongest() {
        return longestStr;
    }
    public Boolean integersNotEmpty() {
        return !integers.isEmpty();
    }

    public Boolean floatsNotEmpty() {
        return !floats.isEmpty();
    }
    public Boolean stringsNotEmpty() {
        return !strings.isEmpty();
    }
    private static boolean hasDot(String str) {
        return str != null && str.matches(".*\\..*");
    }
}
