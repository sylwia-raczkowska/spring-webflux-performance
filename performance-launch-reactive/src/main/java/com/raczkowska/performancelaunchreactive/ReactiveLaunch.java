package com.raczkowska.performancelaunchreactive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Document(collection = "launch")
class ReactiveLaunch {
     String name;
     String description;



}
