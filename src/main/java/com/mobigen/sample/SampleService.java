package com.mobigen.sample;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.mobigen.framework.component.Messages;
import com.mobigen.framework.exception.JsonResultException;
import com.mobigen.framework.iris.IRISProperties;
import com.mobigen.framework.iris.Token;
import com.mobigen.framework.security.SessionManager;
import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
@AllArgsConstructor
public class SampleService {
    private final Messages message;
    private final SampleMapper sampleMapper;
    private final Token token;
    private final SessionManager sessionManager;
    private final IRISProperties properties;

    private String generateToken() throws Exception {
        Algorithm algorithm = Algorithm.HMAC256(properties.getToken().getSecret());
        Date today = new Date();
        return JWT.create().withExpiresAt(new Date(today.getTime() + (1000 * 60 * 60 * 24))).sign(algorithm);
    }

    public Object getMessage() {
        return message.get("sample.data");
    }

    public Object getUser(String username) throws Exception {
        return sampleMapper.getUser(username);
    }

    public Object authenticate(HttpServletRequest request, HttpServletResponse response, String username, String password) throws Exception {
        //String xAccessToken = token.getXAccessToken(username, password);
        Map user = (Map) getUser(username);
        if (!password.equals((String) user.get("PASSWORD"))) {
            throw new Exception();
        }

        // sessionManager.addTokenToResponse(request, response, xAccessToken);
        return generateToken();
    }

    public Object getSampleImages(int count) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Map<String, Object> image = new HashMap<>();
            image.put("id", i);
            image.put("src", "https://i.pravatar.cc/300?img=" + (i + 1));
            list.add(image);
        }
        return list;
    }
    
    public List<?> getBoardList(Map<String, Object> param) throws Exception {
        return sampleMapper.getBoardList(param);
    }
    
    public List<?> getException(Map<String, Object> param) throws Exception {
    	if ( 1 == 1 ) {
    		throw new Exception();
    	}
    	return sampleMapper.getBoardListException(param);
    }
    
    public List<?> getAccessDeniedException(Map<String, Object> param) throws Exception {
    	if ( 1 == 1 ) {
    		throw new Exception();
    	}
    	return sampleMapper.getBoardListException(param);
    }
    
    public List<?> getBoardListException(Map<String, Object> param) throws Exception {
    	String exceptionType = StringUtils.defaultString((String)param.get("exceptionType"), "");
    	
    	if ( ExceptionType.EXCEPTION.getCode().equals(exceptionType) ) {
    		throw new Exception("com.mobigen.sample.SampleController.getBoardListException.exception", null);
    	} else if ( ExceptionType.AccessDeniedException.getCode().equals(exceptionType) ) {
    		throw new JsonResultException("com.mobigen.sample.SampleController.getBoardListException.accessDeniedException", null);
    	}
    	
        return sampleMapper.getBoardListException(param);
    }
}