package tz.maduka.uaa_v1.util.response;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class ResponseService {
    private static final String JSON_FILE_PATH = "src/main/resources/assets/response.json";  // Path to your JSON file

    public static ResponseStatus getResponseById(String searchId) {
        try {
            // Create an ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            // Read the JSON data from the file
            JsonNode responseArray = objectMapper.readTree(new File(JSON_FILE_PATH));

            // Search for the response by ID
            for (JsonNode response : responseArray) {
                if (response.path("response").path("id").asText().equals(searchId)) {
                    // Map the response part (id, status, code, message) to Response class
                    return new ResponseStatus(
                            response.path("response").path("id").asText(),
                            response.path("response").path("status").asBoolean(),
                            response.path("response").path("code").asText(),
                            response.path("response").path("message").asText()
                    );
                }
            }
        }catch (Exception e){
            System.out.println("Error "+e);
            return new ResponseStatus("None",false,"Null","Null");
        }
        return new ResponseStatus("None",false,"Null","Null");
    }
}

//
//ResponseStatus responseStatus = ResponseService.getResponseById(searchId);
//
//// Let's assume we get a list of data here (this could be fetched from a DB or another source)
//List<Object> dataList = fetchDataList(); // Replace this with actual data fetching logic
//
//        if (dataList != null && !dataList.isEmpty()) {
//        return new GqlResponseDto<>(responseStatus, dataList);  // Return with data list
//        } else {
//        // Return GqlResponseDto without data if data is empty or not found
//        return new GqlResponseDto<>(responseStatus);  // Only response, no data
//        }
