package com.neu.azuresql.pojo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "")
public class People implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Name")
    private String name;

    private String state;

    private Integer salary;

    private Integer grade;

    private Integer room;

    private Integer telnum;

    private String picture;

    private String keywords;

}
