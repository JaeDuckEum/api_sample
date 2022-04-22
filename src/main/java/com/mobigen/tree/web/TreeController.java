package com.mobigen.tree.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mobigen.tree.model.TreeDto;
import com.mobigen.tree.service.TreeService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class TreeController {
	
	private final TreeService treeService;
	
	@GetMapping("/getCategoryList")
    public List<TreeDto> getCategoryList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return treeService.selectCategoryList();
    }

	@PostMapping("/save")
    public String saveCategoryTree(@RequestBody TreeDto param, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return treeService.insertCategoryTree(param);
    }
}
