package annoation;

import org.springframework.context.annotation.Import;
import selector.DynamicDataSourceRegisterImportSelector;

import java.lang.annotation.*;

/**
 * @author yang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({DynamicDataSourceRegisterImportSelector.class})
public @interface EnableAutoChangeDatasource {
}
