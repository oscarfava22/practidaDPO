package json.io;


import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Utility class used to load and read json data
 */
public class JsonIO {

    /**
     * Given a specific json file you want to read, you should have access to the following:
     *  1. is needed a class in which to put the object, if is an array you should not use an arraylist, after if you
     *  need the data to become an arraylist use: new ArrayList(Arrays.asList([x])) where [x] is what this method returns
     *  2. the class needs to have an empty constructor and setters for all the values you want to load into the class
     *  3. the setters have to have its default name
     *  4. If you want to have a setter with a non default name, use the annotation @JsonSetter("MyParameterName")
     *  5. If your class needs the constructor, you can use the following formatting, note that if you use this, setters
     *  are not needed.
     *      <code>
     *          @JsonCreator
     *          public Constructor(@JsonProperty("Property1") Type1 arg1,...,@JsonProperty("PropertyN") TypeN argN,
     *                  TypeN+1 argN+1, ... , TypeM argM){
     *                      //Code
     *                  }
     *      </code>
     *  For any TypeI you please, it does not have to be in order, but it has to have all the parameters from the file
     * @param clas The class the json file is in
     * @param url the url of the json file
     * @return returns an object, that can be safely casted to  the Class clas, with all the information of the json
     * @throws IOException If there is an error loading the json file
     */
    public static Object readJson(Class clas, String url) throws IOException {
        url = "./"+url;
        byte[] data = Files.readAllBytes(Paths.get(url));
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(data,clas);
    }

    /**
     * Writes a specific json file from object T, if T is not given it defaults to Object and stores it as such.
     * The object to write has to have the following property:
     *  1. It has to have a getter method with the default name for all attributes it wants to write
     *  2. If you want the getter method to not have the default name of the property in the file, it has to have the
     *  annotation @JsonGetter("JsonProperyName")
     * @param toWrite The object to write to file
     * @param url The url of the file
     * @param <T> The type of the object to write
     * @throws IOException If there is an error writing the file
     */
    public static <T> void writeJson(T toWrite, String url) throws IOException {
        url = "./"+url;
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        writer.writeValue(new File(url),toWrite);
    }
}
