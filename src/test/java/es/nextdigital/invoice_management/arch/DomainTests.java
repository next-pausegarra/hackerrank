package es.nextdigital.invoice_management.arch;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;

import static com.tngtech.archunit.core.domain.JavaModifier.ABSTRACT;
import static com.tngtech.archunit.core.domain.JavaModifier.FINAL;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

@AnalyzeClasses(packages = BaseArchTest.BASE_PACKAGE, importOptions = { ImportOption.DoNotIncludeTests.class })
public class DomainTests extends BaseArchTest {

	@ArchTest
	static final ArchRule domain_should_not_depend_on_any_other_layer = noClasses().that()
		.resideInAPackage(DOMAIN_PACKAGE)
		.should()
		.dependOnClassesThat()
		.resideInAnyPackage(REST_PACKAGE, INFRASTRUCTURE_PACKAGE, APPLICATION_PACKAGE);

	@ArchTest
	static final ArchRule entities_should_be_abstract_or_final = classes().that()
		.areAnnotatedWith(Entity.class)
		.or()
		.areAnnotatedWith(MappedSuperclass.class)
		.or()
		.areAnnotatedWith(Embeddable.class)
		.should()
		.haveModifier(FINAL)
		.orShould()
		.haveModifier(ABSTRACT);

	@ArchTest
	static final ArchRule all_fields_in_entities_should_be_private_and_final = fields().that()
		.areDeclaredInClassesThat()
		.areAnnotatedWith(Entity.class)
		.or()
		.areDeclaredInClassesThat()
		.areAnnotatedWith(MappedSuperclass.class)
		.or()
		.areDeclaredInClassesThat()
		.areAnnotatedWith(Embeddable.class)
		.should()
		.beFinal()
		.andShould()
		.bePrivate();

	static final ArchCondition<JavaClass> haveANamedConstructor = new ArchCondition<JavaClass>(
			"have a named constructor") {
		@Override
		public void check(JavaClass item, ConditionEvents events) {
			if (item.getMethods().stream().noneMatch(method -> method.getName().equals("create"))) {
				String message = String.format("Domain model %s should have a named constructor", item.getName());
				events.add(SimpleConditionEvent.violated(item, message));
			}
		}
	};

	@ArchTest
	static final ArchRule domain_entities_should_have_a_named_constructor = classes().that()
		.areAnnotatedWith(Entity.class)
		.should(haveANamedConstructor);

	@ArchTest
	static final ArchRule repositories_in_domain_layer_should_be_interfaces = classes().that()
		.resideInAPackage(DOMAIN_PACKAGE)
		.and()
		.haveSimpleNameEndingWith(REPOSITORY)
		.should()
		.beInterfaces();

}
