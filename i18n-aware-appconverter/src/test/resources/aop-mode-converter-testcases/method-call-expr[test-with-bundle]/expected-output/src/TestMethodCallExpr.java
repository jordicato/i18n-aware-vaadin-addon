import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestTestMethodCallExpr {

    private Button button = new Button("");

    private ComboBox combo = new ComboBox("");

    public TestMethodCallExpr() {
        button.setCaption("Caption");
        combo.addItem("test");
        combo.setItemCaption("test", "this is a test");
    }
}
