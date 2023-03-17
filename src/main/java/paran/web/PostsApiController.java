package paran.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import paran.service.PostsService;
import paran.web.dto.PostsResponseDto;
import paran.web.dto.PostsSaveRequestDto;
import paran.web.dto.PostsUpdateRequestDto;

@RequiredArgsConstructor //생성자로 Bean 객체 받도록 함, @Autowired와 같은 방식이지만 더 추천되는 방식임
@RestController
public class PostsApiController {
    
    private final PostsService postsService;
    
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
