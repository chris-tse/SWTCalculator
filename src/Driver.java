import controller.Controller;
import model.Model;
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
