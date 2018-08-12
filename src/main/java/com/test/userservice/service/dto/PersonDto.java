package com.test.userservice.service.dto;

import com.test.userservice.util.exception.UserServiceException;
import java.time.LocalDate;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

  private String personId;
  private String name;
  private int age;
  LocalDate birthDate;
  String email;


  public void validate() {
    StringBuilder sb = new StringBuilder();
    if (StringUtils.isBlank(this.name)) {
      sb.append("Name must have value\n");
    }
    if (Objects.isNull(birthDate)) {
      sb.append("Birth date must have value\n");
    }
    if (Objects.isNull(this.age)) {
      sb.append("Age must have value\n");
    }
    if (StringUtils.isBlank(this.email)) {
      sb.append("Email must not be empty\n");
    }

    if (sb.length() > 0) {
      throw new UserServiceException(sb.toString(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

  }

}
