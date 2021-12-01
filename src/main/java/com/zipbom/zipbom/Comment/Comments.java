package com.zipbom.zipbom.Comment;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

public class Comments {

    @OneToMany(mappedBy = "user",  cascade = CascadeType.PERSIST)
    private List<Comment> comments;

}
