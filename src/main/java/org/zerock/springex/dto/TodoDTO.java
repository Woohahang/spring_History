package org.zerock.springex.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDTO {

    private Long tno;

    @NotEmpty // 반드시 데이터를 넣겠다. 낫널이다!
    private String title;

    private LocalDate dueDate;

    private String writer;

    private boolean finished;

}
