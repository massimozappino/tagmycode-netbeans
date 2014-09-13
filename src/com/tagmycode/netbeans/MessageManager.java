package com.tagmycode.netbeans;

import com.tagmycode.plugin.IMessageManager;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

class MessageManager implements IMessageManager {

    @Override
    public void error(String message) {
        NotifyDescriptor.Message d = new NotifyDescriptor.Message(message, NotifyDescriptor.ERROR_MESSAGE);
        DialogDisplayer.getDefault().notify(d);
    }

}
