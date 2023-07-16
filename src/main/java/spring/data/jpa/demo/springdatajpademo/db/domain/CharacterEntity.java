package spring.data.jpa.demo.springdatajpademo.db.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "character")
public class CharacterEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  // mappedBy ... CharacterInfoEntity内のCharacterフィールドからマッピングされる
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true, mappedBy = "character")
  private CharacterInfoEntity characterInfo;

}
