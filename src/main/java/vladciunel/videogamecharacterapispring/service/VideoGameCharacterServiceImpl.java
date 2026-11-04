package vladciunel.videogamecharacterapispring.service;

import org.springframework.stereotype.Service;
import vladciunel.videogamecharacterapispring.dto.VideoGameCharacterRequest;
import vladciunel.videogamecharacterapispring.dto.VideoGameCharacterResponse;
import vladciunel.videogamecharacterapispring.model.VideoGameCharacter;
import vladciunel.videogamecharacterapispring.repository.VideoGameCharacterRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class VideoGameCharacterServiceImpl implements VideoGameCharacterService{
   private final VideoGameCharacterRepository videoGameCharacterRepository;
   public VideoGameCharacterServiceImpl(VideoGameCharacterRepository videoGameCharacterRepository) {
        this.videoGameCharacterRepository = videoGameCharacterRepository;
    }
    private VideoGameCharacterResponse mapToResponse(VideoGameCharacter character) {
        return new VideoGameCharacterResponse(character.getId(), character.getName(), character.getGame(), character.getRole());
    }
    @Override
    public List<VideoGameCharacterResponse> getAllCharacters() {
        List<VideoGameCharacterResponse> characters = videoGameCharacterRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
        return characters;
    }

    @Override
    public Optional<VideoGameCharacterResponse> getCharacterById(UUID id) {
        return videoGameCharacterRepository.findById(id)
                .map(this::mapToResponse);
    }

    @Override
    public VideoGameCharacterResponse createCharacter(VideoGameCharacterRequest request) {
        VideoGameCharacter character = new VideoGameCharacter();
        character.setName(request.name());
        character.setGame(request.game());
        character.setRole(request.role());
        VideoGameCharacter savedCharacter = videoGameCharacterRepository.save(character);
        return mapToResponse(savedCharacter);
    }

    @Override
    public boolean deleteCharacter(UUID id) {
        if (videoGameCharacterRepository.existsById(id)) {
            videoGameCharacterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCharacter(UUID id, VideoGameCharacterRequest request) {
        Optional<VideoGameCharacter> optionalCharacter = videoGameCharacterRepository.findById(id);
        if (optionalCharacter.isPresent()) {
            VideoGameCharacter character = optionalCharacter.get();
            character.setName(request.name());
            character.setGame(request.game());
            character.setRole(request.role());
            videoGameCharacterRepository.save(character);
            return true;
        }
        return false;
    }
}
