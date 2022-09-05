package com.uplus.backend.device.repository;

import com.uplus.backend.device.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 담당자 : 이일환
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

}
