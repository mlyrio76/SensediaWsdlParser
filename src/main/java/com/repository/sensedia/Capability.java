package com.repository.sensedia;

import java.util.ArrayList;
import java.util.List;



public class Capability {
	private String capabilityName;
	private List<String> requests = new ArrayList<String>();
	private List<String> responses = new ArrayList<String>();
	
	public Capability (String capabilityName) {
		this.setCapabilityName(capabilityName);
	}

	public String getCapabilityName() {
		return capabilityName;
	}

	public void setCapabilityName(String capabilityName) {
		this.capabilityName = capabilityName;
		
	}

	public List<String> getRequests() {
		return requests;
	}

	public void setRequests(String string) {
		this.requests.add(string);
	}

	public List<String> getResponses() {
		return responses;
	}

	public void setResponses(String responses) {
		this.responses.add(responses);
	}

}
