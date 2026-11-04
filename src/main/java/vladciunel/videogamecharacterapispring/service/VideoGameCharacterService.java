package vladciunel.videogamecharacterapispring.service;

import vladciunel.videogamecharacterapispring.dto.VideoGameCharacterRequest;
import vladciunel.videogamecharacterapispring.dto.VideoGameCharacterResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoGameCharacterService {
    List<VideoGameCharacterResponse> getAllCharacters();
    Optional<VideoGameCharacterResponse> getCharacterById(UUID id);
    VideoGameCharacterResponse createCharacter(VideoGameCharacterRequest request);
    boolean deleteCharacter(UUID id);
    boolean updateCharacter(UUID id, VideoGameCharacterRequest request);


}
