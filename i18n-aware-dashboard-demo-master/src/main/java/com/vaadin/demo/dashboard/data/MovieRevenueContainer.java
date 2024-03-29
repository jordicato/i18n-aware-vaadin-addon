package com.vaadin.demo.dashboard.data;

import java.util.Date;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;

public class MovieRevenueContainer extends IndexedContainer {

    private static final long serialVersionUID = 1L;

    public MovieRevenueContainer() {
        addContainerProperty("timestamp", Date.class, new Date());
        addContainerProperty("Title", String.class, "");
        addContainerProperty("Revenue", Double.class, 0);
    }

    public void add(Date time, String title, double revenue) {
        Object id = addItem();
        Item item = getItem(id);
        if (item != null) {
            item.getItemProperty("timestamp").setValue(time.getTime());
            item.getItemProperty("Title").setValue(title);
            item.getItemProperty("Revenue").setValue(revenue);
        }
    }
}
