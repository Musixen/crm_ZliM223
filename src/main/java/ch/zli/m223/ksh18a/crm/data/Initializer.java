package ch.zli.m223.ksh18a.crm.data;

import java.util.ArrayList;
import java.util.List;

import ch.zli.m223.ksh18a.crm.model.AppCustomer;
import ch.zli.m223.ksh18a.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ch.zli.m223.ksh18a.crm.repository.UserRepository;
import ch.zli.m223.ksh18a.crm.role.AppRoles;

@Component
public class Initializer implements ApplicationRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CustomerService customerService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {

		List<String> admin = new ArrayList<>();
		admin.add(AppRoles.admin);
		userRepository.insertUser("admin", "admin", admin);
		
		List<String> user = new ArrayList<>();
		user.add(AppRoles.user);
		userRepository.insertUser("user", "user", user);
		
		List<String> usemin = new ArrayList<>();
		usemin.add(AppRoles.user);
		usemin.add(AppRoles.admin);
		userRepository.insertUser("usmin", "usemin",usemin);

		AppCustomer c0, c1, c2;
		c0 = customerService.addCustomer("Altenburger Noah", "Lorenweg 14", "8630 Rüti ZH");
		c1 = customerService.addCustomer("Reiniger Felix", "Kaffstrasse 12", "8853 Lachen");
		c2 = customerService.addCustomer("Brodmann Ciro", "Heimstrasse 41", "5417 Untersiggenthal");

		customerService.addMemoToCustomer(c0.getId(),
				"Schüler, Geht auf die Kantonsschule Hottinge, Weiss nicht was schreiben"
		);
		customerService.addMemoToCustomer(c1.getId(),
				"Schüler, Geht auf die Kantonsschule Hottinge, Weiss nicht was schreiben"
		);
		customerService.addMemoToCustomer(c2.getId(),
				"Schüler, Geht auf die Kantonsschule Hottinge, Weiss nicht was schreiben"
		);
	}

}
