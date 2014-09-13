package com.tagmycode.netbeans;

import com.tagmycode.plugin.AbstractTaskFactory;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.RequestProcessor;
import org.openide.util.TaskListener;

class TaskFactory extends AbstractTaskFactory {

    private ProgressHandle ph = null;
    private RequestProcessor.Task theTask = null;
    private RequestProcessor RequestProcessor;

    @Override
    public void create(Runnable runnable, String title) {
        //The RequestProcessor has to have  allowInterrupt set to true!!
        RequestProcessor = new RequestProcessor(title, 1, true);
        theTask = RequestProcessor.create(runnable);

        ph = ProgressHandleFactory.createHandle(title, theTask);
        theTask.addTaskListener(new TaskListener() {
            @Override
            public void taskFinished(org.openide.util.Task task) {
                ph.finish();
            }
        });
        ph.start();
        theTask.schedule(0);
    }
}
