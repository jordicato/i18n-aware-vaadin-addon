import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestItNotSupportExpressions {

    private Button button;

    private Button button1;

    private Button button2;

    public TestItNotSupportExpressions() {
        button = new Button("TestItNotSupportExpressions.Caption");
        button1 = new Button("Caption" + button.getCaption());
        String stringVar = "button2";
        button2 = new Button("This is the " + stringVar);
    }
}
