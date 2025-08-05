package es.nextdigital.invoice_management.arch;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import jakarta.persistence.Entity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class NamingConventionTests extends BaseArchTest {

	@ArchTest
	static final ArchRule application_services_should_not_be_suffixed = classes().that()
		.areAnnotatedWith(Service.class)
		.or()
		.resideInAPackage(APPLICATION_PACKAGE)
		.should()
		.haveSimpleNameNotEndingWith(SERVICE)
		.andShould()
		.haveSimpleNameNotEndingWith(SERVICE_IMPL);

	@ArchTest
	static final ArchRule jpa_repository_implementations_should_be_suffixed = classes().that()
		.areAssignableTo(JpaRepository.class)
		.or()
		.areAssignableTo(CrudRepository.class)
		.or()
		.areAssignableTo(PagingAndSortingRepository.class)
		.should()
		.haveSimpleNameEndingWith(REPOSITORY);

	@ArchTest
	static final ArchRule controllers_should_be_suffixed = classes().that()
		.areAnnotatedWith(RestController.class)
		.or()
		.areAnnotatedWith(Controller.class)
		.should()
		.haveSimpleNameEndingWith(CONTROLLER);

	@ArchTest
	static final ArchRule config_should_be_suffixed = classes().that()
		.areAnnotatedWith(Configuration.class)
		.and()
		.areNotAnnotatedWith(ConfigurationProperties.class)
		.should()
		.haveSimpleNameEndingWith(CONFIG);

	@ArchTest
	static final ArchRule config_properties_should_be_suffixed = classes().that()
		.areAnnotatedWith(ConfigurationProperties.class)
		.should()
		.haveSimpleNameEndingWith(CONFIG_PROPERTIES);

	@ArchTest
	static final ArchRule domain_entities_should_not_be_suffixed = classes().that()
		.areAnnotatedWith(Entity.class)
		.should()
		.haveSimpleNameEndingWith(ENTITY)
		.andShould()
		.haveSimpleNameNotEndingWith(MODEL);

}
