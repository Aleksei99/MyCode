package com.smuraha.mycode.dto;

import com.smuraha.mycode.dao.model.CodeSample;
import com.smuraha.mycode.dao.model.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeSampleDto {
    private CodeSample sample;
    private String contentArea;
    private Section section;
}
