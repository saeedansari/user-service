package com.test.userservice.util.generator;

import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

  private static final String PREFIX = "1000";
  private static final int POST_LENGTH = 7;
  private static RandomStringGenerator generator = new RandomStringGenerator.Builder()
      .withinRange(new char[]{'0', '9'})
      .build();

  public String generatePersonId() {
    return PREFIX.concat(generator.generate(POST_LENGTH));
  }


}
