package com.zz.controller;

import com.zz.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zsj55
 */
@Controller
@RequestMapping("thymeleaf")
public class DemoController {

    @RequestMapping("demo1")
    public String to_demo1(Model m){
        m.addAttribute("helloname","jerry");
        Account  account = new Account();
        account.setName("小明");
        m.addAttribute("a",account);


        Account  account1 = new Account();
        account1.setName("小明");
        account1.setTel("1234565");
        Account  account2 = new Account();
        account2.setName("小s");
        account2.setTel("234234");
        Account  account3 = new Account();
        account3.setName("wang");
        account3.setTel("0967548");
        List<Account> clist = new ArrayList<Account>();
        clist.add(account1);
        clist.add(account2);
        clist.add(account3);
        m.addAttribute("a",account);
        m.addAttribute("cs",clist);
        return "demo1";
    }

    @RequestMapping("demo2")
    public String to_demo2(Model m){
        Account account = new Account();
        account.setName("小明");
        account.setTel("1242345");
        m.addAttribute("a",account);
        return "demo2";
    }
}
