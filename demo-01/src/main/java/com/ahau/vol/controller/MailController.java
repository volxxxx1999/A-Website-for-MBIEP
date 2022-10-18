package com.ahau.vol.controller;

import com.ahau.vol.common.Code;
import com.ahau.vol.common.Result;
import com.ahau.vol.domain.Mail;
import com.ahau.vol.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/simple")
    public Result SendSimpleMessage(@RequestBody Mail mail) {
            mailService.sendSimpleMail(mail);
            return new Result(Code.SEND_OK,"success");
    }
}
