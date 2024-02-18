
public class App {
    public static void main(String[] args) {
        Parser commandlineParser = new Parser();
        commandlineParser.parse(args);

        FileReader reader = new FileReader();
        reader.read(commandlineParser.getInputFiles());

        StringProcessor processor = new StringProcessor();
        processor.process(reader.getFilesContents());

        Output out = new Output();
        out.dumpToFiles(processor, commandlineParser);
        out.dumpToConcole(processor, commandlineParser);
    }
}
