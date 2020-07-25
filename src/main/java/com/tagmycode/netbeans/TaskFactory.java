package com.tagmycode.netbeans;

import com.tagmycode.plugin.AbstractTaskFactory;
import org.netbeans.api.progress.ProgressHandle;
import org.openide.util.RequestProcessor;
import org.openide.util.Task;

class TaskFactory extends AbstractTaskFactory {

    @Override
    public void create(final Runnable runnable, final String title) {
        final Thread thread = new Thread(runnable);
        RequestProcessor requestProcessor = new RequestProcessor(title, 1, true);

        final ProgressHandle ph = ProgressHandle.createHandle(title, () -> {
            thread.interrupt();
            return true;
        });
        ph.start();

        RequestProcessor.Task task = requestProcessor.create(() -> {
            thread.start();
            while (thread.isAlive()) {
            }
        });

        task.addTaskListener((Task task1) -> {
            ph.finish();
        });
        task.schedule(0);
    }
}
