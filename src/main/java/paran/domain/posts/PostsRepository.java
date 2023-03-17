package paran.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*JpaRepository : DB Layer 접근자
JPA에선 Repository 라고 부르며, 인터페이스로 생성
생성 후, JpaRepository<Entitiy 클래스, PK 타입> 상속하면 기본적인 CRUD 메소드가 자동으로 생성*/

//**************Entity 클래스와 기본 Entity Repository는 함께 위치********************
//도메인 패키지에서 함께 관리
public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
