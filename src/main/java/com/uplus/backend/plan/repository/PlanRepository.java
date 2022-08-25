package com.uplus.backend.plan.repository;

import com.uplus.backend.plan.entity.Plan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {

	List<Plan> findByNetworkType(int networkType);

	List<Plan> findByNetworkTypeOrderByPriceAsc(int networkType);
}
