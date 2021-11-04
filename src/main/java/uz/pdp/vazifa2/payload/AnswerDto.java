package uz.pdp.vazifa2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private String text;

    private Boolean isCorrect = false;

    private Integer taskId;

    private String userId;

}
