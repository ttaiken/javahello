package hello.entity;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class BaseEntity<ID> implements Serializable {
    private static final long serialVersionUID = 572652475525526409L;
    private ID id;
    private Integer version;
}
