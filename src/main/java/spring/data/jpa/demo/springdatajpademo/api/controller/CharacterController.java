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
import spring.data.jpa.demo.springdatajpademo.api.controller.request.CharacterRequest;
import spring.data.jpa.demo.springdatajpademo.api.controller.response.CharacterResponse;
import spring.data.jpa.demo.springdatajpademo.api.service.CharacterService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
public class CharacterController {

  private final CharacterService characterService;

  @PostMapping
  public void create(@RequestBody CharacterRequest request) {
    characterService.create(request);
  }

  @PutMapping("{id}")
  public void update(@PathVariable("id") Integer id, @RequestBody CharacterRequest request) {
    characterService.update(id, request);
  }

  @GetMapping
  public List<CharacterResponse> getAllCharacters() {
    return characterService.findAllCharacters();
  }

  @GetMapping("{id}")
  public CharacterResponse getCharacter(@PathVariable("id") Integer id) {
    return characterService.findCharacter(id);
  }

  // @DeleteMapping("{id}")
  // public void delete(@PathVariable("id") Integer id) {}

  // @GetMapping("/find")
  // public List<CharacterResponse> findCharacterByName(@RequestParam("name") String name) {}

}
