package com.cauanengdev.pamydex.repositories;

import com.cauanengdev.pamydex.models.Specie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpecieRepository extends JpaRepository<Specie, UUID> { }
