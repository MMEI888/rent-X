package priv.thinkam.rentx.web.dao.entity;

import priv.thinkam.rentx.common.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 类别
 *
 * @author yanganyu
 * @date 2019-01-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ToString(callSuper = true)
public class Category extends BaseEntity {

	/**
	 * 类别名称
	 */
	private String name;

	/**
	 * 类别描述
	 */
	private String description;

	/**
	 * 父类别编号（0代表是根类别）
	 */
	private Integer parentId;

	/**
	 *  类别层次（只能为1或2或3）
	 */
	private Integer level;

	/**
	 * 状态(1:启用, 0:禁用)
	 */
	private Boolean status;


}