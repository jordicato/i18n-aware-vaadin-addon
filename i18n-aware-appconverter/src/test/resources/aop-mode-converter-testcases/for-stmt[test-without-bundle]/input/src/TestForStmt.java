import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestForStmt {

    private Button button;

    public TestForStmt() {    	
    	button = new Button("First Caption");    	
    	for (int i = 0; i < 1; i++) {
    		button.setCaption("This is the second Caption");    		
		}    	
    }
}
