package com.tagmycode.netbeans;

import com.tagmycode.plugin.AbstractTaskFactory;
import com.tagmycode.plugin.operation.TagMyCodeAsynchronousOperation;
import org.netbeans.api.progress.ProgressHandle;
import org.openide.util.Cancellable;
import org.openide.util.RequestProcessor;
import org.openide.util.Task;
import org.openide.util.TaskListener;

class TaskFactory extends AbstractTaskFactory {

    @Override
    public void create(final TagMyCodeAsynchronousOperation operation, final Runnable runnable, final String title) {
        final Thread thread = new Thread(runnable);
        RequestProcessor requestProcessor = new RequestProcessor(title, 1, true);

        final ProgressHandle ph = ProgressHandle.createHandle(title, new Cancellable() {
            @Override
            public boolean cancel() {
                thread.interrupt();
                return true;
            }
        });
        ph.start();

        RequestProcessor.Task task = requestProcessor.create(new Runnable() {
            @Override
            public void run() {
                thread.start();
                while (thread.isAlive()) {
                }
            }
        });

        task.addTaskListener(new TaskListener() {
            @Override
            public void taskFinished(Task task) {
                ph.finish();
            }
        });
        task.schedule(0);
    }
}
