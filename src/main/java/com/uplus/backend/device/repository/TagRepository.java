package com.uplus.backend.device.repository;

import com.uplus.backend.device.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
