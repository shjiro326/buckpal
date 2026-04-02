package io.reflectoring.buckpal;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import org.junit.jupiter.api.Test;

import com.tngtech.archunit.core.importer.ClassFileImporter;

import io.reflectoring.buckpal.archunit.HexagonalArchitecture;

class DependencyRuleTests {

	@Test
	void validateRegistrationContextArchitecture() {
		int b;
		HexagonalArchitecture.basePackage("io.reflectoring.buckpal")

				.withDomainLayer("application.domain")

				.withAdaptersLayer("adapter")
				.incoming("in.web")
				.outgoing("out.persistence")
				.and()

				.withApplicationLayer("application")
				.incomingPorts("port.in")
				.outgoingPorts("port.out")
				.and()

				.withConfiguration("configuration")
				.check(new ClassFileImporter()
						.importPackages("io.reflectoring.buckpal.."));
	}

	@Test
	void domainModelDoesNotDependOnOutside() {
		noClasses()
				.that()
				.resideInAPackage("io.reflectoring.buckpal.application.domain.model..")
				.should()
				.dependOnClassesThat()
				.resideOutsideOfPackages(
						"io.reflectoring.buckpal.application.domain.model..",
						"lombok..",
						"java.."
				)
				.check(new ClassFileImporter()
						.importPackages("io.reflectoring.buckpal.."));
	}

}
