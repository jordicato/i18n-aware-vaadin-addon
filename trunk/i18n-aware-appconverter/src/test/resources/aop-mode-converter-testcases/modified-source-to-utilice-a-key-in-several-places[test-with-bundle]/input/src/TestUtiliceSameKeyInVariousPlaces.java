import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestUtiliceSameKeyInVariousPlaces {

	private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;

    public TestUtiliceSameKeyInVariousPlaces() {
    	button1 = new Button("TestModifiedAKey.This_is_the_caption2");
        button2 = new Button("TestModifiedAKey.This_is_the_caption2");
        button3 = new Button("Text for this button");
        button4 = new Button("New text");
        button5 = new Button("Other_Caption");
    }
}
