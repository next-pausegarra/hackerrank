package es.nextdigital.invoice_management.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class ApplicationTests extends BaseArchTest {

	@ArchTest
	static final ArchRule application_classes_should_not_be_accessed_from_domain = classes().that()
		.resideInAPackage(APPLICATION_PACKAGE)
		.should()
		.onlyBeAccessed()
		.byClassesThat()
		.resideOutsideOfPackage(DOMAIN_PACKAGE);

	@ArchTest
	static final ArchRule application_classes_should_not_depend_on_infrastructure = noClasses().that()
		.resideInAPackage(APPLICATION_PACKAGE)
		.should()
		.dependOnClassesThat()
		.resideInAnyPackage(REST_PACKAGE, INFRASTRUCTURE_PACKAGE);

	@ArchTest
	static final ArchRule methods_in_application_services_should_have_transactional_annotation = methods().that()
		.areDeclaredInClassesThat()
		.areAnnotatedWith(Service.class)
		.and()
		.areDeclaredInClassesThat()
		.resideInAPackage(APPLICATION_PACKAGE)
		.and()
		.areDeclaredInClassesThat()
		.haveSimpleNameNotEndingWith(VALIDATOR)
		.and()
		.arePublic()
		.and()
		.areNotStatic()
		.and()
		.areNotAnnotatedWith(Value.class)
		.should()
		.beAnnotatedWith(Transactional.class);

}
