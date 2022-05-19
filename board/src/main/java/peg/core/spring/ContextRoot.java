package peg.core.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ContextAspect.class, ContextCommon.class, ContextDataSource.class, ContextMapper.class, ContextTransaction.class, ContextValidator.class})
public class ContextRoot {

}
