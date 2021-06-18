package org.rpgrunner.j2me.graphics;

public class MessageDialog {
    private final String message;

    public MessageDialog(final String messageToShow) {
        message = messageToShow;
    }

    public String getMessage() {
        return message;
    }
}
