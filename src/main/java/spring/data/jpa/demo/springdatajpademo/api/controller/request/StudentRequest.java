package spring.data.jpa.demo.springdatajpademo.api.controller.request;

import lombok.Data;

@Data
public class StudentRequest {
  private Integer id;
  private String name;
}
