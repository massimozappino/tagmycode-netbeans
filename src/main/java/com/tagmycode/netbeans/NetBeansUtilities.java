package com.tagmycode.netbeans;

import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;

// https://java.net/projects/nbwicketsupport/sources/nbwicketsupport/content/WicketSuite/WicketCore/src/org/netbeans/modules/web/wicket/palette/Utilities.java?rev=60
public class NetBeansUtilities {

    public static void insert(final String s, final JTextComponent target) throws BadLocationException {
        final StyledDocument doc = (StyledDocument) target.getDocument();
        if (doc == null) {
            return;
        }

        class InsertFormatedText implements Runnable {

            @Override
            public void run() {
                try {
                    insertFormated(s, target, doc);
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
        InsertFormatedText insert = new InsertFormatedText();
        NbDocument.runAtomicAsUser(doc, insert);
    }

    private static int insertFormated(String s, JTextComponent target, Document doc) throws BadLocationException {
        int start = -1;
        try {
            Caret caret = target.getCaret();
            int p0 = Math.min(caret.getDot(), caret.getMark());
            int p1 = Math.max(caret.getDot(), caret.getMark());
            doc.remove(p0, p1 - p0);
            start = caret.getDot();
            doc.insertString(start, s, null);
        } catch (BadLocationException ble) {
            Exceptions.printStackTrace(ble);
        }
        return start;
    }
    
}
