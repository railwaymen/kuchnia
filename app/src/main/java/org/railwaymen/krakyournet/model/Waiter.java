package org.railwaymen.krakyournet.model;

import java.util.UUID;

/**
 * Created by tomek on 03.04.16.
 */
public class Waiter {
	private UUID id;
	private String name;

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
}
