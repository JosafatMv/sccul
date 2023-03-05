package sccul.com.sccul.models.surveyModels.questions;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.surveyModels.answer.Answer;
import sccul.com.sccul.models.surveyModels.survey.Survey;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private Set<Answer> answers;

}
