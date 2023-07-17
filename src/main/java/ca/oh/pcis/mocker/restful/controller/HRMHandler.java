package ca.oh.pcis.mocker.restful.controller;

import java.io.InputStream;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.oh.pcis.mocker.Utility;

@RestController
public class HRMHandler {

    @PostMapping(path= "/aims/v2", produces = "application/fhir+json")
    public ResponseEntity<String> processPostBundle(@RequestHeader HttpHeaders requestHeaders,  
                                                    @RequestParam Map<String, String> params,
                                                    InputStream dataStream) throws Exception  {
        String fhirPayload = Utility.convert(dataStream);         
        HttpHeaders headers = new HttpHeaders();
        
        for(String key : params.keySet()) {
            String value = params.get(key);
            headers.add("request-param-" + key, value);
        }
        
        for(String key : requestHeaders.keySet()) {
            String value = Utility.list2String(requestHeaders.get(key));
            headers.add("request-httpheader-" + key, value);
        }
        
        ResponseEntity<String> response = new ResponseEntity<String>( fhirPayload, headers, HttpStatus.OK);
        return response;
    }
    
    @PostMapping(path= "/aims/v2/$process-message", produces = "application/fhir+json")
    public ResponseEntity<String> processPostBundle2( @RequestHeader HttpHeaders requestHeaders,  
                                                      @RequestParam Map<String, String> params,
                                                      InputStream dataStream) throws Exception  {
        String fhirPayload = Utility.convert(dataStream);         
        HttpHeaders headers = new HttpHeaders();
        
        for(String key : params.keySet()) {
            String value = params.get(key);
            headers.add("request-param-" + key, value);
        }
        
        for(String key : requestHeaders.keySet()) {
            String value = Utility.list2String(requestHeaders.get(key));
            headers.add("request-httpheader-" + key, value);
        }
        
        ResponseEntity<String> response = new ResponseEntity<String>( fhirPayload, headers, HttpStatus.OK);
        return response;
    }    
    
    @GetMapping(path= "/aims/v2/metadata", produces = "application/fhir+json")
    public ResponseEntity<String> processGetMetadata( @RequestHeader HttpHeaders requestHeaders,
                                                      @RequestParam Map<String, String> params ) throws Exception  {
        
        String fhirPayload = Utility.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestHeaders);        
        HttpHeaders headers = new HttpHeaders();
        for(String key : params.keySet()) {
            String value = params.get(key);
            headers.add("request-param-" + key, value);
        }
        
        for(String key : requestHeaders.keySet()) {
            String value = Utility.list2String(requestHeaders.get(key));
            headers.add("request-httpheader-" + key, value);
        }
        
        ResponseEntity<String> response = new ResponseEntity<String>( fhirPayload, headers, HttpStatus.OK);
        return response;
    }       
 

}
