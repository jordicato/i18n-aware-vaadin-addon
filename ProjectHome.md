I18NAware internationalization approach centres on automatic propagation of language changes across components and validators.

The basic idea is to define message keys and parameters for caption and values and have the respective caption and values internationalized texts automatically updated.

The helper class “I18NFactory” can be used to instantiate all your internationalizable components and validators. The main purpose of this class is to reduce coupling between your application code and I18NAware:

Example:
```
Window myMainWindow = I18NFactory.newWindow ( “myMainWindow.titleMessageKey” );
```
or
```
Button myButton = I18NFactory.newButton();

I18NFactory.setCaptionKey(myButton, “myButton.captionMessageKey” );

I18NFactory.setCaptionParams(myButton, param );
```

Your Vaadin application must instantiate the I18NAware’s I18NService using an I18NMessageProvider implementation.

The I18NAware’s I18NService is used to register the top level I18NAware elements of your application and to initiate the internationalization of all reachable captions and values.

Example:
```
I18NService i18NService = new I18NServiceImpl( new MessageBundle I18NMessageProvider(“messages”) );

i18NService.registerI18NAware( myMainWindow );

…

i18NService.setLocale( Locale.French );
```