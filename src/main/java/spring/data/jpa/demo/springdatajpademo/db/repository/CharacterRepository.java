package spring.data.jpa.demo.springdatajpademo.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.data.jpa.demo.springdatajpademo.db.domain.CharacterEntity;

@Repository
/**
 * CrudRepository<CharacterEntity, Integer>
 *  第一引数：操作対象のEntityクラス
 *  第二引数：主キーのデータ型
 */
public interface CharacterRepository extends CrudRepository<CharacterEntity, Integer> {

  /**
   * `@Query(value = nativeQuery=true)`: 生SQLの記述を許可する（しかし、以下のメリットがあるJPQLでクエリを記述することが推奨される）
   *  - DBの移植性
   *  - DBに依存しない
   *  - OOPとの相性の良さ
   *  - 抽象的にクエリが書ける
   */

  /**
   * - DISTINCT: 重複する結果を削除する
   * - JOIN FETCH: 関連するエンティティを取得するための構文（今回の場合、関連するエンティティはCharacterEntity.characterInfo）
   * - CharacterEntityクラスのidと引数（:id）が一致するエンティティを取得する
   */
  @Query(value =
    "SELECT DISTINCT character FROM CharacterEntity character "
    + "JOIN FETCH character.characterInfo WHERE character.id = :id"
  )
  public Optional<CharacterEntity> findCharacter(@Param("id") Integer id);

  @Query(value =
    "SELECT DISTINCT character FROM CharacterEntity character "
    + "JOIN FETCH character.characterInfo"
  )
  public List<CharacterEntity> findAllCharacters();

}
