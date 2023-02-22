package sccul.com.sccul.services.score;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sccul.com.sccul.models.course.Course;
import sccul.com.sccul.models.score.Score;
import sccul.com.sccul.models.score.ScoreRepository;
import sccul.com.sccul.models.user.User;
import sccul.com.sccul.utils.CustomResponse;

@Service
@Transactional
public class ScoreService {
    @Autowired
    private ScoreRepository repository;
    //Get by course
    @Transactional(readOnly = true)
    public CustomResponse<List<Score>> getByCourse(Course curso) {
        return new CustomResponse<List<Score>>(
            this.repository.findByCourse(curso).get(),
            false,
            200,
            "Ok"
    );
    }

    //Get Promedio de curso
    @Transactional(readOnly = true)
    public CustomResponse<Double> getAverageScore(Long curso) {
        return new CustomResponse<Double>(
            this.repository.getAverageScore(curso),
            false,
            200,
            "Ok"
    );
    }
    //Get by user and course
    @Transactional(readOnly = true)
    public CustomResponse<Score> getByUserAndCourse(User user, Course curso) {
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
        return new CustomResponse<Score>(
                this.repository.saveAndFlush(score),
                false,
                200,
                "Ok"
        );
    }
}
