package com.ofek324.Entities.Objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "actionName",
        "description",
        "iaAvailable",
        "args",
        "language",
        "owningTeam",
        "dependedOnPrev"
})
public class ActionOfService {

    @JsonProperty("actionName")
    private String actionName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("iaAvailable")
    private Boolean iaAvailable;
    @JsonProperty("args")
    private List<ArgsAction> args = new ArrayList<ArgsAction>();
    @JsonProperty("language")
    private Language language;
    @JsonProperty("owningTeam")
    private String owningTeam;
    @JsonProperty("gitRepo")
    private String gitRepo;
    @JsonProperty("dependedOnPrev")
    private Boolean dependedOnPrev;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public ActionOfService() {
    }

    /**
     *
     * @param args
     * @param iaAvailable
     * @param owningTeam
     * @param dependedOnPrev
     * @param description
     * @param language
     * @param gitRepo
     * @param actionName
     */
    public ActionOfService(String actionName, String description, String gitRepo,
                           Boolean iaAvailable, List<ArgsAction> args,
                           Language language, String owningTeam, Boolean dependedOnPrev) {
        super();
        this.actionName = actionName;
        this.description = description;
        this.gitRepo = gitRepo;
        this.iaAvailable = iaAvailable;
        this.args = args;
        this.language = language;
        this.owningTeam = owningTeam;
        this.gitRepo = gitRepo;
        this.dependedOnPrev = dependedOnPrev;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("actionName")
    public String getActionName() {
        return actionName;
    }


    @JsonProperty("actionName")
    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("iaAvailable")
    public Boolean getIaAvailable() {
        return iaAvailable;
    }

    @JsonProperty("iaAvailable")
    public void setIaAvailable(Boolean iaAvailable) {
        this.iaAvailable = iaAvailable;
    }

    @JsonProperty("args")
    public List<ArgsAction> getArgs() {
        return args;
    }

    @JsonProperty("args")
    public void setArgs(List<ArgsAction> args) {
        this.args = args;
    }

    @JsonProperty("language")
    public Language getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Language language) {
        this.language = language;
    }

    @JsonProperty("owningTeam")
    public String getOwningTeam() {
        return owningTeam;
    }
    @JsonProperty("gitRepo")
    public String getGitRepo() {
        return gitRepo;
    }

    @JsonProperty("owningTeam")
    public void setOwningTeam(String owningTeam) {
        this.owningTeam = owningTeam;
    }
    @JsonProperty("gitRepo")
    public void setGitRepo(String gitRepo) {
        this.gitRepo = gitRepo;
    }

    @JsonProperty("dependedOnPrev")
    public Boolean getDependedOnPrev() {
        return dependedOnPrev;
    }

    @JsonProperty("dependedOnPrev")
    public void setDependedOnPrev(Boolean dependedOnPrev) {
        this.dependedOnPrev = dependedOnPrev;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("actionName", actionName)
                .append("description", description)
                .append("iaAvailable", iaAvailable)
                .append("args", args)
                .append("language", language)
                .append("owningTeam", owningTeam)
                .append("gitRepo", gitRepo)
                .append("dependedOnPrev", dependedOnPrev)
                .append("additionalProperties", additionalProperties).toString();
    }

    public enum Language {

        NODE_JS("NodeJS"),
        JAVA("Java"),
        PYTHON("Python"),
        POWERSHELL("powershell"),
        CPP("cpp"),
        ANSIBLE("Ansible"),
        NON_CODE("non-code");
        private final String value;
        private final static Map<String, Language> CONSTANTS = new HashMap<String, Language>();

        static {
            for (ActionOfService.Language c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Language(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static ActionOfService.Language fromValue(String value) {
            Language constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}