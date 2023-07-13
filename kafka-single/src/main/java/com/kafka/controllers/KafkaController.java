package com.kafka.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@PostMapping("/info")
	public void infodata(@RequestBody InformationData infodata) {
		log.info("Info data controller called...");
		producerService.sendInfoData(infodata);
		
	}
	
	@GetMapping("/data")
	public void stringdata(@RequestParam("data") String data) {
		log.info("String data controller called...");
		producerService.sendMessage(data);
	}
	
	@GetMapping("/senddata")
	public void sendData() {
		int length = 100;
		Date dNow = new Date();
		log.info("send data controller called at:::"+dNow);
		for(int i=0; i<length; i++) {
	        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
	        String datetime = ft.format(dNow);
			InformationData data = InformationData.builder()
									._id(datetime+"-->"+i)
									.partner_code(datetime)
									.user_id(i)
									.username(datetime+"-uname-"+i)
									.amount(Double.parseDouble(datetime))
									.method("momo")
									.to_bank_name("Lương Ngọc Yến")
									.to_bank_code("finder")
									.to_bank_no(datetime)
									.from_bank_name("Nguyễn Minh Thắng")
									.from_bank_no("0977690539")
									.bank_trancode("42369490551")
									.note("42369490551")
									.type("PAYMENT")
									.wallet_type(99)
									.action("DEPOSIT")
									.status("FINISHED")
									.sync_status("SUCCESS")
									.sync_accounting_status("SUCCESS")
									.created_by("99999999999:Nguyễn Minh Thắng - 0977690539:system")
									.processed(true)
									.device_name("Phone")
									.os_name("Android")
									.browser_name("Chrome")
									.sale_advised("")
									.ip("27.70.247.61")
									.uniq_id("C4P14GJUFK7VR93")
									.en_n("Schaefer Galido")
									.invoice_uuid("c74f16e9-c507-44d6-b32c-a95678c4ec56")
									.created_time(dNow)
									.last_updated_time(dNow)
									.id(393859086)
									.time_approved(dNow)
									.balance_after(500324.0)
									.balance_before(324.0)
									.id_accounting(948753724)
									.retry_sync_accounting(0)
									.build();
			log.info("### {} out {} data sent for ",i,length,datetime+"-->"+i);
			producerService.sendInfoData(data);
		}
	}
	
}
