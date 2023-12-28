package com.example.jwt.domain.plz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlzRepository extends JpaRepository<Plz, String> {

}
