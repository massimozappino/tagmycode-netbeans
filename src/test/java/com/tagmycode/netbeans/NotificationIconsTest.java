package com.tagmycode.netbeans;

import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class NotificationIconsTest {

    @Test
    public void testIcons() throws URISyntaxException {
        assertNotNull(NotificationIcons.ERROR);
    }

}
