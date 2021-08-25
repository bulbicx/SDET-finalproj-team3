package com.qa.choonz.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteContoller {
    @GetMapping(value = "/")
    public String index() {
        return "index.html";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "index.html";
    }

    @GetMapping(value = "/tracks")
    public String tracks() {
        return "tracks.html";
    }
    
    @GetMapping(value = "/albums")
    public String albums() {
        return "albums.html";
    }
    
    @GetMapping(value = "/albumsingle")
    public String albumSingle() {
        return "albumsingle.html";
    }

}
