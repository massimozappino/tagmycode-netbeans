package com.tagmycode.netbeans;


import com.tagmycode.plugin.Framework;
import com.tagmycode.sdk.model.Snippet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.JTextComponent;
import org.netbeans.modules.editor.NbEditorUtilities;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.EditorCookie;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Edit",
        id = "com.tagmycode.netbeans.CreateSnippetAction"
)
@ActionRegistration(
        iconBase = "com/tagmycode/netbeans/resources/create.png",
        displayName = "#CTL_CreateSnippetAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/Edit", position = 2250),
    @ActionReference(path = "Toolbars/TagMyCode", position = 0),
    @ActionReference(path = "Editors/Popup", position = 4040, separatorBefore = 4035, separatorAfter = 4045),
    @ActionReference(path = "Shortcuts", name = "DOS-T")
})
@Messages("CTL_CreateSnippetAction=Add Snippet on TagMyCode...")
public final class CreateSnippetAction implements ActionListener {

    private final EditorCookie context;
    private final Framework framework;
    private final TagMyCodeTopComponent topcomponent;

    public CreateSnippetAction(EditorCookie context) {
        this.context = context;
        this.topcomponent = TagMyCodeTopComponent.getInstance();
        this.framework = topcomponent.getFramework();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (!framework.canOperate()) {
            return;
        }

        JTextComponent component = context.getOpenedPanes()[0];
        org.openide.loaders.DataObject dataObject = NbEditorUtilities.getDataObject(context.getDocument());
        final String displayName = dataObject.getNodeDelegate().getDisplayName();

        Snippet snippet = new Snippet();
        snippet.setTitle(displayName);
        snippet.setCode(getCode(component));
        final String mimeType = NbEditorUtilities.getMimeType(component);
        framework.showNewSnippetDialog(snippet, mimeType);
    }

    private String getCode(JTextComponent component) {
        String code = component.getSelectedText();
        if (code == null || code.isEmpty()) {
            code = component.getText();
        }
        return code;
    }
}
