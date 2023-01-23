package com.app.ERS.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.ERS.exceptions.EmailAlreadyExistsException;
import com.app.ERS.exceptions.InvalidAccess;
import com.app.ERS.models.RegisterObject;
import com.app.ERS.models.User;
import com.app.ERS.services.UserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/employees")
@CrossOrigin("*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private UserService uService;

  @PostMapping("/register")
  public User register(@RequestBody RegisterObject ro) {
    System.out.println(ro);
    return uService.registerUser(ro.firstName, ro.lastName, ro.email, ro.password);
  }

  @PostMapping("/login")
  public User login(@RequestBody LinkedHashMap<String, String> body) {

    String email = body.get("email");
    String password = body.get("password");

    return uService.loginUser(email, password);
  }

  @ExceptionHandler({InvalidAccess.class})
  public ResponseEntity<String> handleInvalid() {
    return new ResponseEntity<>("Invalid Credentials", HttpStatus.NOT_ACCEPTABLE);
  }

  @ExceptionHandler({EmailAlreadyExistsException.class})
  public ResponseEntity<String> handleExists() {
    return new ResponseEntity<>("Email already registered", HttpStatus.CONFLICT);
  }
}
