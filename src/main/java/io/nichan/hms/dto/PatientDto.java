package io.nichan.hms.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private Long patient_id;
    @NotEmpty
    private String first_name;
    @NotEmpty
    private String last_name;
    @NotEmpty(message = "Birth date should not be empty")
    private String birth_date;
    @NotEmpty(message = "Sex should not be empty")
    private String sex;
    @NotEmpty(message = "Phone should not be empty")
    private String phone;
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @NotEmpty(message = "Insurance info should not be empty")
    private String insurance_info;
    @NotNull(message = "Age should not be empty")
    private int age;


}
