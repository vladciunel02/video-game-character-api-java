package vladciunel.videogamecharacterapispring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import vladciunel.videogamecharacterapispring.dto.VideoGameCharacterRequest;
import vladciunel.videogamecharacterapispring.dto.VideoGameCharacterResponse;
import vladciunel.videogamecharacterapispring.model.VideoGameCharacter;
import vladciunel.videogamecharacterapispring.repository.VideoGameCharacterRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@DataJpaTest
class VideoGameCharacterServiceImplTest {

    @Autowired
    private VideoGameCharacterRepository repository;

    private VideoGameCharacterServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new VideoGameCharacterServiceImpl(repository);
    }

    @Test
    void createCharacter_validInput_returnsResponseWithGeneratedIdAndSameName() {
        VideoGameCharacterRequest request = new VideoGameCharacterRequest("Mario", "Super Mario", "Plumber");

        VideoGameCharacterResponse response = service.createCharacter(request);

        assertThat(response.id()).isNotNull();
        assertThat(response.name()).isEqualTo("Mario");
    }

    @Test
    void getCharacterById_randomUuidDoesNotExist_returnsEmptyOptional() {
        UUID randomId = UUID.randomUUID();

        Optional<VideoGameCharacterResponse> response = service.getCharacterById(randomId);

        assertThat(response).isEmpty();
    }

    @Test
    void getCharacterById_existingId_returnsCorrectCharacter() {
        VideoGameCharacter character = new VideoGameCharacter();
        character.setName("Link");
        character.setGame("The Legend of Zelda");
        character.setRole("Protagonist");
        VideoGameCharacter savedCharacter = repository.save(character);

        Optional<VideoGameCharacterResponse> response = service.getCharacterById(savedCharacter.getId());

        assertThat(response).isPresent();
        VideoGameCharacterResponse found = response.get();
        assertThat(found.id()).isEqualTo(savedCharacter.getId());
        assertThat(found.name()).isEqualTo("Link");
        assertThat(found.game()).isEqualTo("The Legend of Zelda");
        assertThat(found.role()).isEqualTo("Protagonist");
    }

    @Test
    void getAllCharacters_savedCharacters_returnsAllSavedCharacters() {
        VideoGameCharacter character1 = new VideoGameCharacter();
        character1.setName("Samus");
        character1.setGame("Metroid");
        character1.setRole("Bounty Hunter");
        repository.save(character1);

        VideoGameCharacter character2 = new VideoGameCharacter();
        character2.setName("Pikachu");
        character2.setGame("Pokemon");
        character2.setRole("Electric Mouse");
        repository.save(character2);

        List<VideoGameCharacterResponse> characters = service.getAllCharacters();

        assertThat(characters).hasSize(2);
        assertThat(characters)
                .extracting(VideoGameCharacterResponse::name, VideoGameCharacterResponse::game, VideoGameCharacterResponse::role)
                .containsExactlyInAnyOrder(
                        tuple("Samus", "Metroid", "Bounty Hunter"),
                        tuple("Pikachu", "Pokemon", "Electric Mouse")
                );
    }
}
