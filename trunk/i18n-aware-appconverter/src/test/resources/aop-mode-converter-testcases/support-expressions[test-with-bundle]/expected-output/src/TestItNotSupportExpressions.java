import java.util.Date;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestItNotSupportExpressions {

    private Button button;

    private Button button1;

    private Button button2;

    private Button button3;

    String stringVar = "button2";

    public TestItNotSupportExpressions() {
        button = new Button("Caption");
        button1 = new Button("Caption" + button.getCaption());
        button2 = new Button("This is the " + stringVar + stringVar + "my test");
        stringVar = "button3";
        button3 = new Button("This is the new " + stringVar);
        Label l = new Label(df.format(((Date) item.getItemProperty("timestamp").getValue())) + "test" + item.getItemProperty("City").getValue().toString() + ", " + item.getItemProperty("Country").getValue().toString());
        Button b = new NativeButton(view.substring(0, 1).toUpperCase() + view.substring(1).replace('-', ' '));
        b.setCaption("Unnamed Report " + " (" + draftCount + ")");
    }
}
