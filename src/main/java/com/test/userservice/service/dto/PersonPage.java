package com.test.userservice.service.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PersonPage {

  List<PersonDto> people;
  long totalElement;
  int totalPages;

}
