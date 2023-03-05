package sccul.com.sccul.models.surveyModels.answer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.surveyModels.questions.Question;

@Entity
@Table(name = "answers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "answer", nullable = false, length = 255)
    private String answer;

    //@Column(name = "date", nullable = false, columnDefinition = "datetime default current_timestamp")
    //private String date;
}
