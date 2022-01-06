package com.mediscreen.UI.webClients;

import java.util.List;

import com.mediscreen.UI.models.Note;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ui-report", url = "localhost:8083")
public interface ReportFeignClient {

    @GetMapping(value = "/rest/assess/{userId}")
    String getReport(@PathVariable("userId") Integer id);
}
