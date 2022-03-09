package com.tuana9a.learn.java.servlet.utils;

import com.tuana9a.learn.java.servlet.config.AppConfig;

import java.io.*;

public class IoUtils {
    private static final IoUtils instance = new IoUtils();

    private IoUtils() {

    }

    public static IoUtils getInstance() {
        return instance;
    }

    //SECTION: general purpose
    public void writeFileToOutput(RandomAccessFile input, OutputStream output, long start, long length) throws IOException {
        AppConfig config = AppConfig.getInstance();
        byte[] buffer = new byte[config.BUFFER_SIZE];
        int read; //da doc duoc bao nhieu

        if (input.length() == length) {
            //full file
            while ((read = input.read(buffer)) > 0) {
                output.write(buffer, 0, read);
            }
        } else {
            //partial file
            input.seek(start);
            long toRead = length;
            while ((read = input.read(buffer)) > 0) {
                if ((toRead -= read) > 0) {
                    output.write(buffer, 0, read);
                } else {
                    //EXPLAIN: toRead now is negative, this code is CORRECT =)
                    output.write(buffer, 0, (int) toRead + read);
                    break;
                }
            }
        }
    }

    public void writeFileToOutput(RandomAccessFile input, OutputStream output) throws IOException {
        writeFileToOutput(input, output, 0, input.length());
    }

    public void writeInputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, read);
        }
    }

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ignored) {
            }
        }
    }

}
