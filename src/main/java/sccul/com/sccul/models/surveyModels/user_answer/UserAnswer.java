package sccul.com.sccul.models.surveyModels.user_answer;

import jakarta.persistence.*;
import lombok.*;
import sccul.com.sccul.models.surveyModels.answer.Answer;
import sccul.com.sccul.models.user.User;

@Entity
@Table(name = "user_answers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private Answer answer;
}
