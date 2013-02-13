import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestWhileStmt {

    private Button button;

    public TestWhileStmt() {
        button = new Button("");
        while (button.getCaption().isEmpty()) {
            button.setCaption("TestWhileStmt.This_is_the_second_Caption");
        }
    }
}
