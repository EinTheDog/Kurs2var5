package pack;

import org.junit.jupiter.api.Test;

import java.io.*;
import com.google.common.io.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackerLauncherTest {

    @Test
    void standardTests() throws IOException {
        char sep = File.separatorChar;
        String [] args = {"-z","-out", "newFile1.txt",
                "." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file1.txt"};
        PackerLauncher.main(args);
        args = new String[]{"-u","-out", "newFile1.txt", "." + sep + "output" + sep + "newFile1.txt.rle"};
        PackerLauncher.main(args);
        File file = new File("." + sep + "output" + sep + "newFile1.txt");
        boolean equality = Files.equal(file,
                new File("." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file1.txt"));
        assertTrue(equality);

        args = new String[]{"-z", "." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file1.txt"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "." + sep + "output" + sep + "file1.txt.rle"};
        PackerLauncher.main(args);
        file = new File("." + sep + "output" + sep + "file1.txt");
        equality = Files.equal(file,
                new File("." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file1.txt"));
        assertTrue(equality);

        args = new String[]{"-z", "." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file2"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "." + sep + "output" + sep + "file2.rle"};
        PackerLauncher.main(args);
        file = new File("." + sep + "output" + sep + "file2");
        equality = Files.equal(file,
                new File("." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file2"));
        assertTrue(equality);

        args = new String[]{"-z", "." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file3"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "." + sep + "output" + sep + "file3.rle"};
        PackerLauncher.main(args);
        file = new File("." + sep + "output" + sep + "file3");
        equality = Files.equal(file,
                new File("." + sep + "src" + sep + "test" +sep + "resources" + sep + "input" + sep + "file3"));
        assertTrue(equality);

        args = new String[]{"-z", "." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file4.txt"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "." + sep + "output" + sep + "file4.txt.rle"};
        PackerLauncher.main(args);
        file = new File("." + sep + "output" + sep + "file4.txt");
        equality = Files.equal(file,
                new File("." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file4.txt"));
        assertTrue(equality);

        args = new String[]{"-z", "." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file5.txt"};
        PackerLauncher.main(args);
        args = new String[]{"-u", "." + sep + "output" + sep + "file5.txt.rle"};
        PackerLauncher.main(args);
        file = new File("." + sep + "output" + sep + "file5.txt");
        equality = Files.equal(file,
                new File("." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file5.txt"));
        assertTrue(equality);
    }

    @Test
    void exceptionTests() {
        char sep = File.separatorChar;

        String [] args = new String[]{"-u", "." + sep + "file0.txt.rle"};
        String[] finalArgs2 = args;
        assertThrows(IOException.class, () -> PackerLauncher.main(finalArgs2));

        args =new  String[]{"-out",
                "newFile1.txt", "." + sep + "src" + sep + "test" + sep + "resources" + sep + "input" + sep + "file1.txt"};
        String[] finalArgs = args;
        assertThrows(IllegalArgumentException.class, () -> PackerLauncher.main(finalArgs));

        args = new String[]{"-z", "-out", "newFile1.txt"};
        String[] finalArgs1 = args;
        assertThrows(IllegalArgumentException.class, () -> PackerLauncher.main(finalArgs1));


    }

}