package sccul.com.sccul.services.bankCard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sccul.com.sccul.models.bank_card.BankCard;
import sccul.com.sccul.models.bank_card.BankCardRepository;
import sccul.com.sccul.utils.CustomResponse;

@Service
@Transactional
public class BankCardService {
    @Autowired
    private BankCardRepository repository;

    //Get all (No creo que se ocupe un get all alv pero ahi va por que no tengo otra cosa que hacer)
    @Transactional(readOnly = true)
    public CustomResponse<List<BankCard>> getAll(){
        return new CustomResponse<List<BankCard>>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    //Get all by user id
    @Transactional(readOnly = true)
    public CustomResponse<List<BankCard>> getAllByUserId(Long userId){

        if(!this.repository.existsByUserId(userId)){
            return new CustomResponse<>(
                    null,
                    false,
                    400,
                    "No existe el usuario con ese id"
            );
        }

        return new CustomResponse<List<BankCard>>(
                this.repository.findAllByUserId(userId),
                false,
                200,
                "Ok"
        );
    }

    //Get one by user id
    @Transactional(readOnly = true)
    public CustomResponse<BankCard> getOneByUserId(Long userId, Long id){
        if(!this.repository.existsByUserId(userId)){
            return new CustomResponse<BankCard>(
                null,
                false,
                400,
                "No existe el usuario con ese id"
            );
        }



        if(!this.repository.existsByIdAndUserId(id, userId)){
            return new CustomResponse<BankCard>(
                null,
                false,
                400,
                "No existe una tarjeta con ese id para ese usuario"
            );
        }

        return new CustomResponse<BankCard>(
            this.repository.findByUserIdAndId(userId, id),
            false,
            200,
            "Ok"
        );
    }

    //Get one by id
    @Transactional(readOnly = true)
    public CustomResponse<BankCard> getOne(Long id){
        if(!this.repository.existsById(id)){
            return new CustomResponse<BankCard>(
                null,
                false,
                200,
                "Ok"
        );
        }
        return new CustomResponse<BankCard>(
            this.repository.findById(id).get(),
            false,
            200,
            "Ok"
        );
    }
    
    //Insert
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<BankCard> insert(BankCard bankCard){
        if (this.repository.existsByCardNumber(bankCard.getCardNumber())){
            return new CustomResponse<BankCard>(
                null,
                true,
                400,
                "Tarjeta ya registrada"
            );
        }
        return new CustomResponse<BankCard>(
            this.repository.saveAndFlush(bankCard),
            false,
            201,
            "La tarjeta fue registrada correctamente"
        );
    }
    //Update
    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<BankCard> update(Long id, BankCard bankCard){
        if(!this.repository.existsById(id)){
            return new CustomResponse<BankCard>(
                null,
                true,
                404,
                "La tarjeta no existe"
            );
        }
        bankCard.setId(id);
        return new CustomResponse<BankCard>(
            this.repository.saveAndFlush(bankCard),
            false,
            200,
            "La tarjeta fue actualizada correctamente"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public CustomResponse<Integer> changeStatus(BankCard bankCard){
        if(!this.repository.existsById(bankCard.getId())){
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "Tarjeta inexistente"
            );
        }

        return new CustomResponse<>(
                this.repository.updateStatusById(bankCard.getStatus(), bankCard.getId()),
                false,
                200,
                "Status de la tarjeta actualizado correctamente"
        );

    }

}
