package com.redispractice.repositories;

import com.redispractice.dto.CarPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarPriceRepository extends CrudRepository<CarPrice, String> {
}
