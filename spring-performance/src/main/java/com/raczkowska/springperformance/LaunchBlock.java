package com.raczkowska.springperformance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "launch")
class LaunchBlock {
    @Id
    @EqualsAndHashCode.Include
    private String id;
    private String name;
    private String description;
}
