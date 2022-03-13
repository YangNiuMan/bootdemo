package selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author yang
 */
public class DynamicDataSourceRegisterImportSelector implements ImportSelector {

    public DynamicDataSourceRegisterImportSelector(){}


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"register.DynamicDataSourceRegister"};
    }
}
