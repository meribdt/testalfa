package com.example.testalfa;

import java.util.List;

import com.example.testalfa.dto.MyUser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "UserClient", url = "${custom.placeholder_url}")
public interface IUserClient {
    @GetMapping(value = "/users", consumes = "application/json")
    List<MyUser> getUser();
}
