package com.leverton.service;

import java.net.MalformedURLException;
import java.net.URL;

import com.leverton.dto.ShortUrlResponse;
import com.leverton.models.Urls;
import com.leverton.repositories.UrlsRepository;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlShortnerService {

  private static final String PREFIX = "http://levert.on/";
  private final UrlsRepository urlsRepository;

  @Autowired
  public UrlShortnerService(UrlsRepository urlsRepository){
    this.urlsRepository = urlsRepository;
  }

  public ShortUrlResponse createShortUrl(String longUrl){
    checkValidity(longUrl);
    String hash = RandomStringUtils.randomAlphanumeric(6);;
    while(urlsRepository.findOne(hash) != null){
      hash = RandomStringUtils.randomAlphanumeric(6);
    }
    Urls urls = new Urls(hash, longUrl);
    urlsRepository.save(urls);
    return new ShortUrlResponse(PREFIX+hash);
  }

  public String getLongUrl(String hash){
    Urls urls = urlsRepository.findOne(hash);
    if(urls == null){
      throw new IllegalArgumentException("Resource not found!");
    }
    return urls.getActualURL();
  }

  public void removeShortUrl(String hash){
    urlsRepository.delete(hash);
  }

  private void checkValidity(String longUrl) {
    try {
      URL url = new URL(longUrl);
    } catch (MalformedURLException e) {
      throw new IllegalArgumentException("Invalid URL input");
    }
  }

}
