package com.example.apirestbooks.responses.libro;

import com.example.apirestbooks.responses.ResponseRest;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LibroResponseRest extends ResponseRest {
    private LibroResponse libroResponse = new  LibroResponse();
}
