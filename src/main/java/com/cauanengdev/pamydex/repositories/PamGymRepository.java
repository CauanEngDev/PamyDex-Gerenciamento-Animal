package com.cauanengdev.pamydex.repositories;

import com.cauanengdev.pamydex.models.PamGym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PamGymRepository extends JpaRepository<PamGym, UUID> { }
