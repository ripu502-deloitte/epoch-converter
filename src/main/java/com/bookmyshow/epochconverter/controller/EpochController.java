package com.bookmyshow.epochconverter.controller;

import com.bookmyshow.epochconverter.dto.EpochResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;

@RestController
public class EpochController {

    private Environment environment;

    public EpochController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/epoch/next/{x}/days")
    public ResponseEntity<EpochResponse> getEpochTimeAfterXdays(@PathVariable( name= "x") int x)
    {
        Long timeStamp = (x * 86400)  +  System.currentTimeMillis() /1000;
        return new ResponseEntity<>(new EpochResponse(timeStamp,
                environment.getProperty("local.server.port")),
                HttpStatus.OK);
    }
}
