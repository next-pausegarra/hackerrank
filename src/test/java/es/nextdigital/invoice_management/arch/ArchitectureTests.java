package es.nextdigital.invoice_management.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class ArchitectureTests extends BaseArchTest {

	@ArchTest
	static final ArchRule services_should_reside_in_application_or_infrastructure = classes().that()
		.areAnnotatedWith(Service.class)
		.should()
		.resideInAnyPackage(APPLICATION_PACKAGE, INFRASTRUCTURE_PACKAGE);

	@ArchTest
	static final ArchRule dtos_should_reside_in_application_or_infrastructure = classes().that()
		.haveSimpleNameEndingWith(DTO)
		.or()
		.haveSimpleNameEndingWith(REQUEST)
		.or()
		.haveSimpleNameEndingWith(RESPONSE)
		.or()
		.haveSimpleNameEndingWith(COMMAND)
		.or()
		.haveSimpleNameEndingWith(QUERY)
		.should()
		.resideInAnyPackage(APPLICATION_PACKAGE, INFRASTRUCTURE_PACKAGE);

	@ArchTest
	static final ArchRule utils_should_reside_in_application_or_infrastructure = classes().that()
		.haveSimpleNameEndingWith(UTILS)
		.should()
		.resideInAnyPackage(APPLICATION_PACKAGE, INFRASTRUCTURE_PACKAGE);

}
