import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestIfStmt {

    private Button button;

    public TestIfStmt() {
    	
    	button = new Button("");
    	
    	if (button.getCaption().isEmpty()) {
    		
    		button.setCaption("First Caption");
    		
    	}
    	
    }
}
