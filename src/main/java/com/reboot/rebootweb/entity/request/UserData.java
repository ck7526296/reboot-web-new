package com.reboot.rebootweb.entity.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class UserData {

    private String method;
    private List<String> params;
    private Long id;
}