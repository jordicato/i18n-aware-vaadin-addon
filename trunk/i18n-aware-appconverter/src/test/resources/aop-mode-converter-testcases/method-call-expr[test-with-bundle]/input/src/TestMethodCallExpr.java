import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestTestMethodCallExpr {

    private Button button = new Button("");

    private ComboBox combo = new ComboBox("");

    public TestMethodCallExpr() {
        button.setCaption("TestMethodCallExpr.Caption");
        combo.addItem("test");
        combo.setItemCaption("test", I18NStaticService.getI18NServive().getMessage("TestMethodCallExpr.this_is_a_test"));
    }
}
