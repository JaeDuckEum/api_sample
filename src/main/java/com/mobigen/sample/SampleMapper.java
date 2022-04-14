package com.mobigen.sample;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository("sampleMapper")
public interface SampleMapper {
    Object getUser(String username) throws Exception;
    List<?> getBoardList(Map<String, Object> param) throws Exception;
}
