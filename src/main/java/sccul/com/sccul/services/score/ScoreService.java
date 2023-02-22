package sccul.com.sccul.services.score;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.course.CourseRepository;
import sccul.com.sccul.models.score.Score;
import sccul.com.sccul.models.score.ScoreRepository;
import sccul.com.sccul.models.user.User;
import sccul.com.sccul.models.user.UserRepository;
import sccul.com.sccul.utils.CustomResponse;

@Service
@Transactional
public class ScoreService {
    @Autowired
    private ScoreRepository repository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    //Get by course
    @Transactional(readOnly = true)
    public CustomResponse<List<Score>> getByCourse(Course course) {

        if (!this.courseRepository.existsById(course.getId())) {
            return new CustomResponse<List<Score>>(
                    null,
                    true,
                    400,
                    "El curso no existe"
            );
        }

        return new CustomResponse<List<Score>>(
                this.repository.findByCourse(course).get(),
                false,
                200,
                "Ok"
        );
    }

    //Get Promedio de curso
    @Transactional(readOnly = true)
    public CustomResponse<Double> getAverageScore(Long courseId) {

        if (!this.courseRepository.existsById(courseId)) {
            return new CustomResponse<Double>(
                    null,
                    true,
                    400,
                    "El curso no existe"
            );
        }

        return new CustomResponse<Double>(
                this.repository.getAverageScore(courseId),
                false,
                200,
                "Ok"
        );
    }

    //Get by user and course
    @Transactional(readOnly = true)
    public CustomResponse<Score> getByUserAndCourse(User user, Course curso) {

        if(!this.courseRepository.existsById(curso.getId())) {
            return new CustomResponse<Score>(
                    null,
                    true,
                    400,
                    "El curso no existe"
            );
        }

        if(!this.userRepository.existsById(user.getId())) {
            return new CustomResponse<Score>(
                    null,
                    true,
                    400,
                    "El usuario no existe"
            );
        }

        return new CustomResponse<Score>(
                this.repository.findByUserAndCourse(user, curso).get(),
                false,
                200,
                "Ok"
        );
    }

    //insert score
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Score> insert(Score score) {
        if (this.repository.getCount(score.getUser().getId(), score.getCourse().getId()) > 0) {
            return new CustomResponse<Score>(
                    null,
                    true,
                    400,
                    "Ya existe un puntaje para este usuario y curso"
            );
        }

        if (!this.courseRepository.existsById(score.getCourse().getId())) {
            return new CustomResponse<Score>(
                    null,
                    true,
                    400,
                    "El curso no existe"
            );
        }

        if (!this.userRepository.existsById(score.getUser().getId())) {
            return new CustomResponse<Score>(
                    null,
                    true,
                    400,
                    "El usuario no existe"
            );
        }

        return new CustomResponse<Score>(
                this.repository.saveAndFlush(score),
                false,
                200,
                "Ok"
        );
    }
}
