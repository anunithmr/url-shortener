package com.leverton.repositories;

import com.leverton.models.Urls;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlsRepository extends CrudRepository<Urls, String> {
}
