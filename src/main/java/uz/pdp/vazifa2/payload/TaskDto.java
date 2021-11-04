package uz.pdp.vazifa2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    @NotNull(message = "name can not be empty")
    private String name;

    private String text;

    private String solution;

    private String hint;

    @NotNull(message = "method can not be empty")
    private String method;

    private Boolean hasStar = false;

    private Integer languageId;

}
