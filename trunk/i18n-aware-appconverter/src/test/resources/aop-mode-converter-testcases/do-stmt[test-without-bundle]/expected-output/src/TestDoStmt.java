import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestDoStmt {

    private Button button;

    public TestDoStmt() {
        button = new Button("");
        do {
            button.setCaption("TestDoStmt.This_is_the_second_Caption");
        } while (button.getCaption().isEmpty());
    }
}
