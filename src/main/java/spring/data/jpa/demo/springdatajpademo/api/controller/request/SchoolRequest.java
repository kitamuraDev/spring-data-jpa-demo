package spring.data.jpa.demo.springdatajpademo.api.controller.request;

import java.util.Set;

import lombok.Data;

@Data
public class SchoolRequest {
  private String name;
  private Set<StudentRequest> students;
}
