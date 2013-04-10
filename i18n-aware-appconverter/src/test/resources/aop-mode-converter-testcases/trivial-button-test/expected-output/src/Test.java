import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class Test {

    private Button button;

    private CssLayout root = new CssLayout();

    public Test() {
        button = new Button("Test.The_Button_n_caption");
        root.addComponent(new HorizontalLayout() {

            Button boton = new Button("Test.The_Button_caption1");
        });
    }
}
