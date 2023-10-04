package com.rion.rionojbackendmodel.model.codesandbox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 *
 * @author Rion
 * @date: 2023/9/26
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecuteCodeReps {

    private List<String> outputList;

    private String message;

    private Integer status;

    private JudgeInfo judgeInfo;
}
