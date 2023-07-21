package com.cryptic.ypc.model.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("Registered")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisteredUser extends User {
	private String password;
}
