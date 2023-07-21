package com.pedro.restapi.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarefas")
public class TaskResource {

    private final Logger log = LoggerFactory.getLogger(TaskResource.class);

    @RequestMapping("/hello")
    @ResponseBody
    public String helloGFG()
    {
        log.info("Hello");
        return "Hello GeeksForGeeks";
    }
}
