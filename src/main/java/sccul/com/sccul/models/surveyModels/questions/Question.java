package sccul.com.sccul.models.surveyModels.questions;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import sccul.com.sccul.models.surveyModels.survey.Survey;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import sccul.com.sccul.models.surveyModels.user_answer.UserAnswer;

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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Survey survey;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    private Set<UserAnswer> userAnswers;
}
