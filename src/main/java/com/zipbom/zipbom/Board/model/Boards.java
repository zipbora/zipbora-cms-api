package com.zipbom.zipbom.Board.model;

import com.zipbom.zipbom.Comment.Comments;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
@Getter
public class Boards {
    @OneToMany(mappedBy = "user",  cascade = CascadeType.PERSIST)
    private List<Board> boards;

    @Embedded
    private Comments comments;
}
