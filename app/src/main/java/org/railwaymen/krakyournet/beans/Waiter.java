package org.railwaymen.krakyournet.beans;

/**
 * Created by tomek on 03.04.16.
 */
public class Waiter {
	private String id;
	private String name;

	public Waiter(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
