package pack;

import org.junit.jupiter.api.Test;

import java.io.*;
import com.google.common.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PackerLauncherTest {

    @Test
    void main() throws IOException {
        String [] args={"-z","-out", "file2.txt", "..\\..\\src\\test\\resources\\file1.txt"};
        File file2 = new File("./output/file2.txt");
        Files.equal(file2, )
    }
}