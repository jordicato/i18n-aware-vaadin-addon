package com.vaadin.demo.sampler.features.selects;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.demo.sampler.features.dates.DatePopupInputPrompt;
import com.vaadin.demo.sampler.features.text.TextFieldInputPrompt;
import com.vaadin.ui.ComboBox;

@SuppressWarnings("serial")
public class ComboBoxInputPrompt extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getName() {
        return "com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt.Combobox_with_input_prompt_1";
    }

    @Override
    public String getDescription() {
        return "com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt.ComboBox_is_a_drop_down_select_1" + "com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt.It_can_have_an_i_input_promp_1" + "com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt.the_select_when_no_value_is_s_1" + "com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt.You_can_use_an_input_prompt_i_1" + "com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt.space_but_only_do_so_if_the_1" + "com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt.still_clear_when_a_value_is_s_1" + "com.vaadin.demo.sampler.features.selects.ComboBoxInputPrompt.longer_visible_1";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(ComboBox.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { ComboBoxStartsWith.class, ComboBoxContains.class, ComboBoxNewItems.class, TextFieldInputPrompt.class, DatePopupInputPrompt.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        return new NamedExternalResource[] { new NamedExternalResource("UI Patterns, Input Prompt", "http://ui-patterns.com/pattern/InputPrompt") };
    }
}
