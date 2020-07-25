package com.tagmycode.netbeans;

import com.tagmycode.plugin.IMessageManager;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.NotificationDisplayer;

class MessageManager implements IMessageManager {

    @Override
    public void errorLog(String message) {
        NotificationDisplayer.getDefault().notify("TagMyCode", NotificationIcons.ERROR, message, null);
    }

    @Override
    public void errorDialog(String message) {
        NotifyDescriptor.Message d = new NotifyDescriptor.Message(message, NotifyDescriptor.ERROR_MESSAGE);
        DialogDisplayer.getDefault().notify(d);
    }

}
