package io.nichan.hms.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDto {
    private Long doctor_id;
    @NotEmpty
    private String first_name;
    @NotEmpty
    private String last_name;
    @NotEmpty
    private String email;
    @NotEmpty
    private String specialization;
    @NotEmpty
    private String schedule;
}
