import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestStmtInsideStmt {

    private Button button;

    public TestStmtInsideStmt() {
        button = new Button("");
        try {
            do {
                for (int i = 0; i < 1; i++) {
                    if (button.getCaption().isEmpty()) {
                        button.setCaption("TestStmtInsideStmt.This_is_the_Caption");
                    }
                }
            } while (button.getCaption().isEmpty());
        } catch (Exception e) {
        }
    }
}
