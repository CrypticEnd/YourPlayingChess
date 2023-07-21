package com.cryptic.ypc.model.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("Guest")
@ToString
@Getter
@Setter
public class GuestUser extends User {
}
