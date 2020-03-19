package pack;

import java.io.*;

public class Packer {
    private String task;

    public Packer(String task) {
        this.task = task;
    }

    public void pack(InputStream in, OutputStream out) throws IOException {
        try (InputStreamReader reader = new InputStreamReader(in)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
                char sym = (char) reader.read();
                while (sym != -1) {
                    writer.write(sym);
                    sym = (char) reader.read();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pack(String in, String out) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(in)) {
            try (FileOutputStream outputStream = new FileOutputStream(out)) {
                pack(inputStream, outputStream);
            }
        }
    }
}
