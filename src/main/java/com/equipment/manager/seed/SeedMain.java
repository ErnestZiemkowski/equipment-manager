package com.equipment.manager.seed;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.equipment.manager.model.Category;
import com.equipment.manager.model.Comment;
import com.equipment.manager.model.Equipment;
import com.equipment.manager.model.Parameter;
import com.equipment.manager.model.ParameterTitle;
import com.equipment.manager.model.Role;
import com.equipment.manager.model.RoleName;
import com.equipment.manager.model.Specification;
import com.equipment.manager.model.User;
import com.equipment.manager.repository.CategoryRepository;
import com.equipment.manager.repository.CommentRepository;
import com.equipment.manager.repository.EquipmentRepository;
import com.equipment.manager.repository.ParameterTitleRepository;
import com.equipment.manager.repository.RoleRepository;
import com.equipment.manager.repository.UserRepository;
import com.github.javafaker.Faker;

@Component
public class SeedMain {
	
	private static final Logger logger = LoggerFactory.getLogger(SeedMain.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private EquipmentRepository equipmentRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ParameterTitleRepository parameterTitleRepository;
	
	private Faker faker = new Faker(new Locale("en-GB")); 
		
	private Role roleAdmin = new Role();
	private Role roleUser = new Role();
	
	private User admin = new User();
	private User userAdam = new User();
	private User userEve = new User();

	
	private Category laptopCategory = new Category("Laptops");
	private Category smartphoneCategory = new Category("Smartphones");
	private Category pcCategory = new Category("PC");
	private Category audioCategory = new Category("Audio");
	private Category tvCategory = new Category("TV");

	private Equipment smartTV = new Equipment("Thin Ultra", faker.lorem().characters(150, 249, true), true, "tv.jpg");
	private Equipment smartphone = new Equipment("Smartphone", faker.lorem().characters(150, 249, true), true, "samsung-galaxy.jpg");
	private Equipment pc = new Equipment("PC", faker.lorem().characters(150, 249, true), true, "pc.jpg");
	private Equipment audio = new Equipment("JBL", faker.lorem().characters(150, 249, true), true, "jbl.jpg");
	private Equipment laptop = new Equipment("Lenovo Thinkpad", faker.lorem().characters(150, 249, true), true, "jbl.jpg");
		
	private ParameterTitle parameterTitleProcessor = new ParameterTitle("Processor");
	private ParameterTitle parameterTitleRamMemory = new ParameterTitle("RAM Memory");
	private ParameterTitle parameterTitleBuildMemory = new ParameterTitle("Build Memory");
	private ParameterTitle parameterTitleScreenType = new ParameterTitle("Screen Type");
	private ParameterTitle parameterTitleScreenDiagonal = new ParameterTitle("Screen Diagonal");
	private ParameterTitle parameterTitleResolution = new ParameterTitle("Resolution");
	private ParameterTitle parameterTitleConnectivity = new ParameterTitle("Connectivity");
	private ParameterTitle parameterTitleSateliteNavigation = new ParameterTitle("Satelite Navigation");
	private ParameterTitle parameterTitleConnectors = new ParameterTitle("Connectors");
	private ParameterTitle parameterTitleBattery = new ParameterTitle("Battery");	
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		seedRolesTable();
		seedUsersTable();
		seedCategoriesTable();
		seedEquipmentsTable();
		seedCommentsTable();
		seedParameterTitlesTable();
	}
	
	private void seedRolesTable() {
		List<Role> roles = roleRepository.findAll();
		
		if (roles.size() <= 0 || roles == null) {
			
			roleAdmin.setName(RoleName.ROLE_ADMIN);
			roleUser.setName(RoleName.ROLE_USER);
		
			roleRepository.saveAll(Arrays.asList(roleAdmin, roleUser));
		} else {
            logger.info("Users Seeding Not Required");
        }
	}
	
	private void seedUsersTable() {
		List<User> users = userRepository.findAll();
		
		if (users.size() <= 0 || users == null) {	
						
			admin.setEmail("admin@manager.com");
			admin.setUsername("admin");
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			admin.addRole(roleUser);
			admin.addRole(roleAdmin);
			
			userAdam.setEmail("adam@manager.com");
			userAdam.setUsername("adam");
			userAdam.setPassword(new BCryptPasswordEncoder().encode("adam"));
			userAdam.addRole(roleUser);
			
			userEve.setEmail("eve@manager.com");
			userEve.setUsername("eve");
			userEve.setPassword(new BCryptPasswordEncoder().encode("eve"));
			userEve.addRole(roleUser);
						
			userRepository.saveAll(Arrays.asList(admin, userAdam, userEve));
		} else {
            logger.info("Users Seeding Not Required");
        }
	}
	
