package pack;

import org.junit.jupiter.api.Test;

import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PackerLauncherTest {

    @Test
    void main() throws IOException {
        PackerLauncher.main(new String[] {"-z -out file2.txt ../../../test/resources/file1.txt"});
        BufferedReader reader = new BufferedReader(new FileReader("../../../output/file.txt"));
        String s = reader.readLine();
        assertEquals("2a-4dzdz3b-1f2c", s);
    }
}