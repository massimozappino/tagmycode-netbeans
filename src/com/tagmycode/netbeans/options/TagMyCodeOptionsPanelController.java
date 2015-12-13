package com.tagmycode.netbeans.options;

import com.tagmycode.netbeans.TagMyCodeTopComponent;
import com.tagmycode.plugin.gui.SettingsForm;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

@OptionsPanelController.SubRegistration(
        displayName = "#AdvancedOption_DisplayName_TagMyCode",
        keywords = "#AdvancedOption_Keywords_TagMyCode",
        keywordsCategory = "Advanced/TagMyCode"
)
@org.openide.util.NbBundle.Messages({"AdvancedOption_DisplayName_TagMyCode=TagMyCode", "AdvancedOption_Keywords_TagMyCode=keywords"})
public final class TagMyCodeOptionsPanelController extends OptionsPanelController {

    private JPanel panel;
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private boolean changed;

    @Override
    public void update() {
        changed = false;
    }

    @Override
    public void applyChanges() {
        changed = false;
    }

    @Override
    public void cancel() {
        // need not do anything special, if no changes have been persisted yet
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public boolean isChanged() {
        return changed;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null; // new HelpCtx("...ID") if you have a help set
    }

    @Override
    public JComponent getComponent(Lookup masterLookup) {
        return getPanel();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    @Override
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    private JPanel getPanel() {
        if (panel == null) {
            panel = new SettingsForm(TagMyCodeTopComponent.getInstance().getFramework()).getMainPanel();
        }
        return panel;
    }

    void changed() {
        if (!changed) {
            changed = true;
            pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
        }
        pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
    }

    }
