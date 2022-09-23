package ro.fasttrackit.vehicleprovider;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import ro.fasttrackit.vehicleprovider.model.Vehicle;
import ro.fasttrackit.vehicleprovider.repository.VehicleRepository;

import java.util.List;

@SpringBootApplication
@EnableWebSocket
public class VehicleProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleProviderApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4444")
						.allowCredentials(true)
						.allowedMethods("GET", "POST", "PUT","PATCH", "DELETE");
			}
		};
	}
}

@Component
@RequiredArgsConstructor
class InitialDataLoader implements CommandLineRunner{

	private final VehicleRepository repository;

	@Override
	public void run(String... args) throws Exception {
		long countVehicles = repository.count();
		if(countVehicles == 0) {
			repository.saveAll(List.of(
					Vehicle.builder().vin("WDB9630201L719339").make("AUDI").model("A4").year(2020).owner("Audi Owner").build(),
					Vehicle.builder().vin("WV1ZZZ2HZCA067579").make("MERCEDES").model("GLE").year(2020).owner("Mercedes Owner").build(),
					Vehicle.builder().vin("VF1FLAHA68Y270945").make("BMW").model("X3").year(2020).owner("BMW Owner").build(),
					Vehicle.builder().vin("ZCFC50A2005785877").make("FIAT").model("TIPO").year(2020).owner("Fiat Owner").build(),
					Vehicle.builder().vin("WDB906657DS784278").make("OPEL").model("ASTRA").year(2020).owner("Opel Owner").build(),
					Vehicle.builder().vin("WDB906657AS465367").make("FORD").model("FOCUS").year(2020).owner("Ford Owner").build()
			));
		}
	}
}


