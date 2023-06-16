package com.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.dto.InformationData;
import com.kafka.service.ProducerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/kafka/data")
@Slf4j
public class KafkaController {

	 @Autowired
	 private ProducerService<InformationData> producerService;
	
	@GetMapping("/test")
	public InformationData testdata() {
		InformationData data = InformationData.builder().datainfo("This is test data").build();
		return data;
	}
	
	@PostMapping("/info")
	public void Infodata(@RequestBody InformationData infodata) {
		log.info("Info data controller called...");
		producerService.sendInfoData(infodata);
		
	}
	
	@PostMapping("/data")
	public void Stringdata(@RequestBody String data) {
		log.info("String data controller called...");
		producerService.sendMessage(data);
	}
	
}
