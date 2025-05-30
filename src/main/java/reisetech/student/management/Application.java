package reisetech.student.management;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import java.io.PrintStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "受講生登録システム"))
public class Application {

    public static void main(String[] args) throws Exception {
        // UTF-8で標準出力を設定
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.setErr(new PrintStream(System.err, true, "UTF-8"));
        SpringApplication.run(Application.class, args);

    }


}