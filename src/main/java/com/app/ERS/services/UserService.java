package com.app.ERS.services;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.ERS.exceptions.EmailAlreadyExistsException;
import com.app.ERS.exceptions.InvalidCredentialsException;
import com.app.ERS.models.User;
import com.app.ERS.models.UserType;
import com.app.ERS.repositories.UserRepository;
import com.app.ERS.repositories.UserTypeRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

  private UserRepository uRepo;

  private UserTypeRepository utRepo;

  public User registerUser(String firstName, String lastName, String email, String password) {
    UserType type = utRepo.findById(1).get();
    List<UserType> types = new ArrayList<>();
    types.add(type);

    User u = new User(0, types, firstName, lastName, email, password, new ArrayList<>(),
        new ArrayList<>());

    try {
      User returnedUser = uRepo.save(u);
      return returnedUser;
    } catch (Exception ex) {
      throw new EmailAlreadyExistsException();
    }

  }

  public User loginUser(String email, String password) {
    User u = uRepo.getByEmail(email).orElseThrow(InvalidCredentialsException::new);

    if (!u.getPassword().equals(password)) {
      throw new InvalidCredentialsException();
    }

    return u;

  }

}
