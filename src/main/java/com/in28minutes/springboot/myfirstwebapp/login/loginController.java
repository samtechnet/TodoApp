package com.in28minutes.springboot.myfirstwebapp.login;

import com.in28minutes.springboot.myfirstwebapp.login.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class loginController {

    public loginController(AuthenticationService authentication) {
        this.authentication = authentication;
    }

    private AuthenticationService authentication ;
    @RequestMapping(value="login", method = RequestMethod.GET)
    public String login(){

        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String welcome(@RequestParam String name,@RequestParam String password,ModelMap model){
        if(authentication.authentication(name,password)){
            model.put("name", name);
            return "welcome";
        }
        model.put("error", "Invalid parameter, please try again!");
        return "login";
    }
}
