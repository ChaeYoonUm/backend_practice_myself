package paran.dto;

import org.junit.Test;
import paran.web.dto.HelloResponseDto;

//assertThat
//assertj라는 테스트 검증 라이브러리의 검증 메소드
//검증하고 싶은 대상을 인자로 받음
//메소드 체이닝 지원, isEqualTo와 같은 메소드를 이어서 사용 가능
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    
    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;
        
        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        
        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
