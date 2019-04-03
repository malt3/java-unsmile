import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class unsmile {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: \nunsmile <SMILE FILE>");
            return;
        }

        byte[] encoded;
        try {
            encoded = Files.readAllBytes(Paths.get(args[0]));
        }catch (IOException e) {
            System.err.println("Error reading smile file!");
            return;
        }
        ObjectMapper smileMapper = new ObjectMapper(new SmileFactory());

        JsonNode node;
        try {
            node = smileMapper.readTree(encoded);
        }catch (IOException e) {
            System.err.println("Error decoding smile file!");
            return;
        }

        ObjectMapper jsonMapper = new ObjectMapper();
        String json;
        try {
            json = jsonMapper.writeValueAsString(node);
        }catch (JsonProcessingException e) {
            System.err.println("Error encoding json string!");
            return;
        }

        System.out.println(json);
    }
}
