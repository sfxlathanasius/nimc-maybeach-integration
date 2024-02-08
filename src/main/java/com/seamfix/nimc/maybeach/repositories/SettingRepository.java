package com.seamfix.nimc.maybeach.repositories;

import com.seamfix.nimc.maybeach.entities.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettingRepository extends JpaRepository<Setting,Long> {

    Optional<Setting> findByName(String name);
}
