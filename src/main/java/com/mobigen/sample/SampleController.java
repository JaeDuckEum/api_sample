package com.mobigen.sample;

import com.mobigen.framework.iris.IRISProperties;
import com.mobigen.framework.iris.User;
import com.mobigen.framework.result.JsonResult;
import com.mobigen.framework.result.annotation.ResponseJsonResult;
import com.mobigen.framework.utility.RSA;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api")
@Controller
@AllArgsConstructor
public class SampleController {
    private final IRISProperties properties;
    private final SampleService sampleService;
    private final RSA rsa;

    @ResponseJsonResult
    @GetMapping("/authenticate/key")
    public Object publicKey() throws Exception {
        String base64PublicKey = null;
        if (Boolean.TRUE.equals(properties.getAuth().getRsaEnabled())) {
            base64PublicKey = rsa.getBase64PublicKeyFromKeyPair(rsa.getKeyPair());
        }
        return base64PublicKey;
    }

    @ResponseJsonResult
    @PostMapping("/authenticate")
    public Object authenticate(@RequestBody Map<String, String> param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = param.get("username");
        String password = param.get("password");

        log.debug("== BEFORE RSA Password[{}]", password);
        Boolean result = properties.getAuth().getRsaEnabled();
        if (Boolean.TRUE.equals(result)) {
            password = rsa.decryptRSA(password, rsa.getKeyPair().getPrivate());
        }
        log.debug("== AFTER RSA Password[{}]", password);

        return sampleService.authenticate(request, response, username, password);
    }

    @ResponseJsonResult
    @GetMapping("/user")
    public Object user(@AuthenticationPrincipal User user) {
        return user.getUsername();
    }

    @ResponseJsonResult
    @GetMapping("/message")
    public Object message() {
        return sampleService.getMessage();
    }

    @ResponseJsonResult
    @GetMapping("/user/{username}")
    public Object getUser(@PathVariable String username) throws Exception {
        return sampleService.getUser(username);
    }

    @ResponseJsonResult
    @GetMapping("/sample-images")
    public Object getSampleImages(int count) throws Exception {
        return sampleService.getSampleImages(count);
    }
    
    @ResponseJsonResult
    @PostMapping("/board/list")
    public Object getBoardList(@RequestBody Map<String, Object> param) throws Exception {
    	List list = sampleService.getBoardList(param);
    	return list;
    }
    
    @ResponseJsonResult
    @PostMapping("/board/list/exception")
    public Object getException(@RequestBody Map<String, Object> param) throws Exception {
    	
    	List list = sampleService.getException(param);
    	
    	return list;
    }
    
    @ResponseJsonResult
    @PostMapping("/board/list/accessDeniedException")
    public Object getAccessDeniedException(@RequestBody Map<String, Object> param) throws Exception {
    	
    	String exceptionType = StringUtils.defaultString((String)param.get("exceptionType"), "");
    	
    	List list = null;
    	if ( ExceptionType.EXCEPTION.getCode().equals(exceptionType) ) {
    		list = sampleService.getException(param);
    	} else if ( ExceptionType.AccessDeniedException.getCode().equals(exceptionType) ) {
    		list = sampleService.getAccessDeniedException(param);
    	}
    	return list;
    }
    
    /*@ResponseJsonResult
    @PostMapping("/board/exception")
    public Object getBoardException(@RequestBody Map<String, Object> param) throws Exception {
    	List list = sampleService.getBoardServiceException(param);
    	return list;
    }*/
    
    @PostMapping("/board/exception")
    @ResponseBody
    public Object getBoardException(@RequestBody Map<String, Object> param) throws Exception {
    	
    	JsonResult js = new JsonResult();
    	List list = sampleService.getBoardServiceException(param);
    	js.setData(list);
    	return js;
    }
}