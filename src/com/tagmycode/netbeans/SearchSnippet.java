package com.tagmycode.netbeans;

import com.tagmycode.plugin.Framework;
import com.tagmycode.plugin.gui.IDocumentInsertText;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.cookies.EditorCookie;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Edit",
        id = "com.tagmycode.netbeans.SearchSnippet"
)
@ActionRegistration(
        iconBase = "com/tagmycode/netbeans/resources/search.png",
        displayName = "#CTL_SearchSnippet"
)

@ActionReferences({
    @ActionReference(path = "Menu/Edit", position = 2251),
    @ActionReference(path = "Editors/Popup", position = 4041, separatorBefore = 4035, separatorAfter = 4045),
    @ActionReference(path = "Shortcuts", name = "DOS-SPACE")
})

@Messages("CTL_SearchSnippet=Search snippets...")
public final class SearchSnippet implements ActionListener {

    private final EditorCookie context;
    private final Framework framework;
    private final TagMyCodeTopComponent topcomponent;

    public SearchSnippet(EditorCookie context) {
        this.context = context;
        this.topcomponent = TagMyCodeTopComponent.getInstance();
        this.framework = topcomponent.getFramework();
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (!framework.canOperate()) {
            return;
        }

        final JTextComponent component = context.getOpenedPanes()[0];

        final IDocumentInsertText iDocumentInsertText = new IDocumentInsertText() {

            @Override
            public void insertText(String text) {
                try {
                    NetBeansUtilities.insert(text, component);
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        };

        framework.showSearchDialog(iDocumentInsertText);
    }

}
