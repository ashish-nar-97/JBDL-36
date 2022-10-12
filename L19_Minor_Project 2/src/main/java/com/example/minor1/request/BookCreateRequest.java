package com.example.minor1.request;

import com.example.minor1.model.Author;
import com.example.minor1.model.Book;
import com.example.minor1.model.Genre;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookCreateRequest {

    @NotBlank
    private String name;

    @Positive
    private int cost;

    @NotNull
    private Genre genre;

    @NotNull
    private Author author;

    public Book to(){
        return Book.builder()
                .cost(this.cost)
                .genre(this.genre)
                .name(this.name)
                .author(this.author)
                .build();
    }

}
