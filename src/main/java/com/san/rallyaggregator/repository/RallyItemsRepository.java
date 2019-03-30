package com.san.rallyaggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.san.rallyaggregator.model.RallyItem;

@RepositoryRestResource
public interface RallyItemsRepository extends JpaRepository<RallyItem, String> {

}
