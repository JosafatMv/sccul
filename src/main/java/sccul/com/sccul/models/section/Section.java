package sccul.com.sccul.models.section;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sccul.com.sccul.models.course.Course;

@Entity
@Table(name = "sections")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", nullable = false, columnDefinition = "int")
    private Integer number;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "video", nullable = false, length = 255)
    private String video;

    @Column(name = "duration", nullable = false)
    private String duration;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
