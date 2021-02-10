package org.rpgrunner.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.RandomGenerator;

public class LoaderTest extends TestCase {
    public void testExtractCorrectString() throws IOException {
        String string = RandomGenerator.getRandomString();
        InputStream inputStream = getInputStreamFromString(string);
        String stringExtracted = Loader.extractString(inputStream);
        Assert.assertEquals(string, stringExtracted);
    }

    private InputStream getInputStreamFromString(final String string) {
        int stringLength = getStringLength(string);
        byte[] arrayStream = new byte[stringLength + 1];
        byte[] byteFileName = string.getBytes();

        arrayStream[0] = (byte) stringLength;

        for (int i = 0; i < stringLength; i++) {
            arrayStream[i + 1] = byteFileName[i];
        }

        return new ByteArrayInputStream(arrayStream);
    }

    private int getStringLength(final String string) {
        return string.getBytes().length;
    }
}
