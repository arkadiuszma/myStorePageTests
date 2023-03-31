package configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Slf4j
@SuppressWarnings("unchecked")
public class PropertyStore {
    public PropertyStore() {
        this.getConfigFileValues();
    }

    private Map<String, Object> readConfigFile() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(new File("src/main/resources/config.yaml"), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void getConfigFileValues() {
        getObjectValues("configuration");
        getObjectValues("user");
            log.debug(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>> Config values loaded");
        }
    private void getObjectValues(String object){
        Map<String, Object> properties = (Map<String, Object>) readConfigFile().get(object);
        if (properties != null) {
            properties.forEach((key, value) -> System.setProperty(key, value.toString()));
        }
    }
}
