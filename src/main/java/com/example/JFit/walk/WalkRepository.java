package com.example.JFit.walk;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface WalkRepository extends ListCrudRepository<Walk, Long> {

    List<Walk> findAllByWalkDateBetween(LocalDate start, LocalDate end, Sort sort);
}
