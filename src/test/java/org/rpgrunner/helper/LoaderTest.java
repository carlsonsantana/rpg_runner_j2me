package org.rpgrunner.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.test.helper.InputStreamHelper;
import org.rpgrunner.test.helper.RandomGenerator;

public class LoaderTest extends TestCase {
    public void testExtractCorrectString() throws IOException {
        String string = RandomGenerator.getRandomString();
        InputStream inputStream = getInputStreamFromString(string);
        String stringExtracted = Loader.extractString(inputStream);
        Assert.assertEquals(string, stringExtracted);
    }

    private InputStream getInputStreamFromString(final String string) {
        byte[] byteArray = InputStreamHelper.getByteArray(string);

        return new ByteArrayInputStream(byteArray);
    }
}
