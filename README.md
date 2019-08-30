# Manager

Equipment Manager module for service facility API

### Technologies

 * Spring Boot
 * JPA
 * Hibernate
 * MySQL
 * Spring Security
 * JWT
 
### Instalation
 
 1. Make sure you have installed JDK 1.8 or higher
 2. Create your local instance of MySQL database
 3. Open ``/src/main/resources/application.properties`` and fill Spring DATASOURCE section about MySQL database instance
 4. Make sure everything is OK and run tests ``/src/test/java``
 5. For running application open terminal, go to root directory and type ``mvn spring-boot:run`` (default application port is 5000)
 
### Endpoints

	* GET /api/equipments
	* GET /api/equipments/{id}
	* POST /api/equipments/new
	* PUT /api/equipments/{id}
	* DELETE /api/equipments/{id}
	* GET /api/equipments/{id}/comments
	* POST /api/equipments/{id}/comments/new
	* GET /api/categories
	* GET /api/parameters/titles
	* POST /api/auth/singup
	* POST /api/auth/singin
	* GET /api/user/checkUsernameAvailability
	* GET /api/user/checkEmailAvailability
	