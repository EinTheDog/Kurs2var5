package pack;

import org.junit.jupiter.api.Test;

import java.io.*;
import com.google.common.io.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackerLauncherTest {

    @Test
    void main() throws IOException {
        String [] args = {"-z","-out", "newFile1.txt", "./src/test/resources/input/file1.txt"};
        PackerLauncher.main(args);
        args = new String[]{"-u","-out", "newFile1.txt", "./output/newFile1.txt.rle"};
        PackerLauncher.main(args);
        File file = new File("./output/newFile1.txt");
        boolean equality = Files.equal(file, new File("./src/test/resources/input/file1.txt"));
        assertTrue(equality);

        args = new String[]{"-z", "./src/test/resources/input/file1.txt"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "./output/file1.txt.rle"};
        PackerLauncher.main(args);
        file = new File("./output/file1.txt");
        equality = Files.equal(file, new File("./src/test/resources/input/file1.txt"));
        assertTrue(equality);

        args = new String[]{"-z", "./src/test/resources/input/file2"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "./output/file2.rle"};
        PackerLauncher.main(args);
        file = new File("./output/file2");
        equality = Files.equal(file, new File("./src/test/resources/input/file2"));
        assertTrue(equality);

        args = new String[]{"-z", "./src/test/resources/input/file3"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "./output/file3.rle"};
        PackerLauncher.main(args);
        file = new File("./output/file3");
        equality = Files.equal(file, new File("./src/test/resources/input/file3"));
        assertTrue(equality);

        args = new String[]{"-z", "./src/test/resources/input/file4.txt"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "./output/file4.txt.rle"};
        PackerLauncher.main(args);
        file = new File("./output/file4.txt");
        equality = Files.equal(file, new File("./src/test/resources/input/file4.txt"));
        assertTrue(equality);

        args = new String[]{"-z", "./src/test/resources/input/file5.txt"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "./output/file5.txt.rle"};
        PackerLauncher.main(args);
        file = new File("./output/file5.txt");
        equality = Files.equal(file, new File("./src/test/resources/input/file5.txt"));
        assertTrue(equality);

    }
}