package com.example.hober.todo.domain;

import com.example.hober.global.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo extends BaseEntity {

    @Id
    private Long id;

    private String content;

    private boolean completed;

    @Builder
    public Todo(String content){
        this.content = content;
    }

    public void updateComplete() {
        this.completed = !this.completed;
    }
}
