package sccul.com.sccul.models.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.category.Category;
import sccul.com.sccul.models.comment.Comment;
import sccul.com.sccul.models.inscription.Inscription;
import sccul.com.sccul.models.score.Score;
import sccul.com.sccul.models.section.Section;
import sccul.com.sccul.models.surveyModels.survey.Survey;

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
    private String price;

    @Column(name = "discount", columnDefinition = "double")
    private Double discount;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "course")
    private Set<Section> sections;

    @OneToMany(mappedBy = "course")
    private Set<Survey> surveys;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Score> scores;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Comment> comments;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Inscription> inscriptions;

}
