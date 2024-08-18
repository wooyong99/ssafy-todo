package com.example.hober.todo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoCreateDto {

    @NotBlank
    String content;

}
