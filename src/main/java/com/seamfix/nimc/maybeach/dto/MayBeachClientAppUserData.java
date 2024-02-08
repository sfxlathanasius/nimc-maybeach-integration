package com.seamfix.nimc.maybeach.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MayBeachClientAppUserData {
    private String loginId;
    private String firstname;
    private String lastname;
    private String email;
    private List<String> roles;

}
