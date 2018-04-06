package com.repository.sensedia;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ServiceSpecs {
	
	@RequestMapping("/wsdlParser")
    public WsdlStructure wsdlParser(@RequestParam(name="URI", required=true) String URI) {
		
		WsdlStructure WsdlStructure = new WsdlStructure(URI);
		
		return WsdlStructure;
	}
    
}
