package com.sentryc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sentryc.entity.Employee;

@Repository
public interface EmpRepository extends JpaRepository<Employee, Integer>{
	List<Employee> findByType(String type);
	
	List<Employee> findByParentId(Integer parentId);
	
	List<Employee> findByCustomerName(String customerName);
	
	@Query("SELECT SUM(e.amount) FROM Employee e WHERE e.parentId=?1")
	Double findsum(Integer parentId);

	@Query("SELECT DISTINCT e.currency FROM Employee e")
	List<String> findDistinctCurrency();
	
}
