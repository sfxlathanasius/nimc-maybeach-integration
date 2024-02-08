package com.seamfix.nimc.maybeach.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FepPojo {
    private String code;

    private String name;

    private String status;
    private String jurisdiction;

    public FepPojo(String code, String name, String status) {
        this.code = code;
        this.name = name;
        this.status = status;
    }
}
