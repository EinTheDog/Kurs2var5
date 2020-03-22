package pack;

import java.io.*;

public class Packer {
    private enum Task {
        packTask, unpackTask
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
                    int unique = 0;
                    StringBuilder sbUnique = new StringBuilder();
                    int same = 1;
                    int cur = reader.read();
                    int next = reader.read();
                    while (cur != -1) {
                        if (cur != next) {
                            if (same > 1) {
                                writer.write(String.valueOf(same));
                                writer.write(cur);
                                same = 1;
                            } else {
                                unique++;
                                sbUnique.append((char) cur);
                            }
                        } else {
                            if (unique > 0) {
                                writer.write('-' + String.valueOf(unique));
                                writer.write(sbUnique.toString());
                                unique = 0;
                                sbUnique = new StringBuilder();
                            }
                            same++;
                        }
                        cur = next;
                        next = reader.read();
                    }
                    if (unique > 0) {
                        writer.write('-' + String.valueOf(unique));
                        writer.write(sbUnique.toString());
                    }
                    if (same > 1) {
                        writer.write(String.valueOf(same));
                        writer.write(cur);
                    }
                } else {
                    int same = 0;
                    int unique = 0;
                    int cur = reader.read();
                    int next = reader.read();
                    while (cur != -1) {
                        if (cur == '-') {
                            if (next == -1) break;
                            cur = next;
                            next = reader.read();
                            unique = cur - 48;
                        } else {
                            same = cur - 48;
                        }
                        while (48 <= next && next <= 57) {
                            if (same > 0) same = same * 10 + next - 48;
                            else  unique = unique * 10 + next - 48;
                            next = reader.read();
                        }
                        for (int i = 0; i < same; i++) writer.write(next);
                        for (int i = 0; i < unique; i++) {
                            writer.write(next);
                            next = reader.read();
                        }
                        if (same > 0) next = reader.read();
                        cur = next;
                        next = reader.read();
                        same = 0;
                        unique = 0;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pack(String in, String out) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(in)) {
            try (FileOutputStream outputStream = new FileOutputStream("../../output/"+ out)) {
                pack(inputStream, outputStream);
            }
        }
    }
}
