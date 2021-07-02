package xyz.dvnlabs.servicehr.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String email;
    private String fName;
    private String password;
    private String profilePhoto;
    private Date createdOn;
}
