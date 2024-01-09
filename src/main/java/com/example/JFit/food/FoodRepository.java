package com.example.JFit.food;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodRepository extends ListCrudRepository<Food, Long> {

    List<Food> findAllByFoodDateBetween(LocalDate start, LocalDate end, Sort sort);
}
