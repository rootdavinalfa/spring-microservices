package xyz.dvnlabs.servicehr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private String id;
    private String userName;
    private String division;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
}
