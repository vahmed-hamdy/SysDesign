package com.interviews.systemdesign.sysdesign.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "pairs")
public class Pair implements Serializable {

    private static final long serialVersionUID = 5560221391479816650L;

    @Id
    private String key;

    private Long val;



}
