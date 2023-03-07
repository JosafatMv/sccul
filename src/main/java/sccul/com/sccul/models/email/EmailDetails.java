package sccul.com.sccul.models.email;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
}
