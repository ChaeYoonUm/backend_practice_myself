package paran;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import paran.web.HelloController;

//테스트 진행 시, JUnit에 내장된 실행자 외에 다른 실행자를 실행
//SpringRunner 라는 스프링 실행자를 사용
//즉, 스프링 부트 테스트와 JUnit 사이의 연결자 역할
@RunWith(SpringRunner.class)
//Web(Spring MVC)에 집중할 수 있는 어노테이션
//선언할 경우, @Controller, @ControllerAdvice 등을 사용할 수 있음
//단, @Service, @Component, @Repository 등은 사용할 수 없음
//컨트롤러만 사용하기 떄문에 선언
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    //스프링이 관리하는 빈(Bean) 주입받음
    @Autowired
    private MockMvc mvc; // 웹 API 테스트할 때 사용, 스프링 MVC 테스트의 시작점, HTTP GET, POST 등에 대한 API 테스트 가능
    
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";
        
        //mvc.perform(get)                   : MockMvc를 통해 /hello 주소로 HTTP GET 요청, 여러 검증 기능을 이어서 선언 가능
        //.andExpect(status().isOk())        : mvc.perform 결과 검증, 200, 404, 500 등의 상태 검증, 여기선 OK 즉, 200인지 아닌지를 검증
        //.andExpect(content().string(hello) : mvc.perform 결과 검증, 응답 본문의 내용 검증, Controller에서 "hello" 리턴하기 때문에 이 값이 맞는지 검증
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        //param : API 테스트할 때 사용될 요청 파라미터 설정, 값은 String만 허용, 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능
        //jsonpath : JSON 응답값을 필드별로 검증할 수 있는 메소드, $기준으로 필드명 명시, name과 amount 검증하기 떄문에 $.name, $.amount로 검증
        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", is(name))).andExpect(jsonPath("$.amount", is(amount)));
    }

}
