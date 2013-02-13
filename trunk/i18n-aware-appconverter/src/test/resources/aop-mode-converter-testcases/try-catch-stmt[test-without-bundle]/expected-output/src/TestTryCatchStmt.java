import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestTryCatchStmt {

    private Button button;

    public TestTryCatchStmt() {
        try {
            button = new Button("TestTryCatchStmt.Try_Caption");
        } catch (Exception e) {
            button = new Button("TestTryCatchStmt.catch_Caption");
        }
    }
}
