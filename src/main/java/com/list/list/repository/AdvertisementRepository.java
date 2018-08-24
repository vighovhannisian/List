package com.list.list.repository;

import com.list.list.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement,Integer> {
List<Advertisement> findAllByCategoryId(int id);
}

