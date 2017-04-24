package com.tagmycode.netbeans;

import com.tagmycode.plugin.IMessageManager;
import org.openide.awt.NotificationDisplayer;

class MessageManager implements IMessageManager {

    @Override
    public void error(final String message) {
        NotificationDisplayer.getDefault().notify("TagMyCode", NotificationIcons.ERROR, message, null);
    }

}
