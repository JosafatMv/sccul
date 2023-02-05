package sccul.com.sccul.models.survey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.answer_survey.AnswerSurvey;
import sccul.com.sccul.models.course.Course;

import java.util.Set;

@Entity
@Table(name = "surveys")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long survey_id;

    @Column(name = "question", nullable = false, length = 255)
    private String question;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "survey")
    private Set<AnswerSurvey> answer_surveys;

}
