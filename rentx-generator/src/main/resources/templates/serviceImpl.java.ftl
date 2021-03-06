package ${package.ServiceImpl};

import lombok.extern.slf4j.Slf4j;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import com.baomidou.mybatisplus.extension.service.IService;
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

/**
 * ${table.comment!} service
 *
 * @author ${author}
 * @date ${date}
 */
@Slf4j
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements IService<${entity}> {

}
</#if>
