package io.bootify.audit_test.model;

import java.util.UUID;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthorDTO {

    private UUID id;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

}
