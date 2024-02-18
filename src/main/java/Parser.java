import org.apache.commons.cli.*;
import java.util.List;

public class Parser {
    private Options options;
    private String path = "";
    private boolean hasPath;
    private String prefix = "";
    private boolean addToFile;
    private boolean shortStatistics;
    private boolean fullStatistics;
    private List<String> inputFiles;

    public Parser() {
        options = getOptions();
    }
    public void parse(String[] args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            hasPath = cmd.hasOption("o");
            if (hasPath) {
                path = cmd.getOptionValue("o");
            }
            boolean hasPrefix = cmd.hasOption("p");
            if (hasPrefix) {
                prefix = cmd.getOptionValue("p");
            }
            addToFile = cmd.hasOption("a");
            shortStatistics = cmd.hasOption("s");
            fullStatistics = cmd.hasOption("f");
            inputFiles = cmd.getArgList();
            if (inputFiles.isEmpty()) {
                throw new Exception("No input files");
            }

        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        validate();
    }
    private void validate() {
        if (shortStatistics && fullStatistics) {
            System.out.println("Error cant choose both type of statistics");
            shortStatistics = false;
            fullStatistics = false;
        }
    }
    boolean isShortStatistics() {
        return shortStatistics;
    }
    boolean isFullStatistics() {
        return fullStatistics;
    }
    boolean isFileAppending() {
        return addToFile;
    }
    public boolean isPathProvided() {
        return hasPath;
    }
    public String getPath() {
        return path;
    }
    public String getPrefix() {
        return prefix;
    }
    public List<String> getInputFiles() {
        return inputFiles;
    }
    private Options getOptions() {
        options = new Options();

        Option output = new Option("o", true, "output path");
        output.setRequired(false);
        options.addOption(output);

        Option filePrefix = new Option("p", true, "file prefix");
        filePrefix.setRequired(false);
        options.addOption(filePrefix);

        Option addition = new Option("a", false, "addition to file");
        addition.setRequired(false);
        options.addOption(addition);

        Option shortStat = new Option("s", false, "short statistics");
        shortStat.setRequired(false);
        options.addOption(shortStat);

        Option fullStat = new Option("f", false, "full statistics");
        fullStat.setRequired(false);
        options.addOption(fullStat);
        return options;
    }
}
