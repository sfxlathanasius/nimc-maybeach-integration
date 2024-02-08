/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seamfix.nimc.maybeach.enums;

@SuppressWarnings("PMD")
public enum SettingsEnum {

    MAYBEACH_TEST_URL("MAYBEACH-TEST-URL", "https://graph.maybeachtech.com.ng/graphql", "Maybeach url for test"),
    MAYBEACH_PROD_URL("MAYBEACH-PROD-URL", "https://graph.maybeachtech.com.ng/graphql", "Maybeach url for production"),
    MAYBEACH_TEST_AUTHORIZATION("MAYBEACH-TEST-AUTHORIZATION", "Basic ZHI5cGhpbDAwNzpjaGlHSVJMPTEyeDU=", "Maybeach authorization token for test"),
    MAYBEACH_PROD_AUTHORIZATION("MAYBEACH-PROD-AUTHORIZATION", "Basic ZHI5cGhpbDAwNzpjaGlHSVJMPTEyeDU=", "Maybeach authorization token for production"),
    MAYBEACH_PROD_TOKEN("MAYBEACH-PROD-TOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiYXBpOXUzNDUzNzgzODIxMTUxMzk5NjQ1IiwiYXBwbGljYXRpb25fa2V5IjoiOXUzNDUzNzgzODIxNzM4NDIyOTM5IiwiZXhwaXJhdGlvbiI6MTc5NDM5Mjc2MH0.bB9qfXsxCDfNyXVPxYq0NpaPucE_w0TxwOU4pZ8-FuI", "Maybeach token for production"),
    MAYBEACH_TEST_TOKEN("MAYBEACH-TEST-TOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiYXBpOXUzNDUzNzgzODIxMTUxMzk5NjQ1IiwiYXBwbGljYXRpb25fa2V5IjoiOXUzNDUzNzgzODIxNzM4NDIyOTM5IiwiZXhwaXJhdGlvbiI6MTc5NDM5Mjc2MH0.bB9qfXsxCDfNyXVPxYq0NpaPucE_w0TxwOU4pZ8-FuI", "Maybeach token for TEST"),
    MAYBEACH_MINIMUM_VERSION("MAYBEACH-MINIMUM-VERSION", "1.42", "This is used to configure the minimum version that should be able to push data to maybeach");

    SettingsEnum(String name, String value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    private String name;
    private String value;
    private String description;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
