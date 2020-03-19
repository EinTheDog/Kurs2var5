package pack;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.IOException;

public class PackerLauncher {

    @Argument(metaVar = "TaskName", required = true, usage = "Type of task")
    private String task;

    @Option(name = "-out", metaVar = "OutputName", required = true, usage = "Output file name")
    private String outputName;

    @Argument(metaVar = "InputFileName", index = 1, required = true, usage = "Input file name")
    private String inputName;


    private void launch (String [] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Kurs2Var5.jar -z|-u -out OutputName InputName");
            parser.printUsage(System.err);
            return;
        }

        Packer packer = new Packer(task);
        try {
            packer.pack(inputName, outputName);
        } catch (IOException e) {
            
        }
    }
}
