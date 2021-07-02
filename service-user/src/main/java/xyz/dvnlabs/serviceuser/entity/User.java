package xyz.dvnlabs.serviceuser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Table(name = "USER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;
    @Email
    private String email;
    private String fName;
    private String password;
    private String profilePhoto;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
}
