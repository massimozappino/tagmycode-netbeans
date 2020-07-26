package com.tagmycode.netbeans;

import javax.swing.Icon;
import org.netbeans.api.annotations.common.StaticResource;
import org.openide.util.ImageUtilities;

public abstract class NotificationIcons {

    public static final Icon ERROR;
    public static final Icon WARNING;
    public static final Icon INFO;

    @StaticResource
    private static final String ERROR_IMAGE = "notifications/errorIcon.png";
    @StaticResource
    private static final String WARNING_IMAGE = "notifications/warningIcon.png";
    @StaticResource
    private static final String INFO_IMAGE = "notifications/infoIcon.png";

    static {
        ERROR = ImageUtilities.image2Icon(ImageUtilities.loadImage(ERROR_IMAGE));
        WARNING = ImageUtilities.image2Icon(ImageUtilities.loadImage(WARNING_IMAGE));
        INFO = ImageUtilities.image2Icon(ImageUtilities.loadImage(INFO_IMAGE));
    }

    private NotificationIcons() {
    }
}
