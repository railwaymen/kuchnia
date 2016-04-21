package org.railwaymen.krakyournet.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tomek on 04.04.16.
 */
public class Kitchen {

	private UUID id;
	private String name;
	private List<MenuItem> queue = new ArrayList<>();
	private List<MenuItem> menu = new ArrayList<>();

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

    public List<MenuItem> getQueue() {
        return queue;
    }

    public void setQueue(List<MenuItem> queue) {
        this.queue = queue;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }
}
