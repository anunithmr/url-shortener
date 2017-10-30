package com.leverton.service;

import com.leverton.dto.ShortUrlResponse;
import com.leverton.models.Urls;
import com.leverton.repositories.UrlsRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TestUrlShortenerService {

  @Mock
  UrlsRepository urlsRepository;

  UrlShortnerService urlShortnerService;

  @Before
  public void setup(){
    MockitoAnnotations.initMocks(this);
    this.urlShortnerService = new UrlShortnerService(urlsRepository);
  }

  @Test
  public void testUrlShorteningFirstEntry(){
    Mockito.when(urlsRepository.findOne(Mockito.anyString())).thenReturn(null);
    ShortUrlResponse response = urlShortnerService.createShortUrl("http://google.com");

    Mockito.verify(urlsRepository, Mockito.times(1)).findOne(Mockito.anyString());
    Mockito.verify(urlsRepository, Mockito.times(1)).save(Mockito.any(Urls.class));
    Mockito.verifyNoMoreInteractions(urlsRepository);
  }

  @Test
  public void testUrlShorteningDuplicateEntry(){
    Urls urls = new Urls("")
    Mockito.when(urlsRepository.findOne(Mockito.anyString())).thenReturn(null);
    ShortUrlResponse response = urlShortnerService.createShortUrl("http://google.com");

    Mockito.verify(urlsRepository, Mockito.times(1)).findOne(Mockito.anyString());
    Mockito.verify(urlsRepository, Mockito.times(1)).save(Mockito.any(Urls.class));
    Mockito.verifyNoMoreInteractions(urlsRepository);
  }
}
