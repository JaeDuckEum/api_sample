package com.mobigen.sample;

import com.mobigen.framework.iris.User;
import com.mobigen.framework.security.SessionManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ViewErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    private final SessionManager sessionManager;

    /**
     * Vue Route가 History 모드 일 경우, route 설정대로 Controller 에
     * 넘어오게 되어 있는데 이때 세션이 있는 상태이면 메인 페이지로 돌릴 수 있어야 함
     */
    @GetMapping("/error")
    public String error() {
        User user = sessionManager.getUser();
        if (null == user) {
            return "index";
        }
        return "app";
    }
}
