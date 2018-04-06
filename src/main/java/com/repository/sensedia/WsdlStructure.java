package com.repository.sensedia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.Input;
import javax.wsdl.Message;
import javax.wsdl.WSDLException;
import javax.wsdl.Operation;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.PortType;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;


public class WsdlStructure {
	private String URI;
	private List<Capability> ServiceOperations = new ArrayList<Capability>();
	//private List<String> ServiceXSDs = new ArrayList<String>();

	//Constructor
	public WsdlStructure (String URI) {
		WSDLFactory factory;
		try {
			factory = WSDLFactory.newInstance();
			WSDLReader reader = factory.newWSDLReader();
			reader.setFeature("javax.wsdl.verbose", true);
			Definition wsdlInstance = reader.readWSDL(URI);
			
			Map portTypes = wsdlInstance.getAllPortTypes();
			
			for (Object o : portTypes.keySet()) {
				PortType portTypeInWSDL = (PortType) portTypes.get(o);
				List <Operation> operationList = portTypeInWSDL.getOperations();
				
				for (Operation op : operationList) {
					this.setServiceOperations(op);
				}
			}
			
			
		} catch (WSDLException e) {
			e.printStackTrace();
		}
		this.URI = URI;
	}
	
	// GETs and SETs
	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public List<Capability> getServiceOperations() {
		return ServiceOperations;
	}

	public void setServiceOperations(Operation op) {
		Capability capability = new Capability(op.getName());
		this.ServiceOperations.add(capability);
	
		Input inDef = op.getInput();
		if (inDef != null) {
			Message inMsg = inDef.getMessage();
			if (inMsg != null) {
				Map msgParts = inMsg.getParts();
				
				for (Object p : msgParts.keySet()) {
					Part part = (Part) msgParts.get(p);
					
					capability.setRequests(part.getName() + " | " + part.getTypeName());
				}
					
			}
		}
		
		Output outDef = op.getOutput();
		if (outDef != null) {
			Message outMsg = outDef.getMessage();
			if (outMsg != null) {
				Map msgParts = outMsg.getParts();
				
				for (Object p : msgParts.keySet()) {
					Part part = (Part) msgParts.get(p);
					
					capability.setResponses(part.getName() + " | " + part.getTypeName());
				}
					
			}
		}
	}
	
}
