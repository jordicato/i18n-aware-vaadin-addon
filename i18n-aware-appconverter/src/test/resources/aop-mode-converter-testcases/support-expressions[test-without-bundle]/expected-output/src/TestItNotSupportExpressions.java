import java.util.Date;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class TestItNotSupportExpressions {

    private I18NButton button;

    private I18NButton button1;

    private I18NButton button2;

    private I18NButton button3;

    String stringVar = "button2";

    public TestItNotSupportExpressions() {
        button = new I18NButton("TestItNotSupportExpressions.Caption");
        button.setCaption(new I18NExpression("TestItNotSupportExpressions.Caption_1", button.getCaption()));
        button1 = new I18NButton(new I18NExpression("TestItNotSupportExpressions.Caption_2", button.getCaption()));
        button2 = new I18NButton(new I18NExpression("TestItNotSupportExpressions.This_is_the", stringVar, stringVar, "TestItNotSupportExpressions.my_test"));
        stringVar = "button3";
        button3 = new I18NButton(new I18NExpression("TestItNotSupportExpressions.This_is_the_new", stringVar));
        I18NLabel l = new I18NLabel(new I18NExpression(df.format(((Date) item.getItemProperty("timestamp").getValue())), "TestItNotSupportExpressions.test", item.getItemProperty("City").getValue().toString(), ", ", item.getItemProperty("Country").getValue().toString()));
        I18NButton b = new I18NNativeButton(view.substring(0, 1).toUpperCase() + view.substring(1).replace('-', ' '));
        b.setCaption(new I18NExpression("TestItNotSupportExpressions.Unnamed_Report", (df.format(new Date())), " (", movie.duration, ")"));
        b.setCaption(l.getCaption());
    }
}
