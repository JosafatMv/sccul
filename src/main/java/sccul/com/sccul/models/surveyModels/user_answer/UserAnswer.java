package sccul.com.sccul.models.surveyModels.user_answer;

import jakarta.persistence.*;
import lombok.*;

import sccul.com.sccul.models.surveyModels.questions.Question;

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

    @Column(name = "answer",nullable = false)
    private int answer;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}
