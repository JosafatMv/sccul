package sccul.com.sccul.models.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.answer_survey.AnswerSurvey;
import sccul.com.sccul.models.bank_card.BankCard;
import sccul.com.sccul.models.comment.Comment;
import sccul.com.sccul.models.inscription.Inscription;
import sccul.com.sccul.models.score.Score;

import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "surname", nullable = false, length = 45)
    private String surname;

    @Column(name = "lastname", length = 45)
    private String lastname;

    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "phone_number", nullable = false, length = 20, unique = true)
    private String phoneNumber;

    @Column(name = "role", nullable = false, length = 30)
    private String role;

    @Column(name = "image", length = 255)
    private String image;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint default 1")
    private Boolean status;

    @OneToMany(mappedBy = "user")
    private Set<AnswerSurvey> answerSurveys;

    @OneToMany(mappedBy = "user")
    private Set<BankCard> bankCards;

    @OneToMany(mappedBy = "user")
    private Set<Score> scores;

    @OneToMany(mappedBy = "user")
    private Set<Inscription> inscriptions;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

}
