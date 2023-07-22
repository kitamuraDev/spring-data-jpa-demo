package spring.data.jpa.demo.springdatajpademo.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.data.jpa.demo.springdatajpademo.api.controller.request.SchoolRequest;
import spring.data.jpa.demo.springdatajpademo.api.controller.response.SchoolResponse;
import spring.data.jpa.demo.springdatajpademo.api.service.SchoolService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schools")
public class CharacterController {

  private final SchoolService schoolService;

  @PostMapping
  public void create(@RequestBody SchoolRequest request) {
    schoolService.create(request);
  }

  @PutMapping("{id}")
  public void update(@PathVariable("id") Integer id, @RequestBody SchoolRequest request) {
    schoolService.update(id, request);
  }

  @GetMapping
  public List<SchoolResponse> getAllCharacters() {
    return schoolService.findAllSchools();
  }

  @GetMapping("{id}")
  public SchoolResponse getCharacter(@PathVariable("id") Integer id) {
    return schoolService.findSchool(id);
  }

  // @DeleteMapping("{id}")
  // public void delete(@PathVariable("id") Integer id) {}

  // @GetMapping("/find")
  // public List<CharacterResponse> findCharacterByName(@RequestParam("name") String name) {}

}
