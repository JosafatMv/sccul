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

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "survey")
    private Set<Question> questions;

}
