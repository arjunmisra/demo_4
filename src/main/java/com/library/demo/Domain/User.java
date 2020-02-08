package com.library.demo.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;


@Data
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String name;
    int userId;
    ArrayList<Book> reservedBooks = new ArrayList<>();
    ArrayList<Book> issuedBooks = new ArrayList<>();

}
