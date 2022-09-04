package com.uplus.backend.launchingdevice.repository;

import com.uplus.backend.launchingdevice.entity.LaunchingDevice;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchingDeviceRepository extends JpaRepository<LaunchingDevice, Long> {

    List<LaunchingDevice> findByNetworkType(int networkType);
}
