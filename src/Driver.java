import controller.Controller;
import model.Model;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import view.View;

/**
 * Created by christophertse on 2017/02/23.
 */
public class Driver {
    public static void main(String[] args) {

        Model m = new Model();

        Controller c = new Controller(m);

        new View(c, m);







    }
}
