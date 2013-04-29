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

    String stringVar = I18NCountLiterals.registerLiteral("button2", "TestItNotSupportExpressions.button2");

    public TestItNotSupportExpressions() {
        button = new Button("TestItNotSupportExpressions.Caption");
        button1 = new Button(I18NCountLiterals.registerBinaryExpression("TestItNotSupportExpressions.Caption_1", button.getCaption()));
        button2 = new Button(I18NCountLiterals.registerBinaryExpression("TestItNotSupportExpressions.This_is_the", stringVar, stringVar, "TestItNotSupportExpressions.my_test"));
        stringVar = I18NCountLiterals.registerLiteral("button3", "TestItNotSupportExpressions.button3");
        button3 = new Button(I18NCountLiterals.registerBinaryExpression("TestItNotSupportExpressions.This_is_the_new", stringVar));
        Label l = new Label(I18NCountLiterals.registerBinaryExpression(df.format(((Date) item.getItemProperty("timestamp").getValue())), "TestItNotSupportExpressions.test", item.getItemProperty("City").getValue().toString(), ", ", item.getItemProperty("Country").getValue().toString()));
        Button b = new NativeButton(view.substring(0, 1).toUpperCase() + view.substring(1).replace('-', ' '));
        b.setCaption(I18NCountLiterals.registerBinaryExpression("TestItNotSupportExpressions.Unnamed_Report", (df.format(new Date())), " (", movie.duration, ")"));
    }
}
