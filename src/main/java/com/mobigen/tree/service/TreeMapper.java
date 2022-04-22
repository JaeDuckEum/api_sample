package com.mobigen.tree.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mobigen.tree.model.CategoryDto;

@Mapper
public interface TreeMapper {
	Integer insertCategory(CategoryDto param) throws Exception;
	String selectCurrentCategoryVersion() throws Exception;
	List<CategoryDto> selectCategoryList(Map<String, Object> param) throws Exception;
}
