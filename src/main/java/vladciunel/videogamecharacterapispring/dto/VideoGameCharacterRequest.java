package vladciunel.videogamecharacterapispring.dto;

import jakarta.validation.constraints.NotBlank;


public record VideoGameCharacterRequest(@NotBlank String name, @NotBlank String game, @NotBlank String role) {}
