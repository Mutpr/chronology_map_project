package com.board.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String name;
    private String nickname;
}
