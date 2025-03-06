package reisetech.student.management;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

		private Map<String, String> student;

    public Application() {
        student = new HashMap<>
                (Map.of("name", "tanaka", "age", "40"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/studentInfo")
    public String getStudentInfo() {
        return "name:" + student.get("name") + ";age:" + student.get(
                "age");
    }

    @PostMapping("/studentInfo")
    public void setStudentInfo(@RequestParam(required = false) String name,
            @RequestParam(required = false) String age) {
        if (name != null) {
            student.put("name", name);
        }
        if (age != null) {
            student.put("age", age);
        }
    }
}





