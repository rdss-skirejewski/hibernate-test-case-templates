package org.hibernate.repository;

import org.hibernate.dto.TestEntityOne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OneRepository extends JpaRepository<TestEntityOne, UUID> {

}

