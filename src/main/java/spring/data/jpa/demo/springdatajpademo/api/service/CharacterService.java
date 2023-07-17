package spring.data.jpa.demo.springdatajpademo.api.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import spring.data.jpa.demo.springdatajpademo.api.controller.request.CharacterRequest;
import spring.data.jpa.demo.springdatajpademo.api.controller.response.CharacterResponse;
import spring.data.jpa.demo.springdatajpademo.db.domain.CharacterEntity;
import spring.data.jpa.demo.springdatajpademo.db.domain.CharacterInfoEntity;
import spring.data.jpa.demo.springdatajpademo.db.repository.CharacterInfoRepository;
import spring.data.jpa.demo.springdatajpademo.db.repository.CharacterRepository;

@Service
@RequiredArgsConstructor
public class CharacterService {

  private final CharacterRepository characterRepo;
  private final CharacterInfoRepository characterInfoRepo;
  private final ModelMapper modelMapper;

  /**
   * 登録
   *
   * @param request リクエストボディ（レコード一行分）
   */
  @Transactional
  public void create(CharacterRequest request) {
    CharacterEntity characterEntity = modelMapper.map(request, CharacterEntity.class);

    // save Character
    CharacterEntity savedCharacterEntity = characterRepo.save(characterEntity);

    // save CharacterInfo
    CharacterInfoEntity characterInfoEntity = characterEntity.getCharacterInfo();
    characterInfoEntity.setCharacter(savedCharacterEntity);
    characterInfoRepo.save(characterInfoEntity);
  }

  /**
   * 更新
   *
   * @param id ユニークid
   * @param request リクエストボディ（レコード一行分）
   */
  @Transactional
  public void update(Integer id, CharacterRequest request) {
    CharacterEntity characterEntity = characterRepo.findCharacter(id)
      .orElseThrow(() -> new RuntimeException("NOT FOUND CHARACTER"));

    modelMapper.map(request, characterEntity);
  }

  /**
   * 全件取得
   *
   * @return List<CharacterResponse> テーブルのレコード全件
   */
  public List<CharacterResponse> findAllCharacters() {
    List<CharacterEntity> characterEntities = characterRepo.findAllCharacters();
    return modelMapper.map(characterEntities, new TypeToken<List<CharacterResponse>>() {
    }.getType());
  }

  /**
   * 単件取得
   *
   * @param id ユニークid
   * @return CharacterResponse テーブルのレコード単件
   */
  public CharacterResponse findCharacter(Integer id) {
    CharacterEntity characterEntity = characterRepo.findCharacter(id)
      .orElseThrow(() -> new RuntimeException("NOT FOUND CHARACTER"));

    return modelMapper.map(characterEntity, CharacterResponse.class);
  }

  /**
   * 削除
   *
   * @param id ユニークid
   */
  // public void deleteCharacter(Integer id) {
  //   CharacterEntity characterEntity = characterRepo.findById(id)
  //     .orElseThrow(() -> new RuntimeException("NOT FOUND CHARACTER"));

  //   characterRepo.delete(characterEntity);
  // }

  /**
   * 名前キーワードによる曖昧検索
   *
   * @param name nameカラム
   * @return List<CharacterResponse> テーブルのレコード複数件（ヒットしない場合は空の配列）
   */
  // public List<CharacterResponse> findCharacterByName(String name) {
  //   List<CharacterEntity> characterEntities = StreamSupport.stream(characterRepo.findByName(name)
  //     .orElseThrow(() -> new RuntimeException("NOT FOUND CHARACTER")).spliterator(), false)
  //     .collect(Collectors.toList());

  //   return characterEntities.stream().map(e -> modelMapper.map(e, CharacterResponse.class))
  //     .collect(Collectors.toList());
  // }

}
