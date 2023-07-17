package spring.data.jpa.demo.springdatajpademo.api.controller.response;

import lombok.Data;

@Data
public class CharacterResponse {
  private Integer id;
  private String name;
  private CharacterInfoResponse characterInfo;
}
