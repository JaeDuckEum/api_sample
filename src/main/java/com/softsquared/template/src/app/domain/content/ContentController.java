package com.softsquared.template.src.app.domain.content;

import com.softsquared.template.config.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/contents")
public class ContentController {
    private final ContentService contentService;

    /**
     * 컨텐츠 전체 조회
     * */
    @GetMapping(value="",produces = "application/json; charset=utf-8")
    public BaseResponse<List<ContentDTO.findRes>> findAllContents(){
        return new BaseResponse<>(contentService.findAllContents());
    }

    /**
     * 컨텐츠 조회
     * */
    @GetMapping(value="/{contentId}",produces = "application/json; charset=UTF-8")
    public BaseResponse<ContentDTO.findRes> findContent(@PathVariable("contentId") Long id){
        return new BaseResponse<>(contentService.findContent(id));
    }

    /**
     * 컨텐츠 추가
     * */
    @PostMapping
    public BaseResponse<ContentDTO.createRes> createContent(@RequestBody ContentDTO.createReq req){
        return new BaseResponse<>(contentService.createContent(req));
    }


}
