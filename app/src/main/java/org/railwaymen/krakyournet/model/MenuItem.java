package org.railwaymen.krakyournet.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by tomek on 03.04.16.
 */
public class MenuItem {

	private UUID id;
	private String name;
	private boolean selected;
	private Date takeDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Date getTakeDate() {
        return takeDate;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }
}
