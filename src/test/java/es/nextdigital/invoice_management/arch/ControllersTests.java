package es.nextdigital.invoice_management.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class ControllersTests extends BaseArchTest {

	@ArchTest
	static final ArchRule controllers_should_not_be_accessed_by_any_other_layer = classes().that()
		.resideInAPackage(REST_PACKAGE)
		.should()
		.onlyBeAccessed()
		.byAnyPackage(REST_PACKAGE);

	@ArchTest
	static final ArchRule controllers_should_reside_in_rest_layer = classes().that()
		.areAnnotatedWith(RestController.class)
		.or()
		.areAnnotatedWith(Controller.class)
		.or()
		.haveSimpleNameEndingWith(CONTROLLER)
		.should()
		.resideInAPackage(REST_PACKAGE);

	@ArchTest
	static final ArchRule rest_controllers_should_return_response_entities = methods().that()
		.areDeclaredInClassesThat()
		.areAnnotatedWith(RestController.class)
		.and()
		.arePublic()
		.should()
		.haveRawReturnType(ResponseEntity.class);

}
