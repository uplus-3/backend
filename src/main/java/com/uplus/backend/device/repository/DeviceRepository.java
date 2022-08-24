package com.uplus.backend.device.repository;

import com.uplus.backend.device.entity.Device;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {


	List<Device> findTop5ByNameContainingIgnoreCaseAndNetworkTypeOrderByLaunchedDateDesc(String name, int networkType);
}
