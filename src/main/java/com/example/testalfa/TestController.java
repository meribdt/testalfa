package com.example.testalfa;

import java.util.List;
import java.util.stream.Collectors;

import com.example.testalfa.dto.MyUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private IUserClient client;

    @GetMapping(value = "/test/{id}")
    public String getUser(@PathVariable Integer id) {
        List<MyUser> result = client.getUser().stream()
        .filter(x -> x.getId().equals(id))
        .collect(Collectors.toList());
        return result.get(0).getName();
    }
}
