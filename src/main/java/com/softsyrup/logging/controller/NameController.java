package com.softsyrup.logging.controller;

import com.softsyrup.logging.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {
    private StringUtil stringUtil;
    @Autowired
    public NameController(StringUtil stringUtil) {
        this.stringUtil = stringUtil;
    }

    @GetMapping("/hello/{name}/{surname}")
    public String getName(@PathVariable("name") String name,
                       @PathVariable("surname") String surname)
    {
        String nameSurname  = stringUtil.concatNameSurname(name, surname);
        return nameSurname;
    }
}
