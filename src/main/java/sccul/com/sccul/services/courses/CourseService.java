package sccul.com.sccul.services.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sccul.com.sccul.models.category.CategoryRepository;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.course.CourseRepository;
import sccul.com.sccul.models.section.Section;
import sccul.com.sccul.models.section.SectionRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Course>> getAll(){

        List<Course> courses = this.repository.findAll();

//        courses.forEach(course -> {
//            course.setTotalRatings(this.repository.countScoresByCourseId(course.getId()));
//            course.setTotalParticipants(this.repository.totalParticipantsByCourseId(course.getId()));
//            course.setAverageRatings(this.repository.averageScoresByCourseId(course.getId()) != null ? this.repository.averageScoresByCourseId(course.getId()) : 0.0);
//        });

        return new CustomResponse<>(
                courses,
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Course> getOne(long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El curso no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Course> insert(Course course){

        try {
            if(!this.categoryRepository.existsById(course.getCategory().getId())){
                return new CustomResponse<>(
                        null,
                        true,
                        400,
                        "No existe la categoría seleccionada"
                );
            }

            if(this.repository.existsByName(course.getName())){
                return new CustomResponse<>(
                        null,
                        true,
                        400,
                        "Ya existe un curso registrado con ese nombre"
                );
            }

            Course newCourse = this.repository.save(course);

            Set<Section> sections = course.getSections();

            int count = 0;

            for(Section section : sections) {
                if(count >= 5){
                    break;
                }

                section.setCourse(newCourse);
                this.sectionRepository.save(section);
                count++;
            };

            newCourse.setSections(sections);

            return new CustomResponse<>(
                    newCourse,
                    false,
                    200,
                    "Ok"
            );
        } catch (Exception e) {
            return new CustomResponse<>(
                    null,
                    true,
                    500,
                    "Error al registrar el curso"
            );
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Course> update(Course course){
        if(!this.repository.existsById(course.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El curso no existe"
            );
        }

        if(this.repository.existsByNameAndIdNot(course.getName(), course.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "Ya existe un curso registrado con ese nombre"
            );
        }

        return new CustomResponse<>(
                this.repository.save(course),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Integer> changeDiscount(Course course){
        if(!this.repository.existsById(course.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El curso no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.updateDiscountById(course.getDiscount(), course.getId()),
                false,
                200,
                "Descuento actualizado"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Integer> changeStatus(Course course){
        if(!this.repository.existsById(course.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "El curso no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.updateStatusById(course.getStatus(), course.getId()),
                false,
                200,
                "Estado actualizado"
        );
    }


}
