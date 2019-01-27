package priv.thinkam.rentx.web.service;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.baidu.unbiz.fluentvalidator.registry.Registry;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import priv.thinkam.rentx.common.base.Response;
import priv.thinkam.rentx.common.enums.EnableEnum;
import priv.thinkam.rentx.web.dao.entity.Category;
import priv.thinkam.rentx.web.dao.mapper.CategoryMapper;
import priv.thinkam.rentx.web.service.param.CategoryParam;

import javax.annotation.Resource;

/**
 * 类别 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> implements IService<Category> {
	@Resource
	private javax.validation.Validator hibernateValidator;
	@Resource
	private Registry springApplicationContextRegistry;

	/**
	 * whether category name exists
	 *
	 * @param name category name
	 * @return true: exists
	 */
	public boolean existsName(String name) {
		return this.count(
				new QueryWrapper<Category>().lambda()
						.eq(Category::getName, name)
						.eq(Category::getMark, EnableEnum.YES.getValue())
		) > 0;
	}

	/**
	 * whether category name equals {id} and level not equals {level}
	 *
	 * @param id    category id
	 * @param level category level
	 * @return true: exists
	 */
	public boolean existsLevelNotEquals(Integer id, int level) {
		return this.count(
				new QueryWrapper<Category>().lambda()
						.eq(Category::getId, id)
						.ne(Category::getParentId, level)
						.eq(Category::getMark, EnableEnum.YES.getValue())
		) > 0;
	}

	/**
	 * 添加类别
	 *
	 * @param categoryParam categoryParam
	 * @return priv.thinkam.rentx.common.base.Response
	 * @author yanganyu
	 * @date 1/27/19 1:33 PM
	 */
	public Response add(CategoryParam categoryParam) {
		Result result = FluentValidator.checkAll()
				.on(categoryParam, new HibernateSupportedValidator<CategoryParam>()
						.setHiberanteValidator(hibernateValidator))
				.configure(springApplicationContextRegistry)
				.on(categoryParam)
				.doValidate()
				.result(ResultCollectors.toSimple());
		if (!result.isSuccess()) {
			log.info("add category invalid param: {}", result.getErrors());
			return Response.fail(result.getErrors().get(0));
		}
		Category category = new Category()
				.setName(categoryParam.getName())
				.setDescription(categoryParam.getDescription())
				.setParentId(categoryParam.getParentId())
				.setStatus(categoryParam.getStatus())
				.setLevel(categoryParam.getLevel());
		this.save(category);
		log.info("a category saved: {}", category);
		return Response.SUCCESS;
	}
}
