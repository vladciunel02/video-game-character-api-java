package vladciunel.videogamecharacterapispring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vladciunel.videogamecharacterapispring.model.VideoGameCharacter;


import java.util.UUID;

public interface VideoGameCharacterRepository extends JpaRepository<VideoGameCharacter, UUID> {
}
