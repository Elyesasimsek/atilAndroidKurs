
package com.example.catchtheonepiece;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class OnePieceCharacterReply {

    @SerializedName("characters")
    @Expose
    private List<Character> characters;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
