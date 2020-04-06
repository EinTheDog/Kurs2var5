package pack;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Packer {
    private byte unique, same, cur, next;
    private StringBuilder sbUnique;

    /**
     * метод для закодированной записи непрерывной последовательности уникальных символов
     * @param writer
     * @throws IOException
     */
    private void writeUniq (OutputStream writer) throws IOException {
        try {
            writer.write(unique);
            for (char symb: sbUnique.toString().toCharArray()) writer.write(symb);
            unique = 0;
            sbUnique = new StringBuilder();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * метод для закодированной записи непрерывной последовательности повторяющихся символов
     * @param writer
     * @throws IOException
     */
    private void writeSame (OutputStream writer) throws IOException {
        try {
            writer.write(same);
            writer.write(cur);
            same = 1;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * метод для запаковки файла
     * @param in путь для файла, из которго мы читаем данные
     * @param out путь для файла, в который мы записываем данные
     * @throws IOException
     */
    public void pack(Path in, Path out) throws IOException {
        //если имя выходного файла не введено - генерируем его самостоятельно
        if (out == null) out = in.getFileName();
        try (InputStream input = new FileInputStream(in.toFile())) {
            String directoryName = "."+ File.separatorChar + "output";
            File directory = new File(directoryName);
            if (! directory.exists()) directory.mkdir();
            try (OutputStream output = new FileOutputStream(directoryName + File.separatorChar + out.toString() + ".rle")) {
                unique = 0; // кол-во уникальных символов, идущих подряд
                sbUnique = new StringBuilder();
                same = 1; // кол-во одинаковых символов, идущих подряд
                //считываем 2 байта
                cur = (byte) input.read();
                next = (byte) input.read();
                //продолжаем считывать байты, пока не в читаемом файле не кончатся символы
                while (cur != -1) {
                    // не даем занять числу больше 1 байта при записи
                    if (unique == -128) writeUniq(output);
                    if (same == 127) writeSame(output);
                    // если два символа не совпадают, то выписываем сколько одинаковых символов мы насчитали и сбрасываем счетчик
                    // если счетчик и так равен 1, то у нас подряд идут 2 неодинаковых символа, а значит пора считать
                    // кол-во уникальных символов, идущих подряд
                    if (cur != next) {
                        if (same > 1) writeSame(output);
                        else {
                            unique--;
                            sbUnique.append((char) cur);
                        }
                    } else {
                        //если подряд идут 2 одинаковых символа, то выписывем кол-во уникальных символов, идущих подряд
                        // и сбрасывем их счетчик
                        // начинаем считать кол-во одинаковых символов подряд
                        if (unique < 0) writeUniq(output);
                        same++;
                    }
                    cur = next;
                    next = (byte) input.read();
                }
                // выписываем кол-во уникальных или повторяющихся символов, которые мы успели начсчиать, пока у нас
                // не кончились символы в файле
                if (unique < 0) writeUniq(output);
                if (same > 1) writeSame(output);
            }
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * метод для распаковки файла
     * @param in путь для файла, из которго мы читаем данные
     * @param out путь для файла, в который мы записываем данные
     * @throws IOException
     */
    public void unpack(Path in, Path out) throws IOException {
        //если имя выходного файла не введено - генерируем его самостоятельно
        String name = in.getFileName().toString();
        if (out == null) out = Paths.get(name.substring(0, name.length() - 3));
        try (InputStream input = new FileInputStream(in.toFile())) {
            try (OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream("."+ File.separatorChar + "output" + File.separatorChar + out.toString()))) {
                int same = 0;
                int unique = 0;
                //читаем 2 байта, 1-ый - кол-во символов (если <0 - уникальных, если >0 - повторяющихся), 2-ой - символ
                byte num = (byte) input.read();
                byte sym = (byte) input.read();
                while (num != -1) {
                    //проаверяем: кол-во каких символов мы узнали
                    if (num < 0) {
                        unique = -num;
                    } else {
                        same = num;
                    }
                    //печатаем символы
                    for (int i = 0; i < same; i++) writer.write(sym);
                    for (int i = 0; i < unique; i++) {
                        writer.write((char) sym);
                        sym = (byte) input.read();
                    }
                    //повторяем пока не дочитаем файл до конца
                    if (same > 0) sym = (byte) input.read();
                    num = sym;
                    sym = (byte) input.read();
                    same = 0;
                    unique = 0;
                }
            }
        } catch (IOException e) {
            throw e;
        }
    }



}
