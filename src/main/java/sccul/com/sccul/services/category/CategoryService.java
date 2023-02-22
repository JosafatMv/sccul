package sccul.com.sccul.services.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sccul.com.sccul.models.category.Category;
import sccul.com.sccul.models.category.CategoryRepository;
import sccul.com.sccul.utils.CustomResponse;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    //Get all
    @Transactional(readOnly = true)
    public CustomResponse<List<Category>> getAll() {
        return new CustomResponse<List<Category>>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //Get one by id
    @Transactional(readOnly = true)
    public CustomResponse<Category> getOne(long id) {

        //If the category doesn't exist
        if (!this.repository.existsById(id)) {
            return new CustomResponse<Category>(
                    null,
                    true,
                    404,
                    "La categoría no existe"
            );
        }

        return new CustomResponse<Category>(
                this.repository.findById(id).get(),
                false,
                200,
                "Ok"
        );
    }

    //Insert
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Category> insert(Category category) {
        if (this.repository.existsByName(category.getName())) {
            return new CustomResponse<Category>(
                    null,
                    true,
                    400,
                    "Ya existe una categoría con ese nombre"
            );
        }
        return new CustomResponse<Category>(
                this.repository.saveAndFlush(category),
                false,
                201,
                "La categoría fue registrada correctamente"
        );
    }

    //Update
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Category> update(long id, Category category) {
        if (!this.repository.existsById(id)) {
            return new CustomResponse<Category>(
                    null,
                    true,
                    400,
                    "La categoría no existe"
            );
        }

        if (this.repository.existsByNameAndIdNot(category.getName(), id)) {
            return new CustomResponse<Category>(
                    null,
                    true,
                    400,
                    "Ya existe una categoría con ese nombre"
            );
        }

        //Asignamos el id que viene por parámetro para que actualice y no cree otro
        category.setId(id);
        return new CustomResponse<Category>(
                this.repository.saveAndFlush(category),
                false,
                200,
                "La categoría fue modificada correctamente"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Integer> changeStatus(Category category) {
        ///nombre de la categoría no se repite
        if (!this.repository.existsById(category.getId())){
            return new CustomResponse<>(
                    null, true, 400,
                    "La categoría no existe"
            );
        }

        return new CustomResponse<>(
                this.repository.updateStatusById(category.getStatus(), category.getId()),
                false, 200,
                "Categoría actualizada correctamente"

        );
    }


}
