package br.com.avelar.recrutamento.controllers;

import br.com.avelar.recrutamento.user.User;
import br.com.avelar.recrutamento.user.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {
 private UserService service;
  
  @Autowired
  public UsersController(UserService service) {
    this.service = service;
  }
 
  @CrossOrigin
  @RequestMapping(method = RequestMethod.POST)
  @PreAuthorize("isAuthenticated()")
  public @ResponseBody User getUser(Principal principal) {
    User user = null;
    String name = principal.getName();
    
    if (name.contains("@")) {
      user = service.findByEmail(name);
    } else {
      user = service.find(name);
    }
    
    return user;
  }
}
