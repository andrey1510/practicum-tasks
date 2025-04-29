package com.frontendservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.practicum.patterns.domain.Animal;
import ru.practicum.patterns.domain.Crop;
import ru.practicum.patterns.domain.Sale;

import java.util.List;

@Controller
public class FrontendController {
    private final WebClient webClient;

    public FrontendController(WebClient.Builder webClientBuilder) {
        String backendUrl = "http://localhost:8081";
        this.webClient = webClientBuilder.baseUrl(backendUrl).build();
    }

    @GetMapping("/dashboard")
    public String getDashboard(Model model, @CookieValue(name = "JWT", defaultValue = "") String token) {
        if (token.isEmpty()) {
            return "redirect:/login";
        }
        Mono<List<Animal>> animals = webClient.get()
            .uri("/api/animals")
            .header("Authorization", "Bearer " + token)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<>() {});

        Mono<List<Crop>> crops = webClient.get()
            .uri("/api/crops")
            .header("Authorization", "Bearer " + token)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<>() {});

        Mono<List<Sale>> sales = webClient.get()
            .uri("/api/sales")
            .header("Authorization", "Bearer " + token)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<>() {});

        model.addAttribute("animals", animals.block());
        model.addAttribute("crops", crops.block());
        model.addAttribute("sales", sales.block());

        return "dashboard";
    }
}
