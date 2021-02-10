package org.rpgrunner.helper;

import java.io.IOException;
import java.io.InputStream;

public class Loader {
    private Loader() { }

    public static String extractString(
        final InputStream inputStream
    ) throws IOException {
        int lengthString = inputStream.read();
        byte[] stringBytes = new byte[lengthString];
        inputStream.read(stringBytes);

        return new String(stringBytes);
    }
}
