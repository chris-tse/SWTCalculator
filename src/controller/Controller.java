package controller;

import model.Model;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

/**
 * Created by christophertse on 2017/02/23.
 */
public class Controller extends SelectionAdapter{
    Model m;

    public Controller(Model m) { this.m = m; }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        if (selectionEvent.getSource() instanceof Button) {
            String input = ((Button) selectionEvent.getSource()).getText();

            if(input.equals("Clear")) {
                m.clear();
            } else if (input.equals("Del")) {
                m.del();
            } else if (input.equals("M+")){
                m.memSet();
            } else if(input.equals("MRC")) {
                m.getMem();
            } else if (input.equals("Debug")) {
                m.debug();
            } else if (input.matches("[0-9]|[-+.*^/]")) {
                System.out.println("Matched");
                m.input(input);
            } else if (input.equals("=")) {
                m.eval();
            }

        }
    }
}
