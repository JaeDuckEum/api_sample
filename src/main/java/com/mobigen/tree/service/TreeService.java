package com.mobigen.tree.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mobigen.tree.model.CategoryDto;
import com.mobigen.tree.model.TreeDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreeService {
	private final TreeMapper treeMapper;
	
	public List<TreeDto> selectCategoryList() throws Exception {
		// DB 정보 다르므로 주석처리
//		String currentVer = treeMapper.selectCurrentCategoryVersion();
//		Map<String, Object> param = new HashMap<>();
//		param.put("ctgryVer", currentVer);

		List<TreeDto> result = new ArrayList<>();
		// DB 정보 다르므로 주석처리
		// List<CategoryDto> list = treeMapper.selectCategoryList(param);
		List<CategoryDto> list = new ArrayList<>();
		makeTree(list, result);
		
		// test data
		TreeDto treeDto = new TreeDto();
		treeDto.setName("test");
		treeDto.setDepth(1);
		result.add(treeDto);

		return result;
	}
	
	public void makeTree(List<CategoryDto> list, List<TreeDto> result) {
		for(CategoryDto category : list) {
			if(category.getDepth() == 1) {
				TreeDto tree = new TreeDto();
				tree.setId(category.getPrptyId());
				tree.setName(category.getValue());
				tree.setPrptyId(category.getPrptyId());
				tree.setParentId(category.getParentId());
				tree.setChildren(getChildCategory(list, category));
				result.add(tree);
			}
		}
	}
	
	public List<TreeDto> getChildCategory(List<CategoryDto> list, CategoryDto parent) {
		List<TreeDto> result = new ArrayList<>();
		
		for (CategoryDto c : list) {
            if (c.getDepth() != 1 && c.getParentId().equals(parent.getPrptyId())) {
            	TreeDto tree = new TreeDto();
            	tree.setId(c.getPrptyId());
        		tree.setName(c.getValue());
        		tree.setPrptyId(c.getPrptyId());
        		tree.setParentId(c.getParentId());
        		tree.setParent(parent);
        		tree.setChildren(getChildCategory(list, c));
        		result.add(tree);
            }
        }
		
		return result;
	}
	
	public String insertCategoryTree(TreeDto tree) throws Exception {
		int resultCount = 0;

		if(tree.getChildren()!= null) {
			List<TreeDto> firstTree = tree.getChildren();
			int index = 0;
			for(TreeDto t : firstTree) {
				CategoryDto param = new CategoryDto();
				param.setPrptyId(t.getPrptyId());
				param.setParentId(t.getPrptyId());
				param.setValue(t.getName());
				param.setDepth(1);
				param.setSortOrder(index);
				resultCount += treeMapper.insertCategory(param);
				t.setDepth(1);
				insertChildCategory(t);
				index++;
			}
		}
		
		return resultCount > 0 ? "success" : "fail";
	}
	
	public void insertChildCategory(TreeDto parent) throws Exception {
		
		if(parent.getChildren() != null) {
			List<TreeDto> list = parent.getChildren();
			int index = 0;
			for(TreeDto t : list) {
				t.setDepth(parent.getDepth()+1);
				CategoryDto param = new CategoryDto();
				param.setPrptyId(t.getPrptyId());
				param.setParentId(parent.getPrptyId());
				param.setValue(t.getName());
				param.setDepth(t.getDepth());
				param.setSortOrder(index);
				treeMapper.insertCategory(param);
				insertChildCategory(t);
			}
		}
		
	}

}
