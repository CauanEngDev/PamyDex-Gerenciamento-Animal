package com.cauanengdev.pamydex.repositories;

import com.cauanengdev.pamydex.models.PamMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PamMasterRepository extends JpaRepository<PamMaster, UUID> { }
