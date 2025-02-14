package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@EnableAutoConfiguration
public class Website extends SpringBootServletInitializer {

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        try {
            return new ModelAndView("info", "busInfo", new BusInfo(postcode)) ;
        } catch (Exception e) {
            return new ModelAndView("invalid_postcode", "invalidPostcode", postcode) ;
        }
    }

    @RequestMapping("/busData")
    ModelAndView busData(@RequestParam("postcode") String postcode) {
        return new ModelAndView("bus_info", "busInfo", new BusInfo(postcode));
    }

    public static void main(String[] args) {
        SpringApplication.run(Website.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Website.class);
    }

}