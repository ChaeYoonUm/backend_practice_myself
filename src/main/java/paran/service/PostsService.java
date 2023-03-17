package paran.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import paran.domain.posts.Posts;
import paran.domain.posts.PostsRepository;
import paran.web.dto.PostsListResponseDto;
import paran.web.dto.PostsResponseDto;
import paran.web.dto.PostsSaveRequestDto;
import paran.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

//Controller와 Dao의 중간 영역에서 사용
//@Transactional 사용되는 영역
@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById (Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity); // JPA : 영속성 컨텍스트, 영속성 컨텍스트 : 엔티티를 영구 저장하는 환경
    }

    @Transactional(readOnly = true) //트랜잭션 범위는 유지, 조회 속도 개선 (등록, 수정, 삭제 기능 없는 서비스 메소드에서 사용하는 것 추천)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));
        postsRepository.delete(posts); //엔티티 파라미터로 삭제가능, deleteById 메소드 이용하면 id로 삭제 가능
    }
}
