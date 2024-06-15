package com.tpe.library_management_system.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@Table(name = "users")
public class  User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   /* @Column(unique = true)
    private String username;*/

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Min(value = -2)
    @Max(value = 2)
    private Integer score = 0;

    @NotNull
    @Size(min = 10, max = 100)
    private String address;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "\\d{3} \\d{3} \\d{4}")
    private String phone;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @NotNull
    @Email
    @Column(unique = true)
    @Size(min = 10, max = 80)
    private String email;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String password; // Bu alan şifrelenmiş (hashed) olmalıdır

    @NotNull
    private LocalDateTime createDate;

    private String resetPasswordCode; // Bu alan hashlenmiş olmalıdır

    @NotNull
    private Boolean builtIn = false;

    @OneToMany(mappedBy = "user")
    private List<Loan> loans;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name= "user_roles",
            joinColumns =  @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Role userRoles;



}