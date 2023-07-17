package spring.data.jpa.demo.springdatajpademo.api.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CharacterRequest {
  private String name;
  private CharacterInfoRequest characterInfo;
}
