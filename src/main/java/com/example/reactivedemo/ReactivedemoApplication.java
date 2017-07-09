package com.example.reactivedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.reactivedemo.repository.DummyPersonRepository;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import java.util.stream.Collectors;


@SpringBootApplication
public class ReactivedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivedemoApplication.class, args);
	}
	
	@Autowired
	DummyPersonRepository dummyPersonRepository;
	
	
    @Bean
    RouterFunction<?> routerFunction(DummyPersonRepository dummyPersonRepository) {
        return route(
        		GET("/movies"),
        		request->ok().body(dummyPersonRepository.allPeople().map(p->p.getName()).collect(Collectors.joining(",")),String.class))
        				;
//                .andRoute(GET("/movies/{id}"), rh::byId)
//                .andRoute(GET("/movies/{id}/events"), rh::events)
//                .andRoute(GET("/users/{username}"), uh::byUsername)
//                .andRoute(GET("/users/me"), uh::current);
    }
}
