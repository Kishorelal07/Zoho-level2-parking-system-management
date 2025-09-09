package com.example.cycle.parking.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotEmpty(message = "Name cannot be Empty")
    @Size(min=5, max = 20, message = "Name characters size should be between 5 and 20")
    private String name;

    @NotEmpty(message = "Email cannot be Empty or Null")
    @Email(message = "Email address should be valid")
    private String email;

    @NotEmpty(message = "MobileNumber cannot be Empty or Null")
    @Pattern(regexp = "($|[0-9]{10})",message = "Mobile Number Must be 10 digit")
    private String MobileNumber;
}
