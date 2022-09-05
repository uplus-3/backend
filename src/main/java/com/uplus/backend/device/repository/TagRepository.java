package com.uplus.backend.device.repository;

import com.uplus.backend.device.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 담당자 : 이일환
 */
public interface TagRepository extends JpaRepository<Tag, Long> {

}
