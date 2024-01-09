package com.example.JFit.weight;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface WeightRepository extends ListCrudRepository<Weight, Long> {

    List<Weight> findAllByWeightDateBetween(LocalDate start, LocalDate end, Sort sort);
}
