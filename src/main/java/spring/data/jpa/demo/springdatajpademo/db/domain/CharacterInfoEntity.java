package spring.data.jpa.demo.springdatajpademo.db.domain;

import java.time.LocalDate;
import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "character_info")
public class CharacterInfoEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "birth_date") // DBのカラム名とフィールド変数名をマッピング
  private LocalDate birthDate;

  private String gender;

  // CharacterEntityのcharacterInfoフィールドとマッピングする
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  // CharacterInfoテーブルのカラム「character_id」をJOINカラムとして定義
  @JoinColumn(name = "character_id", unique = true, nullable = true, updatable = true)
  private CharacterEntity character;

}
