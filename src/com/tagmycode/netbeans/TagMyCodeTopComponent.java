package com.tagmycode.netbeans;


import com.tagmycode.plugin.Framework;
import com.tagmycode.plugin.FrameworkConfig;
import com.tagmycode.sdk.authentication.TagMyCodeApiProduction;
import com.tagmycode.sdk.exception.TagMyCodeException;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.IOException;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ConvertAsProperties(
        dtd = "-//com.tagmycode.netbeans//TagMyCode//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "TagMyCodeTopComponent",
        iconBase = "com/tagmycode/netbeans/resources/tagmycode.png",
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "output", openAtStartup = true)
@ActionID(category = "Window", id = "com.tagmycode.netbeans.TagMyCodeTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_TagMyCodeAction",
        preferredID = "TagMyCodeTopComponent"
)
@Messages({
    "CTL_TagMyCodeAction=TagMyCode",
    "CTL_TagMyCodeTopComponent=TagMyCode",
    "HINT_TagMyCodeTopComponent=This is a TagMyCode window"
})
public final class TagMyCodeTopComponent extends TopComponent {

    private Framework framework;

    public TagMyCodeTopComponent() {
        initTagMyCode();
        initComponents();
        add(framework.getMainWindow().getMainComponent(), BorderLayout.CENTER);

        revalidate();
        repaint();

        setName(Bundle.CTL_TagMyCodeTopComponent());
        setToolTipText(Bundle.HINT_TagMyCodeTopComponent());
    }

    private void initTagMyCode() {
        try {
            final FrameworkConfig frameworkConfig = new FrameworkConfig(new PasswordKeyChain(), new PreferencesManager(), new MessageManager(), new TaskFactory(), getMainFrame());
            framework = new Framework(new TagMyCodeApiProduction(), frameworkConfig, new Secret());
            framework.start();
        } catch (IOException | TagMyCodeException e) {
            throw new RuntimeException(e);
        }
    }

    public Framework getFramework() {
        if (framework == null) {
            initTagMyCode();
        }
        return framework;
    }

    public static TagMyCodeTopComponent getInstance() {
        TopComponent comp = WindowManager.getDefault()
                .findTopComponent("TagMyCodeTopComponent");
        return TagMyCodeTopComponent.class.cast(comp);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    public Frame getMainFrame() {
        return WindowManager.getDefault().getMainWindow();
    }
}
