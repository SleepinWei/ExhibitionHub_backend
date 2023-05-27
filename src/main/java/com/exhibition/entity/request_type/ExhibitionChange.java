package com.exhibition.entity.request_type;

import com.exhibition.entity.ExhibitionReview;
import com.exhibition.entity.Tag;
import lombok.Data;

import java.util.List;

@Data
@Deprecated
public class ExhibitionChange extends ExhibitionReview {
    private List<Tag> tags;
}
