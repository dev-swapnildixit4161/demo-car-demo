package com.nashtech.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String emailId;
    private String mobileNumber;

}