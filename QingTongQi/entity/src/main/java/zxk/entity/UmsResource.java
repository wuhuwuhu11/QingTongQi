package zxk.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import zxk.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源表
 * </p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UmsResource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public UmsResource(String name, Integer type, Integer level, Long parentId, String frontUrl, String backUrl) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.parentId = parentId;
        this.frontUrl = frontUrl;
        this.backUrl = backUrl;
    }

    /**
     * 资源名称
     */
    private String name;

    /**
     * 1目录 0按钮
     */
    private Integer type;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 上级id,顶级为0
     */
    private Long parentId;

    /**
     * 前端地址
     */
    private String frontUrl;

    /**
     * 后端地址
     */
    private String backUrl;

    /**
     * 子权限
     *
     * */
    @TableField(exist = false)
    private List<UmsResource> children = new ArrayList<UmsResource>();
}
