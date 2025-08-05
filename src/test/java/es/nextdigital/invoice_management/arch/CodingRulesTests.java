package es.nextdigital.invoice_management.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.CompositeArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static com.tngtech.archunit.core.domain.JavaModifier.FINAL;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class CodingRulesTests extends BaseArchTest {

	@ArchTest
	static final ArchRule no_classes_should_throw_generic_exceptions = GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

	@ArchTest
	static final ArchRule exceptions_should_be_in_a_exceptions_package = classes().that()
		.areAssignableTo(RuntimeException.class)
		.or()
		.haveSimpleNameEndingWith(EXCEPTION)
		.should()
		.resideInAPackage(ANY_EXCEPTION_PACKAGE);

	@ArchTest
	static final ArchRule custom_exceptions_should_be_final = classes().that()
		.areAssignableTo(RuntimeException.class)
		.or()
		.resideInAPackage(ANY_EXCEPTION_PACKAGE)
		.and()
		.resideOutsideOfPackage(BASE_PACKAGE + COMMON_EXCEPTION_PACKAGE)
		.should()
		.haveModifier(FINAL);

	@ArchTest
	static final ArchRule field_injection_should_not_be_used = CompositeArchRule
		.of(GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION)
		.and(noFields().should(GeneralCodingRules.BE_ANNOTATED_WITH_AN_INJECTION_ANNOTATION))
		.and(noFields().should().beAnnotatedWith(Autowired.class))
		.and(noMethods().should().beAnnotatedWith(Autowired.class));

	@ArchTest
	static final ArchRule loggers_should_be_private_static_final = fields().that()
		.haveRawType(Logger.class)
		.should()
		.bePrivate()
		.andShould()
		.beStatic()
		.andShould()
		.beFinal();

}
