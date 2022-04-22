package com.mobigen.tree.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // 모든 필드에 대한 생성자 생성
@NoArgsConstructor // 파라미터 없는 생성자 생성
@Builder(toBuilder = true)
@Data
public class TreeDto {
	private String pid;
	private String name;
	private String id;
	
	private String prptyId;
	private String parentId;
	private String value;
	private int depth;
	
	private CategoryDto parent;
	
	private List<TreeDto> children;
	
}
