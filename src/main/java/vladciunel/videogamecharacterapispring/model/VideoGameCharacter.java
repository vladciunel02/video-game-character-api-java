package vladciunel.videogamecharacterapispring.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class VideoGameCharacter
{
    @Id @GeneratedValue()
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String game;
    @Column(nullable = false)
    private String role;
    public VideoGameCharacter() {}


    public UUID getId()
    {
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getGame(){
        return game;
    }
    public void setGame(String game){
        this.game = game;
    }

    public String getRole(){
        return role;
    }
    public void setRole(String role){
        this.role = role;
    }


}
