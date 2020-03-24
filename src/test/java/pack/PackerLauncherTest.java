package pack;

import org.junit.jupiter.api.Test;

import java.io.*;
import com.google.common.io.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackerLauncherTest {

    @Test
    void main() throws IOException {
        String [] args = {"-z","-out", "file2.txt", "./src/test/resources/input/file1.txt"};
        PackerLauncher.main(args);

        /*args = new String[]{"-z", "./src/test/resources/input/file1.txt"};
        PackerLauncher.main(args);
        file = new File("./output/file1.txt");
        equality = Files.equal(file, new File("./src/test/resources/answers/answer1.txt"));
        assertTrue(equality);*/

        args = new String[]{"-u","-out", "file3.txt", "./src/output/file2.txt"};
        PackerLauncher.main(args);
        File file = new File("./output/unpack.txt");
        boolean equality = Files.equal(file, new File("./src/test/resources/input/file1.txt"));
        assertTrue(equality);
    }
}