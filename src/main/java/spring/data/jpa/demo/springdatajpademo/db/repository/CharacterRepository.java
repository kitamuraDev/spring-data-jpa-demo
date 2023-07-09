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

  // 自分でクエリを定義することも可能（nameカラムによる曖昧検索）
  // nativeQuery: ネイティブSQLクエリを使用できることを示す
  @Query(value = "SELECT * FROM character WHERE name LIKE %:name%", nativeQuery = true)
  Optional<List<CharacterEntity>> findByName(@Param("name") String name);

}
