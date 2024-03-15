package com.example.askproject.Model.DTO;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SqlResultSetMapping(
    name = "CountDTO",
    classes = {
        @ConstructorResult(
            targetClass = CountDTO.class,
            columns = {
                @ColumnResult(name = "user", type = String.class),
                @ColumnResult(name = "count", type = Long.class)
            }
        )
    }
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CountDTO {
    private String user;
    private Long count;
}
