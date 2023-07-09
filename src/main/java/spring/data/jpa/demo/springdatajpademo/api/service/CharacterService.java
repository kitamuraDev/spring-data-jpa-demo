package spring.data.jpa.demo.springdatajpademo.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.data.jpa.demo.springdatajpademo.api.controller.request.CharacterRequest;
import spring.data.jpa.demo.springdatajpademo.db.domain.CharacterEntity;
import spring.data.jpa.demo.springdatajpademo.db.repository.CharacterRepository;

@Service
@RequiredArgsConstructor
public class CharacterService {

  private final CharacterRepository characterRepo;
  private final ModelMapper modelMapper;

  // 登録
  public void saveCharacter(CharacterRequest request) {
    CharacterEntity characterEntity = modelMapper.map(request, CharacterEntity.class);

    // SpringのDIコンテナが自動でsaveメソッドにトランザクションを張ってくれるので、
    // save()実行後、SQLが自動生成されて実行される。
    characterRepo.save(characterEntity);
  }

}
