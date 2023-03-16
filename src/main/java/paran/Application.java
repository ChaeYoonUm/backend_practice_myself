package paran;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//메인 클래스
//@SpringBootApplication 에서부터 읽어 나가기 떄문에 최상단에 위치해야 함
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
