package com.ss.lms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ss.lms.entity.Publisher;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer>{

}