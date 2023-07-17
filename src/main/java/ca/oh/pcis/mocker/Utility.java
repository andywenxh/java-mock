package ca.oh.pcis.mocker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;


public class Utility {

    public final static ObjectMapper mapper = JsonMapper.builder().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true).build();

    public static String convert(InputStream inputStream) throws IOException {
        
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {  
            while ((line = bufferedReader.readLine()) != null) {            
                stringBuilder.append(line.trim());
            }
        }
     
        return stringBuilder.toString();
    }
    
    
    public static String list2String(@SuppressWarnings("rawtypes") List list) {
        String value = "";        
        for(Object obj : list) {
            value = value + "," + obj.toString();
        }        
        value = value.substring(1);
        return value;
    }
    
}
