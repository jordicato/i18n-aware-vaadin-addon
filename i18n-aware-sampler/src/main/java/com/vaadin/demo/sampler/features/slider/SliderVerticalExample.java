package com.vaadin.demo.sampler.features.slider;

import com.opnworks.vaadin.i18n.ui.I18NVerticalLayout;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Slider;

@SuppressWarnings("serial")
public class SliderVerticalExample extends I18NVerticalLayout {

    public SliderVerticalExample() {
        setSpacing(true);

        final Label value = new Label("0");
        value.setSizeUndefined();

        final Slider slider = new Slider("Select a value between 0 and 100");
        slider.setOrientation(Slider.ORIENTATION_VERTICAL);
        slider.setHeight("200px");
        slider.setMin(0);
        slider.setMax(100);
        slider.setImmediate(true);
        slider.addListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                value.setValue(event.getProperty().getValue());
            }
        });

        addComponent(slider);
        addComponent(value);
        setComponentAlignment(slider, Alignment.TOP_CENTER);
        setComponentAlignment(value, Alignment.TOP_CENTER);

    }

}
