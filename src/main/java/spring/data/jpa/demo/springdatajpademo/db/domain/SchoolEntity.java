package spring.data.jpa.demo.springdatajpademo.db.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "school")
public class SchoolEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    // mappedBy ... StudentEntity内のschoolフィールドからマッピング"される"
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private Set<StudentEntity> students = new HashSet<>();

}
