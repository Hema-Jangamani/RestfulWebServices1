package com.cedrus.RestfulWebservices1.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWordController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/helloworld")
    public String helloworld(){
        return "Hello World";
    }

    @GetMapping("/helloworld-bean")
    public HelloWordBean helloworldbean(){
        return new HelloWordBean("Hello World");
    }

    @GetMapping("/helloworld/path-variable/{name}")
    public HelloWordBean helloworldpath(@PathVariable String name){
        return new HelloWordBean(String.format("Hello World, %s",name));
    }

    @GetMapping("/helloworld-internationalized")
    public String helloworldInternationalized(
//            @RequestHeader(name = "Accept-Language", required = false) Locale locale
            ){
        return messageSource.getMessage("good.morning.message", null, "Default Message"
//                locale);
                , LocaleContextHolder.getLocale());
        //        return "Hello World";
    }
}
