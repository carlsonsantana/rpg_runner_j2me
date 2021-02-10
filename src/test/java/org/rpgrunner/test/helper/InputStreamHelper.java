package org.rpgrunner.test.helper;

public class InputStreamHelper {
    private InputStreamHelper() { }

    public static byte[] getByteArray(final String string) {
        int arrayLength = getStringLength(string);
        byte[] byteArray = new byte[arrayLength + 1];
        setByteArray(byteArray, string);

        return byteArray;
    }

    public static void setByteArray(
        final byte[] byteArray,
        final String string
    ) {
        byte[] byteArrayStringRaw = string.getBytes();
        int stringArrayLength = getStringLength(string);

        byteArray[0] = (byte) stringArrayLength;

        for (int i = 0; i < stringArrayLength; i++) {
            byteArray[i + 1] = byteArrayStringRaw[i];
        }
    }

    public static int getStringLength(final String string) {
        return string.getBytes().length;
    }
}
