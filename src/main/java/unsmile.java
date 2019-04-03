import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class unsmile {
    public static void main(String[] args) {
        byte[] encoded;
        try {
            encoded = Files.readAllBytes(Paths.get(args[0]));
        }catch (IOException e) {
            System.out.println("Error reading smile file!");
            return;
        }
        ObjectMapper smileMapper = new ObjectMapper(new SmileFactory());

        JsonNode node;
        try {
            node = smileMapper.readTree(encoded);
        }catch (IOException e) {
            return;
        }

        ObjectMapper jsonMapper = new ObjectMapper();
        String json;
        try {
            json = jsonMapper.writeValueAsString(node);

        }catch (JsonProcessingException e) {
            return;
        }

        System.out.println(json);
    }
}
