import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CityController {

    private static final String DATA_URL =
        "https://samples.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&appid=b6907d289e10d714a6e88b30761fae22";

    @GetMapping("/count")
    public Map<String, Integer> getCityCount(@RequestParam("letter") String letter) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.getForEntity(DATA_URL, Map.class);
        List<Map<String, Object>> list = (List<Map<String, Object>>) response.getBody().get("list");

        int count = (int) list.stream()
                .filter(city -> ((String) city.get("name")).toLowerCase().startsWith(letter.toLowerCase()))
                .count();

        return Map.of("count", count);
    }
}
