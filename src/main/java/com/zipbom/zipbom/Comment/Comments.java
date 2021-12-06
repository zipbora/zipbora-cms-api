package com.zipbom.zipbom.Comment;

import lombok.Getter;
import java.util.List;
import javax.persistence.*;
@Embeddable
@Getter
public class Comments {
    @OneToMany(mappedBy = "board",  cascade = CascadeType.PERSIST)
    private List<Comment> comments;
}
