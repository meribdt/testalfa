
package com.example.testalfa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "height",
    "width",
    "mp4_size",
    "mp4"
})
public class Preview {

    @JsonProperty("height")
    private String height;
    @JsonProperty("width")
    private String width;
    @JsonProperty("mp4_size")
    private String mp4Size;
    @JsonProperty("mp4")
    private String mp4;

    @JsonProperty("height")
    public String getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(String height) {
        this.height = height;
    }

    @JsonProperty("width")
    public String getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(String width) {
        this.width = width;
    }

    @JsonProperty("mp4_size")
    public String getMp4Size() {
        return mp4Size;
    }

    @JsonProperty("mp4_size")
    public void setMp4Size(String mp4Size) {
        this.mp4Size = mp4Size;
    }

    @JsonProperty("mp4")
    public String getMp4() {
        return mp4;
    }

    @JsonProperty("mp4")
    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

}
