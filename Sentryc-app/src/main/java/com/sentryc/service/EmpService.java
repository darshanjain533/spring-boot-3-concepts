package com.sentryc.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.sentryc.dto.SumDto;
import com.sentryc.entity.Employee;
import com.sentryc.exception.CustomResourceNotFoundException;
import com.sentryc.repository.EmpRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@EnableCaching
@Slf4j
public class EmpService {

	@Autowired
	EmpRepository repo;
	
	@CachePut(value = "transaction", key = "#emp.id")
	public Employee fetchUpdatedEmployess(Integer id, Employee emp) {
		log.info("fetchUpdatedEmployess hit::::");
		// Body: {“amount”: double, “currency”: string, “type”: string, “parent_id”:integer}
		Employee empupdt = new Employee();
		Optional<Employee> employeeOptional = repo.findById(id);
		if (employeeOptional.isPresent()) {
			empupdt = employeeOptional.get();
			
			//if parent_id exists
			if(empupdt.getParentId() != null) {
				if(empupdt.getCurrency().equalsIgnoreCase(emp.getCurrency())) {
					empupdt.setAmount(emp.getAmount());
					empupdt.setCurrency(emp.getCurrency());
					empupdt.setType(emp.getType());
					empupdt.setParentId(emp.getParentId());
				}else {
					throw new CustomResourceNotFoundException("Existing currency and currency used while updating is not the same..");
				}
			}else {
				empupdt.setAmount(emp.getAmount());
				empupdt.setCurrency(emp.getCurrency());
				empupdt.setType(emp.getType());
				empupdt.setParentId(emp.getParentId());
			}
			repo.save(empupdt);
		} else {
			throw new CustomResourceNotFoundException("data is not presenet.. Invalid data entered...");
		}
		return empupdt;
	}
	
	@Cacheable("transaction")
	public Employee fetchEmployess(Integer id) {
		log.info("fetchEmployess hit::::");
		Optional<Employee> employeeOptional = repo.findById(id);
		if(employeeOptional.isPresent()) {
			return employeeOptional.get();
		}else {
			throw new CustomResourceNotFoundException("transaction data is not presenet.. Invalid data entered...");
		}
	}

	@Cacheable("transaction")
	public List<Integer> fetchAllTypes(String type) {
		log.info("fetchAllTypes hit::::");
		List<Employee> empList= repo.findByType(type);
		List<Integer> ids =  empList.stream().map(a->a.getId()).collect(Collectors.toList());
		return ids;
	}

	@Cacheable("transaction")
	public List<String> fetchAllCurrencies(){
		log.info("fetchAllCurrencies hit::::");
		return repo.findDistinctCurrency();
	}

	@Cacheable("transaction")
	public SumDto fetchSum(Integer id) {
		log.info("sundto hit::::");
		Double sum = 0.00;
		String currency = "";
		Optional<Employee> employeeOptional = repo.findById(id);
		if(employeeOptional.isPresent()) {
			Integer parentId = employeeOptional.get().getParentId();
			if(parentId != null) {
				List<Employee> empList = repo.findByParentId(parentId);
				sum = empList.stream().map(Employee::getAmount).collect(Collectors.summingDouble(Double::doubleValue));
				currency = empList.get(0).getCurrency();
				log.info("sum value from query jpa is:::"+repo.findsum(parentId));
				return SumDto.builder().sum(sum).currency(currency).build();
			}else {
				throw new CustomResourceNotFoundException("Parent ID missing for transaction::"+id);
			}
		}else {
			throw new CustomResourceNotFoundException("transaction data is not presenet.. Invalid data entered...");
		}
	}
}
