package spring.data.jpa.demo.springdatajpademo.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import spring.data.jpa.demo.springdatajpademo.api.controller.request.SchoolRequest;
import spring.data.jpa.demo.springdatajpademo.api.controller.request.StudentRequest;
import spring.data.jpa.demo.springdatajpademo.api.controller.response.SchoolResponse;
import spring.data.jpa.demo.springdatajpademo.db.domain.SchoolEntity;
import spring.data.jpa.demo.springdatajpademo.db.domain.StudentEntity;
import spring.data.jpa.demo.springdatajpademo.db.repository.StudentRepository;
import spring.data.jpa.demo.springdatajpademo.db.repository.SchoolRepository;

@Service
@RequiredArgsConstructor
public class SchoolService {

  private final SchoolRepository schoolRepo;
  private final StudentRepository studentRepo;
  private final ModelMapper modelMapper;

  /**
   * 登録
   *
   * @param request リクエストボディ（レコード一行分）
   */
  @Transactional
  public void create(SchoolRequest request) {
    SchoolEntity requestEntity = modelMapper.map(request, SchoolEntity.class);

    // save School
    SchoolEntity savedSchoolEntity = schoolRepo.save(requestEntity);

    // save Student
    Set<StudentEntity> studentEntities = requestEntity.getStudents();
    studentEntities.forEach(student -> student.setSchool(savedSchoolEntity));
    studentRepo.saveAll(studentEntities);
  }

  /**
   * 更新
   *
   * @param id ユニークid
   * @param request リクエストボディ（レコード一行分）
   */
  @Transactional
  public void update(Integer id, SchoolRequest request) {
    SchoolEntity schoolEntity = schoolRepo.findSchool(id)
      .orElseThrow(() -> new RuntimeException("NOT FOUND SCHOOL"));

    // update school
    schoolEntity.setName(request.getName());
    schoolRepo.save(schoolEntity);

    // update students
    List<StudentEntity> studentEntities = new ArrayList<>();
    for (StudentRequest studentRequest : request.getStudents()) {
      StudentEntity studentEntity = studentRepo.findById(studentRequest.getId())
        .orElseThrow(() -> new RuntimeException("NOT FOUND STUDENT"));
      studentEntity.setName(studentRequest.getName());
      studentEntities.add(studentEntity);
    }
    studentRepo.saveAll(studentEntities);
  }

  /**
   * 全件取得
   *
   * @return List<CharacterResponse> テーブルのレコード全件
   */
  public List<SchoolResponse> findAllSchools() {
    List<SchoolEntity> schoolEntities = schoolRepo.findAllSchools();

    return modelMapper.map(schoolEntities, new TypeToken<List<SchoolResponse>>() {
    }.getType());
  }

  /**
   * 単件取得
   *
   * @param id ユニークid
   * @return CharacterResponse テーブルのレコード単件
   */
  public SchoolResponse findSchool(Integer id) {
    SchoolEntity schoolEntity = schoolRepo.findSchool(id)
      .orElseThrow(() -> new RuntimeException("NOT FOUND SCHOOL"));

    return modelMapper.map(schoolEntity, SchoolResponse.class);
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
