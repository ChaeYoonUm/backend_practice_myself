package paran.domain;

//save, findAll 기능 테스트

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import paran.domain.posts.Posts;
import paran.domain.posts.PostsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest //자동으로 H2 데이터베이스 실행
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    //JUnit에서 단위 테스트가 끝날 때마다 수행되는 메소드 지정
    //테스트간 데이터 침범 막기 위해 사용
    //여러 테스트가 동시에 수행되면 테스트용 데이터베이스인 H2에 데이터가 그대로 남아 있어 다음 테스트 실행 시 테스트가 실패할 수 있음
    public void clendup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        //테이블 posts에 insert/update 쿼리 실행
        //id 값이 있따면 update가, 없다면 insert 쿼리 실행
        postsRepository.save(Posts.builder().title(title).content(content).author("ucy0303@gmail.com").build());

        //when
        //테이블 posts에 있는 모든 데이터를 조회해오는 메소드
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
