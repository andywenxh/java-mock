package ca.oh.pcis.mocker.restful;

import java.util.HashMap;
import java.util.Map;

public class Consts {
    
    public static final Map<String, String> pathMap = new HashMap<String, String>();
    
    static {     
        pathMap.put("/aims/v2", "post");
        pathMap.put("/aims/v2/$process-message", "post");
        pathMap.put("/aims/v2/metadata", "get");        
    }
}
