package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;

public class NullActionFactoryTest extends TestCase {
    public void testCreateNullAction() {
        NullActionFactory nullActionFactory = new NullActionFactory();
        InputStream inputStream = getInputStream();
        Action action = nullActionFactory.create(inputStream);

        Assert.assertTrue(action instanceof NullAction);
    }

    private InputStream getInputStream() {
        byte[] byteArray = new byte[0];

        return new ByteArrayInputStream(byteArray);
    }
}
