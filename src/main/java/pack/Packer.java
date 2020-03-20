package pack;

import java.io.*;

public class Packer {
    private enum Task {
        packTask, unpackTask;
    }

    Task task;

    public Packer(boolean packTask, boolean unpackTask) {
        if (packTask) task = Task.packTask;
        else {
            if (unpackTask) task = Task.unpackTask;
            else System.err.println("ERROR: Can't define the task. Use -z or -u to set the task");
        }
    }

    public void pack(InputStream in, OutputStream out) {
        try (InputStreamReader reader = new InputStreamReader(in)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(out)) {
                if (task == Task.packTask) {
                    int sym = reader.read();
                    while (sym != -1) {
                        writer.write(sym);
                        sym = reader.read();
                    }
                } else {
                    int sym = reader.read();
                    while (sym != -1) {
                        writer.write(Character.toUpperCase((char) sym));
                        sym = reader.read();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int count () {
        int num = 1;
        while ()
    }

    public void pack(String in, String out) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(in)) {
            try (FileOutputStream outputStream = new FileOutputStream(out)) {
                pack(inputStream, outputStream);
            }
        }
    }
}
