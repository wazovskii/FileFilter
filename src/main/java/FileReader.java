import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private final List<String> filesContents;
    public FileReader() {
        filesContents = new ArrayList<String>();
    }
    public void read(List<String> files) {
        for (var file: files) {
            try {
                Scanner scanner = new Scanner(new File(file));
                scanner.useDelimiter("\n");

                while (scanner.hasNext()) {
                    filesContents.add(scanner.next());
                }
                scanner.close();
            } catch (IOException e) {
                System.out.println("Error reading file");
            }
        }
    }
    public List<String> getFilesContents() {
        return filesContents;
    }
}
