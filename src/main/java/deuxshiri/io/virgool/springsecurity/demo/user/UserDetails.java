package deuxshiri.io.virgool.springsecurity.demo.user;

import deuxshiri.io.virgool.springsecurity.demo.validation.FieldMatch;
import deuxshiri.io.virgool.springsecurity.demo.validation.ValidEmail;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@FieldMatch.List(
        {@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")})
public class UserDetails {

  @NotNull(message = "is required")
  @Size(min = 1, message = "is required")
  private String username;

  @NotNull(message = "is required")
  @Size(min = 1, message = "is required")
  private String password;

  @NotNull(message = "is required")
  @Size(min = 1, message = "is required")
  private String matchingPassword;

  @NotNull(message = "is required")
  @Size(min = 1, message = "is required")
  private String firstName;

  @NotNull(message = "is required")
  @Size(min = 1, message = "is required")
  private String lastName;

  @ValidEmail
  @NotNull(message = "is required")
  @Size(min = 1, message = "is required")
  private String email;

  public UserDetails() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMatchingPassword() {
    return matchingPassword;
  }

  public void setMatchingPassword(String matchingPassword) {
    this.matchingPassword = matchingPassword;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
