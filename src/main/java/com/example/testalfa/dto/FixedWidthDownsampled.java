
package com.example.testalfa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "height",
    "width",
    "size",
    "url",
    "webp_size",
    "webp"
})
public class FixedWidthDownsampled {

    @JsonProperty("height")
    private String height;
    @JsonProperty("width")
    private String width;
    @JsonProperty("size")
    private String size;
    @JsonProperty("url")
    private String url;
    @JsonProperty("webp_size")
    private String webpSize;
    @JsonProperty("webp")
    private String webp;

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

    @JsonProperty("size")
    public String getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(String size) {
        this.size = size;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("webp_size")
    public String getWebpSize() {
        return webpSize;
    }

    @JsonProperty("webp_size")
    public void setWebpSize(String webpSize) {
        this.webpSize = webpSize;
    }

    @JsonProperty("webp")
    public String getWebp() {
        return webp;
    }

    @JsonProperty("webp")
    public void setWebp(String webp) {
        this.webp = webp;
    }

}
