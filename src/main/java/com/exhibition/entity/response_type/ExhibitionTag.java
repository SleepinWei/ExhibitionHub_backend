package com.exhibition.entity.response_type;

import com.exhibition.entity.Exhibition;
import com.exhibition.entity.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExhibitionTag extends Exhibition {

    public List<Tag> tag_list;
}
