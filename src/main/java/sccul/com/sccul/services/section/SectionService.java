package sccul.com.sccul.services.section;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.course.CourseRepository;
import sccul.com.sccul.models.score.Score;
import sccul.com.sccul.models.section.Section;
import sccul.com.sccul.models.section.SectionRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.util.List;

@Service
@Transactional
public class SectionService {
    @Autowired
    private SectionRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Section> insert(Section section){
        return new CustomResponse<Section>(
                this.repository.saveAndFlush(section),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Section>> getByCourse(long id){
       /* if (!this.courseRepository.existsById(id)){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "el curso no existe"
                    );
        }*/
        return new CustomResponse<List<Section>>(
                this.repository.findByCourse(id).get(),
                false,
                200,
                "ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Section>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }
}
