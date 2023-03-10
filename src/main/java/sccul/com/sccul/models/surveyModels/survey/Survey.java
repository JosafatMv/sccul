package sccul.com.sccul.models.surveyModels.survey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.surveyModels.questions.Question;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "surveys")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "survey")
    @JsonIgnore
    private Set<Course> courses;

    @OneToMany(mappedBy = "survey")
    @JsonIgnore
    private Set<Question> questions;

}
