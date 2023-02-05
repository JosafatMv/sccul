package sccul.com.sccul.models.answer_survey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.survey.Survey;
import sccul.com.sccul.models.user.User;

@Entity
@Table(name = "answer_survey")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerSurvey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answer_survey_id;

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "answer", nullable = false, length = 255)
    private String answer;

    @Column(name = "date", nullable = false, columnDefinition = "datetime default current_timestamp")
    private String date;
}
