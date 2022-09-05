package com.uplus.backend.launchingdevice.repository;

import com.uplus.backend.launchingdevice.entity.LaunchingDevice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 담당자 : 김수현
 */
public interface LaunchingDeviceRepository extends JpaRepository<LaunchingDevice, Long> {

	List<LaunchingDevice> findByNetworkType(int networkType);
}
