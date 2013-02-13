import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestSwitchStmt {

    private Button button;

    private int key;

    public TestSwitchStmt() {
        switch(key) {
            case 1:
                button = new Button("TestSwitchStmt.Caption_1");
                break;
            case 2:
                button = new Button("TestSwitchStmt.Caption_2");
                break;
            default:
                button = new Button("TestSwitchStmt.Caption");
                break;
        }
    }
}
