package vladciunel.videogamecharacterapispring.dto;


import java.util.UUID;

public record VideoGameCharacterResponse(UUID id, String name, String game,String role) {}