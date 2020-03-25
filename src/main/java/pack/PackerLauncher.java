package pack;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.IOException;
import java.nio.file.Path;

public class PackerLauncher {

    @Option(name = "-z", metaVar = "zip", required = false,  usage = "Type of task", forbids = {"-u"})
    private boolean packTask;

    @Option(name = "-u", metaVar = "unzip", required = false,  usage = "Type of task", forbids = {"-z"})
    private boolean unpackTask;

    @Option(name = "-out", metaVar = "OutputName", required = false, usage = "Output file name")
    private Path outputName;

    @Argument(metaVar = "InputFileName", required = true, usage = "Input file name")
    private Path inputName;

    public static void main (String [] args) throws IOException {
        new PackerLauncher().launch(args);
    }


    private void launch (String [] args) throws IOException {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar packer-1.0.jar -z|-u -out OutputName InputName");
            parser.printUsage(System.err);
            throw new IllegalArgumentException();
        }
        Packer packer = new Packer();
        try {
            if (packTask) packer.pack(inputName, outputName);
            else {
                if (unpackTask) packer.unpack(inputName, outputName);
                else {
                    System.err.println("ERROR: Can't define the task. Use -z or -u to set the task");
                    throw new IllegalArgumentException();
                }
            }
            System.out.println("SUCCESS");
        } catch (IOException e) {
            System.err.println("ERROR: " + e.getMessage() + System.lineSeparator());
            throw e;
        }
    }
}
