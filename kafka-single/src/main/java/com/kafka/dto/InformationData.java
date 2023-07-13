package com.kafka.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class InformationData {

	public String _id;
    public String partner_code;
    public int user_id;
    public String username;
    public double amount;
    public String method;
    public String to_bank_code;
    public String to_bank_name;
    public String to_bank_no;
    public String from_bank_name;
    public String from_bank_no;
    public String bank_trancode;
    public String note;
    public String type;
    public int wallet_type;
    public String action;
    public String status;
    public String sync_status;
    public String sync_accounting_status;
    public String created_by;
    public boolean processed;
    public String device_name;
    public String os_name;
    public String browser_name;
    public String sale_advised;
    public String ip;
    public String uniq_id;
    public String en_n;
    public String invoice_uuid;
    public String user_uuid;
    public Date created_time;
    public Date last_updated_time;
    public int id;
    public Date time_approved;
    public double balance_after;
    public double balance_before;
    public int retry;
    public int id_accounting;
    public int retry_sync_accounting;
}
