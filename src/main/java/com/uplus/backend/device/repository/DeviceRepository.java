package com.uplus.backend.device.repository;

import com.uplus.backend.device.entity.Device;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface DeviceRepository extends JpaRepository<Device, Long> {

	List<Device> findByNetworkType(int networkType);

	List<Device> findTop5ByNameContainingIgnoreCaseOrderByLaunchedDateDesc(String name);

	List<Device> findTop5ByNameContainingIgnoreCaseAndNetworkTypeOrderByLaunchedDateDesc(
		String name, int networkType);

	List<Device> findByNameContainingIgnoreCaseAndNetworkTypeOrderByLaunchedDateDesc(String name,
		int networkType);

	List<Device> findByNameContainingIgnoreCaseOrderByLaunchedDateDesc(String name);

	@Query(value = "SELECT * FROM Device WHERE MATCH(name, company) AGAINST(?1)", nativeQuery = true)
	List<Device> findAutocompleteKeyword(String keyword);

	@Query(value = "SELECT * FROM Device WHERE networkType = :networkType and MATCH(name, company) AGAINST(?2)", nativeQuery = true)
	List<Device> findAutocompleteKeywordWithNetworkType(@Param("keyword") String keyword, @Param("networkType")int networkType);

	@Query(value = "SELECT * FROM Device WHERE MATCH(name, company) AGAINST(?1) ORDER BY launched_date DESC", nativeQuery = true)
	List<Device> search(String keyword);

	@Query(value = "SELECT * FROM Device WHERE networkType = :networkType and MATCH(name, company) AGAINST(?2)", nativeQuery = true)
	List<Device> searchWithNetworkType(@Param("keyword")String keyword, @Param("networkType")int networkType);

}
