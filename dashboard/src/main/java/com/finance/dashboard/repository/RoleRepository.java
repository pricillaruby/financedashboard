package com.finance.dashboard.repository;

import com.finance.dashboard.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity ,Integer> {
}
