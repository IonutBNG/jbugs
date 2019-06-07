package bug.validation;

import exeptions.BusinessException;
import exeptions.ExceptionMessageCatalog;
import utils.BaseDto;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Stateless
public class BugValidator {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    public void validateBean(BaseDto baseDto) throws BusinessException {
        Set<ConstraintViolation<BaseDto>> violations = validator.validate(baseDto);
        for (ConstraintViolation<BaseDto> v : violations) {
            String constraintErrorMessage = v.getMessage();
            switch (constraintErrorMessage) {

                case(ValidationMessageCatalog.INVALID_TITLE):
                    throw new BusinessException(ExceptionMessageCatalog.BUG_INVALID_TITLE);
                case(ValidationMessageCatalog.INVALID_DESCRIPTION):
                    throw new BusinessException(ExceptionMessageCatalog.BUG_INVALID_DESCRIPTION);
                case(ValidationMessageCatalog.INVALID_VERSION):
                    throw new BusinessException(ExceptionMessageCatalog.BUG_INVALID_VERSION);
                case(ValidationMessageCatalog.INVALID_DATE):
                    throw new BusinessException(ExceptionMessageCatalog.BUG_INVALID_DATE);
                case(ValidationMessageCatalog.INVALID_SEVERITY):
                    throw new BusinessException(ExceptionMessageCatalog.BUG_INVALID_SEVERITY);
                case(ValidationMessageCatalog.INVALID_USER):
                    throw new BusinessException(ExceptionMessageCatalog.BUG_INVALID_USER);


            }
        }
    }
}