	private void seedCategoriesTable() {
		List<Category> categories = categoryRepository.findAll();
		
		if (categories.size() <= 0 || categories == null) {
			
			categoryRepository.saveAll(Arrays.asList(laptopCategory, smartphoneCategory, pcCategory, audioCategory, tvCategory));
		} else {
            logger.info("Users Seeding Not Required");
        }
	}
	
	private void seedEquipmentsTable() {
		List<Equipment> equipments = equipmentRepository.findAll();
		
		if (equipments.size() <= 0 || equipments == null) {
						
			Specification smartTvSpecification = new Specification();
			Specification smartphoneSpecification = new Specification();
			Specification pcSpecification = new Specification();
			Specification audioSpecification = new Specification();
			Specification latopSpecification = new Specification();
			
			smartTV.setCategory(tvCategory);
			smartTV.setSpecification(smartTvSpecification);
			smartTvSpecification.setEquipment(smartTV);
			
			smartphone.setCategory(smartphoneCategory);
			smartphone.setSpecification(smartphoneSpecification);
			smartphoneSpecification.setEquipment(smartphone);
			
			pc.setCategory(pcCategory);
			pc.setSpecification(pcSpecification);
			pcSpecification.setEquipment(pc);
			
			audio.setCategory(audioCategory);
			audio.setSpecification(audioSpecification);
			audioSpecification.setEquipment(audio);
			
			laptop.setCategory(laptopCategory);
			laptop.setSpecification(latopSpecification);
			latopSpecification.setEquipment(laptop);
			
			equipmentRepository.saveAll(Arrays.asList(smartTV, smartphone, pc, audio, laptop));
		} else {
            logger.info("Users Seeding Not Required");
        }
	}
	
	private void seedCommentsTable() {
		List<Comment> comments = commentRepository.findAll();
		
		if (comments.size() <= 0 || comments == null) {
			
			Comment commentTvFirst = new Comment(faker.lorem().characters(100, 149, true), userAdam, smartTV);
			Comment commentTvSecond = new Comment(faker.lorem().characters(100, 149, true), userEve, smartTV);

			Comment commentSmartphoneFirst = new Comment(faker.lorem().characters(100, 149, true), userAdam, smartphone);
			Comment commentSmartphoneSecond = new Comment(faker.lorem().characters(100, 149, true), userAdam, smartphone);

			Comment commentPcFirst = new Comment(faker.lorem().characters(100, 149, true), userAdam, pc);
			Comment commentPcSecond = new Comment(faker.lorem().characters(100, 149, true), userAdam, pc);

			Comment commentAudioFirst = new Comment(faker.lorem().characters(100, 149, true), userAdam, audio);
			Comment commentAudioSecond = new Comment(faker.lorem().characters(100, 149, true), userAdam, audio);

			Comment commentLaptopFirst = new Comment(faker.lorem().characters(100, 149, true), userAdam, laptop);
			Comment commentLaptopSecond = new Comment(faker.lorem().characters(100, 149, true), userAdam, laptop);
			
			commentRepository.saveAll(Arrays.asList(
				commentTvFirst,
				commentTvSecond,
				commentSmartphoneFirst,
				commentSmartphoneSecond,
				commentPcFirst,
				commentPcSecond,
				commentAudioFirst,
				commentAudioSecond,
				commentLaptopFirst,
				commentLaptopSecond
			));
		} else {
            logger.info("Users Seeding Not Required");
        }	
	}
	
	private void seedParameterTitlesTable() {
		List<ParameterTitle> parameterTitles = parameterTitleRepository.findAll();
		
		if (parameterTitles.size() <= 0 || parameterTitles == null) {
			
			parameterTitleRepository.saveAll(Arrays.asList(
					parameterTitleProcessor,
					parameterTitleRamMemory,
					parameterTitleBuildMemory,
					parameterTitleScreenType,
					parameterTitleScreenDiagonal,
					parameterTitleResolution,
					parameterTitleConnectivity,
					parameterTitleSateliteNavigation,
					parameterTitleConnectors,
					parameterTitleBattery
			));
			
		} else {
            logger.info("Parameters Seeding Not Required");
        }
	}

}

