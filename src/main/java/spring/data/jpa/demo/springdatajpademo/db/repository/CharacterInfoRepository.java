package spring.data.jpa.demo.springdatajpademo.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.data.jpa.demo.springdatajpademo.db.domain.CharacterInfoEntity;

@Repository
public interface CharacterInfoRepository extends CrudRepository<CharacterInfoEntity, Integer> {}
