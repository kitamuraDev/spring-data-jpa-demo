package spring.data.jpa.demo.springdatajpademo.api.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import spring.data.jpa.demo.springdatajpademo.api.controller.request.CharacterRequest;
import spring.data.jpa.demo.springdatajpademo.api.controller.response.CharacterResponse;
import spring.data.jpa.demo.springdatajpademo.db.domain.CharacterEntity;
import spring.data.jpa.demo.springdatajpademo.db.repository.CharacterRepository;

@Service
@RequiredArgsConstructor
public class CharacterService {

  private final CharacterRepository characterRepo;
  private final ModelMapper modelMapper;

  /**
   * 登録
   *
   * @param request リクエストボディ（レコード一行分）
   */
  public void saveCharacter(CharacterRequest request) {
    CharacterEntity characterEntity = modelMapper.map(request, CharacterEntity.class);

    // SpringのDIコンテナが自動でsaveメソッドにトランザクションを張ってくれるので、
    // save()実行後、SQLが自動生成されて実行される。
    characterRepo.save(characterEntity);
  }

  /**
   * 更新
   * @Transactional で明示的にトランザクションを張ることも可能
   *
   * @param id ユニークid
   * @param request リクエストボディ（レコード一行分）
   */
  @Transactional
  public void updateCharacter(Integer id, CharacterRequest request) {
    // findById() でDBにSELECTを発行
    // レコードが存在すればそのレコードを格納。存在しなければ例外を投げる
    CharacterEntity characterEntity = characterRepo.findById(id)
      .orElseThrow(() -> new RuntimeException("NOT FOUND CHARACTER"));

    // request と characterEntity をマッピング（更新）
    modelMapper.map(request, characterEntity);
  }

  /**
   * 削除
   *
   * @param id ユニークid
   */
  public void deleteCharacter(Integer id) {
    CharacterEntity characterEntity = characterRepo.findById(id)
      .orElseThrow(() -> new RuntimeException("NOT FOUND CHARACTER"));

    characterRepo.delete(characterEntity);
  }

  /**
   * 全件取得
   *
   * @return List<CharacterResponse> テーブルのレコード全件
   */
  public List<CharacterResponse> findAllCharacter() {
    List<CharacterEntity> characterEntities = StreamSupport.stream(characterRepo.findAll().spliterator(), false)
      .collect(Collectors.toList());

    return characterEntities.stream().map(e -> modelMapper.map(e, CharacterResponse.class))
      .collect(Collectors.toList());
  }

  /**
   * 単件取得
   *
   * @param id ユニークid
   * @return CharacterResponse テーブルのレコード単件
   */
  public CharacterResponse findCharacterById(Integer id) {
    CharacterEntity characterEntity = characterRepo.findById(id)
      .orElseThrow(() -> new RuntimeException("NOT FOUND CHARACTER"));

    return modelMapper.map(characterEntity, CharacterResponse.class);
  }

  /**
   * 名前キーワードによる曖昧検索
   *
   * @param name nameカラム
   * @return List<CharacterResponse> テーブルのレコード複数件（ヒットしない場合は空の配列）
   */
  public List<CharacterResponse> findCharacterByName(String name) {
    List<CharacterEntity> characterEntities = StreamSupport.stream(characterRepo.findByName(name)
      .orElseThrow(() -> new RuntimeException("NOT FOUND CHARACTER")).spliterator(), false)
      .collect(Collectors.toList());

    return characterEntities.stream().map(e -> modelMapper.map(e, CharacterResponse.class))
      .collect(Collectors.toList());
  }

}
