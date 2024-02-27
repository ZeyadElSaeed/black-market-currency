package com.zeyad.blackmarketcurrency.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Website {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "Name shouldn't be EMPTY")
    @Size(max = 200)
    private String name;
    @NotBlank(message = "URL shouldn't be EMPTY")
    @Size(max = 200)
    private String url;
    @NotBlank(message = "XPath shouldn't be EMPTY")
    @Size(max = 200)
    private String xPath;

}
