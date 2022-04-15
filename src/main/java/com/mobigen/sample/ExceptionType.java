package com.mobigen.sample;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ExceptionType {
	EXCEPTION("EX", "Exception"),
	AccessDeniedException("ADEX", "AccessDeniedException"),
	;
	
    @Getter
    private String code;

    @Getter
    private String codeName;
}
