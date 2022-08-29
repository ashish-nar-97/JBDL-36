package com.example.restapis;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImageController {

    @GetMapping(value = "/image",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@RequestParam("id") Integer id){

        // HW : can we add the content type as PNG without using produces.

        // https://picsum.photos/id/1/200/300

        String url = "https://picsum.photos/id/"+id+"/200/300";

        RestTemplate restTemplate = new RestTemplate();

        // first param : uri
        // second param : response type by the destination server (loreum picsum)
        byte[] image = restTemplate.getForObject(url, byte[].class);

        return image;

    }
}
