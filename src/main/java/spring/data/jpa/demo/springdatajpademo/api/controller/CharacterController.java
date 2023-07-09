package spring.data.jpa.demo.springdatajpademo.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import spring.data.jpa.demo.springdatajpademo.api.controller.request.CharacterRequest;
import spring.data.jpa.demo.springdatajpademo.api.service.CharacterService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {

  private final CharacterService characterService;

  @PostMapping
  public void create(@RequestBody CharacterRequest request) {
    characterService.saveCharacter(request);
  }

}
