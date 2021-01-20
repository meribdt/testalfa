
package com.example.testalfa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "onload",
    "onclick",
    "onsent"
})
public class Analytics {

    @JsonProperty("onload")
    private Onload onload;
    @JsonProperty("onclick")
    private Onclick onclick;
    @JsonProperty("onsent")
    private Onsent onsent;

    @JsonProperty("onload")
    public Onload getOnload() {
        return onload;
    }

    @JsonProperty("onload")
    public void setOnload(Onload onload) {
        this.onload = onload;
    }

    @JsonProperty("onclick")
    public Onclick getOnclick() {
        return onclick;
    }

    @JsonProperty("onclick")
    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }

    @JsonProperty("onsent")
    public Onsent getOnsent() {
        return onsent;
    }

    @JsonProperty("onsent")
    public void setOnsent(Onsent onsent) {
        this.onsent = onsent;
    }

}
