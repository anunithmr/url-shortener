package com.leverton.resources;

import java.net.URL;
import java.util.List;

import com.leverton.dto.ShortUrlResponse;
import com.leverton.models.Urls;
import com.leverton.service.UrlShortnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(path = "/")
public class UrlShortnerResource {

  private final UrlShortnerService urlShortnerService;

  @Autowired
  public UrlShortnerResource(UrlShortnerService service){
    this.urlShortnerService = service;
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<ShortUrlResponse> shortenUrl(@RequestParam("url") String longUrl){
    return ResponseEntity.ok().body(urlShortnerService.createShortUrl(longUrl));
  }

  @RequestMapping(path = "{shortUrl}", method = RequestMethod.DELETE)
  public ResponseEntity<ShortUrlResponse> removeUrl(@PathVariable String shortUrl){
    urlShortnerService.removeShortUrl(shortUrl);
    return ResponseEntity.ok().build();
  }

  @RequestMapping(path = "{shortUrl}", method = RequestMethod.GET)
  public ResponseEntity redirect(@PathVariable String shortUrl) {
    String destination = urlShortnerService.getLongUrl(shortUrl);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Location", destination);
    return new ResponseEntity(headers, HttpStatus.FOUND); // 302 REDIRECT
  }

}
