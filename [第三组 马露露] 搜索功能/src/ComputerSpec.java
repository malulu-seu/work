import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ComputerSpec {


    private Map<String, Object> properties ;

    public ComputerSpec(Map<String, Object> properites) {
        if (properites == null) {
            this.properties = new HashMap<>();
        } else {
            this.properties = new HashMap<>(properites);
        }

    }

    public Object getProperity(String key) {
        return properties.get(key);

    }

    public void setProperites (String key, String value) {
        properties.put(key, value);
    }

    public Map<String, Object> getProperites() {
        return properties;
    }

    public boolean matches (ComputerSpec otherSpec) {
        for(Iterator i = otherSpec.properties.keySet().iterator(); i.hasNext();){
            String propertyName = (String)i.next();
            if(!properties.containsKey(propertyName))
                return false;
            if(!properties.get(propertyName).equals(otherSpec.getProperity(propertyName)))
                return false;
        }
        return true;

    }

    @Override
    public String toString() {
        return " "+properties;
    }

}
