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
public class CategoryDto {
	private String prptyId;
	private String parentId;
	private Integer depth;
	private Integer sortOrder;
	private String value;
	private String ctgryVer;
	private String regUser;
	private String regDate;
	private String updtUser;
	private String updtDate;
	
	private String pid;
	private String name;
	
}
