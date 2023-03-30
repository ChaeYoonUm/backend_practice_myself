package paran.domain.posts;

//import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import paran.domain.BaseTimeEntity;

import javax.persistence.*;

// 주요 어노테이션을 클래스와 가깝게 위치 -> 주요하지 않은 어노테이션을 위에 두면 쉽게 삭제 가능
@Table(name = "tbl_memo")
@Getter //롬복 어노테이션
@NoArgsConstructor //롬복 어노테이션
@Entity //JPA의 어노테이션
//*******Entity 클래스에서는 절대 Setter 메소드 만들지 않음************ : 인스턴스 값들이 언제 어디서 변해야 하는지 명확하게 구분 못하기 때문
public class Posts extends BaseTimeEntity { //Posts 클래스 : 실제 DB의 테이블과 매칭될 클래스 = Entity 클래스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }

}

//@Entity
//테이블과 링크될 클래스임을 나타냄
//클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭 : SalesManager.java -> sales_manager_table
//------------------------------------------------------------------------------------------------------
//@Id
//해당 테이블의 PK (Primary Key) 필드 나타냄
//------------------------------------------------------------------------------------------------------
//GenerationType
//PK의 생성 규칙 나타냄
//------------------------------------------------------------------------------------------------------
//@Column
//테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨
//사용 이유 : 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
//문자열의 경우 VARCHAR(255)가 기본, 사잊를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용됨
//------------------------------------------------------------------------------------------------------
//@NoArgsConstructor
//기본 생성자 자동 추가
//public Posts() {}
//------------------------------------------------------------------------------------------------------
//@Getter
//클래스 내 모든 필드의 Getter 메소드 자동생성
//------------------------------------------------------------------------------------------------------
//@Builder
//해당 클래스의 빌더 패턴 클래스 생성
//생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함