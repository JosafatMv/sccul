package sccul.com.sccul.models.inscription;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.user.User;

@Entity
@Table(name = "inscriptions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    @Column(name = "full_percentage", nullable = false, columnDefinition = "double")
    private Double fullPercentage;

    @Column(name = "status", nullable = false, length = 20)
    private String status;
}
