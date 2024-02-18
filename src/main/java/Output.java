import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Output {
    public void dumpToConcole(StringProcessor info, Parser parser) {
        if (!parser.isShortStatistics() && !parser.isFullStatistics()) {
            return;
        }

        if (info.integersNotEmpty()) {
            System.out.println("Integers:");
            System.out.println("\tNumber of inserted elements: " + info.getIntegers().size());
            if (parser.isFullStatistics()) {
                System.out.println("\tMin of inserted elements: " + info.getIntegersMin());
                System.out.println("\tMax of inserted elements: " + info.getIntegersMax());
                System.out.println("\tAvg of inserted elements: " + info.getIntegersAvg());
                System.out.println("\tSum of inserted elements: " + info.getIntegersSum());
            }
        }
        if (info.floatsNotEmpty()) {
            System.out.println("Floats:");
            System.out.println("\tNumber of inserted elements: " + info.getFloats().size());
            if (parser.isFullStatistics()) {
                System.out.println("\tMin of inserted elements: " + info.getFloatsMin());
                System.out.println("\tMax of inserted elements: " + info.getFloatsMax());
                System.out.println("\tAvg of inserted elements: " + info.getFloatsAvg());
                System.out.println("\tSum of inserted elements: " + info.getFloatsSum());
            }
        }
        if (info.stringsNotEmpty()) {
            System.out.println("Strings:");
            System.out.println("\tNumber of inserted elements: " + info.getStrings().size());
            if (parser.isFullStatistics()) {
                System.out.println("\tShortest of inserted elements: " + info.getStringsShortest());
                System.out.println("\tLongest of inserted elements: " + info.getStringsLongest());
            }
        }
    }
    public void dumpToFiles(StringProcessor info, Parser parser) {
        boolean needToCreateDir = info.integersNotEmpty() || info.floatsNotEmpty() || info.stringsNotEmpty();
        if (parser.isPathProvided() && needToCreateDir) {
            new File(parser.getPath()).mkdirs();
        }

        String delimiter = (parser.isPathProvided() && needToCreateDir) ? "/" : "";
        String prefix = parser.getPath() + delimiter + parser.getPrefix();

        if (info.integersNotEmpty()) {
            File integers = createFile( prefix + "integers.txt");
            writeToFile(integers, parser.isFileAppending(), String.join("\n", info.getIntegers()));
        }
        if (info.floatsNotEmpty()) {
            File floats = createFile(prefix + "floats.txt");
            writeToFile(floats, parser.isFileAppending(), String.join("\n", info.getFloats()));
        }

        if (info.stringsNotEmpty()) {
            File strings = createFile(prefix + "strings.txt");
            writeToFile(strings, parser.isFileAppending(), String.join("\n", info.getStrings()));
        }
    }
    private File createFile(String filename) {
        File file = null;
        try {
            file = new File(filename);
        } catch (NullPointerException e) {
            System.out.println("Error cannot create file " + filename);
            System.exit(1);
        }
        return file;
    }
    private void writeToFile(File file, Boolean append, String in) {
        try (FileWriter fw = new FileWriter(file, append)) {
            fw.write(in);
        } catch (IOException e) {
            System.out.println("Error cannot write to file");
            System.exit(1);
        }
    }
}
