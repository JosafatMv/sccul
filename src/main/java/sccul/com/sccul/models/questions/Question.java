package sccul.com.sccul.models.questions;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.answer.Answer;
import sccul.com.sccul.models.survey.Survey;

import java.util.Set;

@Entity
@Table(name="questions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", nullable = false )
    private String question;

    @ManyToOne
    @JoinColumn(name = "survei_id", nullable = false)
    private Survey survey;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;

}
