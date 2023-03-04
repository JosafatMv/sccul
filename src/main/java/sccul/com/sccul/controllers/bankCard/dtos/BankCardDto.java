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
    private String cardNumber;

    private String alias;

    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 5,min = 5, message = "La fecha de expiración debe tener 5 caracteres")
    private String cardExpiration;
    
    @NotEmpty(message = "Campo obligatorio")
    @Size(max = 3,min = 3, message = "El Numero de cvv debe tener 3 caracteres")
    private String cardCvv;

    private User user ;

    private Boolean status;

    public BankCard castToBankCard() {
        return new BankCard(getId(),getCardNumber(),getUser().getName(),getAlias(),getCardExpiration(),getCardCvv(),getStatus(),getUser());
    }
}