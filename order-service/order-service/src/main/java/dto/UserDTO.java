package dto;

import lombok.Data;

@Data // Generates Getters, Setters, etc.
public class UserDTO {
    private Long id;
    private String name;
    private String email;
}
