package com.wuxianggujun.mvc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PathInfo �洢 http �����Ϣ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathInfo {
    /**
     * http ���󷽷�
     */
    private String httpMethod;

    /**
     * http ����·��
     */
    private String httpPath;
}
