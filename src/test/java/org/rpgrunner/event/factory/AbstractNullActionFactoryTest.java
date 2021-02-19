package org.rpgrunner.event.factory;

import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.NullAction;

public abstract class AbstractNullActionFactoryTest extends TestCase {
    public void testCreateNullAction() {
        InputStream inputStream = getInputStream();
        Action action = createAction(inputStream);

        Assert.assertTrue(action instanceof NullAction);
    }

    private InputStream getInputStream() {
        byte[] byteArray = new byte[0];

        return generateInputStream(byteArray);
    }

    protected abstract InputStream generateInputStream(byte[] byteArray);

    protected abstract Action createAction(InputStream inputStream);
}
