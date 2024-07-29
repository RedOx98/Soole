package com.ecobank.soole.payload.bus;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateRouteDTO {
    // @NotBlank(message = "Captain Id must not be blank")
    private String captainId;

    // @NotBlank(message = "Route name must not be blank")
    // private String routeName;
}