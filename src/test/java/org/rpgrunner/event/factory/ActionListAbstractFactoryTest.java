package org.rpgrunner.event.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.rpgrunner.event.action.Action;
import org.rpgrunner.event.action.ActionList;
import org.rpgrunner.test.helper.HelperActionAbstractFactory;

public class ActionListAbstractFactoryTest extends TestCase {
    private static final byte ACTION_LIST_FACTORY = (byte) 1;
    private ActionAbstractFactory actionAbstractFactory;

    public ActionListAbstractFactoryTest() {
        actionAbstractFactory = (
            HelperActionAbstractFactory.createActionAbstractFactory()
        );
    }

    public void testActionListFactory() throws IOException {
        byte[] byteArray = new byte[] {ACTION_LIST_FACTORY, 0};
        InputStream inputStream = new ByteArrayInputStream(byteArray);

        Action action = actionAbstractFactory.create(inputStream);

        Assert.assertTrue(action instanceof ActionList);
    }
}
