package com.mobigen.sample;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionType {
	EXCEPTION("EX", "Exception"),
	AccessDeniedException("ADEX", "AccessDeniedException"),
	BoardViewException("BVEX", "BoardViewException"),
	BoardListException("BLEX", "BoardListException"),
	;
	
    @Getter
    private String code;

    @Getter
    private String codeName;
}
