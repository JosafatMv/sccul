package sccul.com.sccul.services.section;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sccul.com.sccul.models.category.Category;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.course.CourseRepository;
import sccul.com.sccul.models.score.Score;
import sccul.com.sccul.models.section.Section;
import sccul.com.sccul.models.section.SectionRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class SectionService {
    @Autowired
    private SectionRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Section> insert(Section section) {

        if (!this.courseRepository.existsById(section.getCourse().getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El curso no existe"
            );
        }

        List<Section> sections = this.repository.findByCourse(section.getCourse()).get();

        if (sections.size() >= 5){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "El curso no puede tener más de 5 secciones"
            );
        }

        if (this.repository.existsByNameAndCourseId(section.getName(), section.getCourse().getId())) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe una sección con ese nombre"
            );
        }


        return new CustomResponse<Section>(
                this.repository.saveAndFlush(section),
                false,
                200,
                "Ok"
        );
    }


    //update
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Section> update(long id, Section section){
        if (this.repository.existsByNameAndCourseIdAndIdNot(section.getName(), section.getCourse().getId(), id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe una sección con ese nombre"
            );
        }


        if (!this.repository.existsById(id)) {
            return new CustomResponse<Section>(
                    null,
                    true,
                    400,
                    "La sección no existe"
            );
        }


        return new CustomResponse<Section>(
                this.repository.saveAndFlush(section),
                false,
                200,
                "La sección fue modificada correctamente"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Section>> getByCourse(long id) {
        if (!this.courseRepository.existsById(id)) {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El curso no existe"
            );
        }

        Course courseToFind = new Course();
        courseToFind.setId(id);

        return new CustomResponse<List<Section>>(
                this.repository.findByCourse(courseToFind).get(),
                false,
                200,
                "OK"
        );
    }


    @Transactional(readOnly = true)
    public CustomResponse<List<Section>> getAll() {
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }


}
