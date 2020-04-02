package deuxshiri.io.virgool.springsecurity.demo.controller;

import deuxshiri.io.virgool.springsecurity.demo.entity.User;
import deuxshiri.io.virgool.springsecurity.demo.service.UserService;
import deuxshiri.io.virgool.springsecurity.demo.user.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
@RequestMapping("/register")
public class RegistrationController {

  @Autowired
  private UserService userService;

  private Logger logger = Logger.getLogger(getClass().getName());

  @InitBinder
  public void initBinder(WebDataBinder dataBinder) {
    var stringTrimmerEditor = new StringTrimmerEditor(true);
    dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
  }

  @GetMapping("/showRegistrationForm")
  public String showMyLoginPage(Model theModel) {
    theModel.addAttribute("user", new UserDetails());
    return "registration-form";
  }

  @PostMapping("/processRegistrationForm")
  public String processRegistrationForm(@Valid @ModelAttribute("user") UserDetails user,
																				BindingResult theBindingResult, Model theModel) {

    String userName = user.getUsername();
    logger.info("Processing registration form for: " + userName);

    // form validation
    if (theBindingResult.hasErrors()) {
      return "registration-form";
    }

    // check the database if user already exists
		User existing = userService.findByUsername(userName);
		if (existing != null) {
      theModel.addAttribute("user", new UserDetails());
      theModel.addAttribute("registrationError", "Username already exists.");

      logger.warning("Username already exists.");
      return "registration-form";
    }

    // create user account
    userService.save(user);
    logger.info("Successfully created user: " + userName);

    return "registration-confirmation";
  }
}
