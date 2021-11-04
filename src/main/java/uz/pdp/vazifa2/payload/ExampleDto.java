package uz.pdp.vazifa2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDto {

    @NotNull
    private String text;

    private Integer taskId;

}
