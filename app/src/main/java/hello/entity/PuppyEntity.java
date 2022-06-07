package hello.entity;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//import lombok.extern.slf4j.Slf4j;

import lombok.Data;


@Entity
@Table
@Data
//@Slf4j
//@TableName(value = "tbl_puppy")
public class PuppyEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
//    @NotNull
    private String name;
    private String color;
    private int age;
    public PuppyEntity(int id, String name,String color, int age){
       this.setId(id);
       this.setName(name);
       this.setColor(color);
       this.setAge(age);
    }
}
