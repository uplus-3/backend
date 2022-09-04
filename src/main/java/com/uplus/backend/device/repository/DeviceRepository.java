package com.uplus.backend.device.repository;

import com.uplus.backend.device.entity.Device;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeviceRepository extends JpaRepository<Device, Long> {

	List<Device> findByNetworkType(int networkType);

	@Query(value = "SELECT DISTINCT d FROM Device d JOIN FETCH d.colors JOIN FETCH d.plan WHERE d.id = :id")
	Optional<Device> findByIdFetchJoinColorAndTag(@Param("id") Long id);

	@Query(value = "SELECT DISTINCT d FROM Device d JOIN FETCH d.colors JOIN FETCH d.plan WHERE d.networkType = :networkType")
	List<Device> findByNetworkTypeFetchJoinColorAndTag(@Param("networkType") int networkType);

	@Query(value = "SELECT * FROM device WHERE MATCH(name, company, serial_number) AGAINST(:keyword in boolean mode) ORDER BY launched_date DESC limit 5", nativeQuery = true)
	List<Device> findAutocompleteKeyword(@Param("keyword") String keyword);

	@Query(value = "SELECT * FROM device WHERE network_type = :networkType and MATCH(name, company, serial_number) AGAINST(:keyword in boolean mode) ORDER BY launched_date DESC limit 5", nativeQuery = true)
	List<Device> findAutocompleteKeywordWithNetworkType(@Param("keyword") String keyword,
		@Param("networkType") int networkType);

	@Query(value = "SELECT * FROM device WHERE MATCH(name, company, serial_number) AGAINST(:keyword in boolean mode) ORDER BY launched_date DESC", nativeQuery = true)
	List<Device> search(@Param("keyword") String keyword);

	@Query(value = "SELECT * FROM device WHERE network_type = :networkType and MATCH(name, company, serial_number) AGAINST(:keyword in boolean mode) ORDER BY launched_date DESC", nativeQuery = true)
	List<Device> searchWithNetworkType(@Param("keyword") String keyword,
		@Param("networkType") int networkType);
}
