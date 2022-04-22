package com.softsquared.template.src.app.domain.content;

import com.softsquared.template.config.BaseException;
import com.softsquared.template.config.BaseResponseStatus;
import com.softsquared.template.config.State;
import com.softsquared.template.src.app.domain.user.User;
import com.softsquared.template.src.app.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final UserRepository userRepository;


    public List<ContentDTO.findRes> findAllContents(){
        List<Content> allContents = contentRepository.findAllByState(State.ACTIVE);

        return allContents.stream().map((content)-> {
            User user = userRepository.findById(content.getUser().getId())
                    .orElseThrow(()-> new BaseException(BaseResponseStatus.REQUEST_ERROR));
            return ContentConverter.convert(content,user);
        }).collect(toList());
    }

    public ContentDTO.findRes findContent(Long id){
        Content content = contentRepository.findById(id)
                .orElseThrow(()-> new BaseException(BaseResponseStatus.REQUEST_ERROR));
        User user = userRepository.findById(content.getUser().getId())
                .orElseThrow(()-> new BaseException(BaseResponseStatus.REQUEST_ERROR));
        return ContentConverter.convert(content,user);
    }


    public ContentDTO.createRes createContent(ContentDTO.createReq req){
        User user = userRepository.findById(req.getUserId())
                .orElseThrow(()-> new BaseException(BaseResponseStatus.REQUEST_ERROR));
        Content content = contentRepository.save(req.contentBuild(user));
        return new ContentDTO.createRes(content.getId(), content.getTitle());
    }

}
