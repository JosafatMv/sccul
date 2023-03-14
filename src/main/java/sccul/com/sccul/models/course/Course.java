package sccul.com.sccul.models.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import sccul.com.sccul.models.category.Category;
import sccul.com.sccul.models.comment.Comment;
import sccul.com.sccul.models.inscription.Inscription;
import sccul.com.sccul.models.score.Score;
import sccul.com.sccul.models.section.Section;
import sccul.com.sccul.models.surveyModels.survey.Survey;
import sccul.com.sccul.models.surveyModels.user_answer.UserAnswer;

import java.util.Set;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @Column(name = "image", nullable = false, length = 255)
    private String image;

    @Column(name = "price", nullable = false, columnDefinition = "double")
    private Double price;

    @Column(name = "discount", columnDefinition = "double")
    private Double discount;

    @Column(name = "status", nullable = false, columnDefinition = "tinyint default 1", insertable = false, updatable = false)
    private Integer status;

    @Formula("(select count(*) from scores s where s.course_id = id)")
    private Integer totalRatings;

    @Formula("(select avg(s.score) from scores s where s.course_id = id)")
    private Double averageRatings;

    @Formula("(select count(*) from inscriptions i where i.course_id = id and i.status = 'comprado')")
    private Integer totalParticipants;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "course")
    private Set<Section> sections;

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @OneToMany(mappedBy = "course")
    private Set<Score> scores;

    @OneToMany(mappedBy = "course")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "course")
    private Set<Inscription> inscriptions;

    @OneToMany(mappedBy = "course")
    private Set<UserAnswer> userAnswers;

}
