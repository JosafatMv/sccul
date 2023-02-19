package sccul.com.sccul.controllers.bankCard.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import sccul.com.sccul.models.bank_card.BankCard;
import sccul.com.sccul.models.user.User;

@AllArgsConstructor
@Getter
@Setter
public class BankCardDto{
    public BankCardDto(){
        this.status = true;
    }

    private Long id;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 16,min = 16, message = "El Numero de la tarjeta debe tener 16 caracteres")
    private String cardnumber;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 8,min = 8, message = "El Numero de expiracion debe tener 10 caracteres")
    private String cardexpiration;
    
    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 3,min = 3, message = "El Numero de cvv debe tener 3 caracteres")
    private String cardcvv;

    private User user ;

    private Boolean status;

    public BankCard castToBankCard() {
        return new BankCard(getId(),getCardnumber(),getUser().getName(),getCardexpiration(),getCardcvv(),getStatus(),getUser());
    }
}