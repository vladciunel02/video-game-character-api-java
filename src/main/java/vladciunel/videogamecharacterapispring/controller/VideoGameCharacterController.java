package vladciunel.videogamecharacterapispring.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vladciunel.videogamecharacterapispring.dto.VideoGameCharacterRequest;
import vladciunel.videogamecharacterapispring.dto.VideoGameCharacterResponse;
import vladciunel.videogamecharacterapispring.model.VideoGameCharacter;
import vladciunel.videogamecharacterapispring.service.VideoGameCharacterService;

import java.util.List;
import java.util.UUID;
import java.net.URI;
@RestController
@RequestMapping("/api/characters")
public class VideoGameCharacterController {
    private final VideoGameCharacterService videoGameCharacterService;
    public VideoGameCharacterController(VideoGameCharacterService videoGameCharacterService) {
        this.videoGameCharacterService = videoGameCharacterService;
    }
    @GetMapping
    public ResponseEntity<List<VideoGameCharacterResponse>> getAllCharacters() {
        return ResponseEntity.ok(videoGameCharacterService.getAllCharacters());
    }
    @GetMapping("/{id}")
    public ResponseEntity<VideoGameCharacterResponse> getCharacterById(@PathVariable UUID id) {
        return videoGameCharacterService.getCharacterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<VideoGameCharacterResponse> createCharacter(@RequestBody @Valid VideoGameCharacterRequest character) {
        VideoGameCharacterResponse createdCharacter = videoGameCharacterService.createCharacter(character);
        URI location = URI.create("/api/characters/" + createdCharacter.id());
        return ResponseEntity.created(location).body(createdCharacter);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCharacter(@PathVariable UUID id, @RequestBody @Valid VideoGameCharacterRequest character) {
        if(videoGameCharacterService.updateCharacter(id, character)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable UUID id) {
        if(videoGameCharacterService.deleteCharacter(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
