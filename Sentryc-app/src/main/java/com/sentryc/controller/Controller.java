package com.sentryc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sentryc.dto.SumDto;
import com.sentryc.entity.Employee;
import com.sentryc.service.EmpService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bookingservice")
@Slf4j
public class Controller {
	
	@Autowired
	EmpService service;
	
	@GetMapping("/test")
	public String test() {
		log.info("This is sentryc data controller test::::");
		return "test data called from sentryc app";
	}
	
	@PutMapping("/transaction/{id}")
	public Employee updateEmployee(@PathVariable Integer id, @RequestBody Employee emp) {
		return service.fetchUpdatedEmployess(id,emp);
		
	}
	
	@GetMapping("/transaction/{id}")
	public Employee getEmployee(@PathVariable Integer id) {
		return service.fetchEmployess(id);
	}
	
	@GetMapping("/types/{type}")
	public List<Integer> getAllTypes(@PathVariable String type) {
		return service.fetchAllTypes(type);
	}
	
	@GetMapping("/currencies")
	public List<String> getAllCurrencies() {
		return service.fetchAllCurrencies();
	}
	
	@GetMapping("/sum/{id}")
	public SumDto getSum(@PathVariable Integer id) {
		log.info("getSum controller called...");
		return service.fetchSum(id);
	}
	
	@GetMapping("/transaction/{data}")
	public List<Employee> findEmployee(@PathVariable String data) {
		return service.findEmployee(data);
	}
	
	@PostMapping("/transaction/create")
	public Employee createEmployee(@RequestBody Employee emp) {
		return service.createEmployee(emp);
	}
}
