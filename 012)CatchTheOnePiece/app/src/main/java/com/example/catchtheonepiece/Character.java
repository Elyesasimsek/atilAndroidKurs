
package com.example.catchtheonepiece;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Character  implements Serializable {

    @SerializedName("characterId")
    @Expose
    private String characterId;
    @SerializedName("characterName")
    @Expose
    private String characterName;
    @SerializedName("characterImage")
    @Expose
    private String characterImage;

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(String characterImage) {
        this.characterImage = characterImage;
    }

}
