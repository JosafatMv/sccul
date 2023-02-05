package sccul.com.sccul.models.section;

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
    private Long section_id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "video", nullable = false, length = 255)
    private String video;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
