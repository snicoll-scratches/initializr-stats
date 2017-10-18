package com.example.initializr.stats.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class InitializrRequest implements Serializable {

	private long generationTimestamp;

	private String bootVersion;
	private String javaVersion;
	private String language;
	private String packaging;
	private String type;
	private final List<String> dependencies = new ArrayList<>();

	private boolean invalid;
	private boolean invalidJavaVersion;
	private boolean invalidLanguage;
	private boolean invalidPackaging;
	private boolean invalidType;
	private final List<String> invalidDependencies = new ArrayList<>();

	public long getGenerationTimestamp() {
		return this.generationTimestamp;
	}

	public void setGenerationTimestamp(long generationTimestamp) {
		this.generationTimestamp = generationTimestamp;
	}

	public String getBootVersion() {
		return this.bootVersion;
	}

	public void setBootVersion(String bootVersion) {
		this.bootVersion = bootVersion;
	}

	public String getJavaVersion() {
		return this.javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPackaging() {
		return this.packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getDependencies() {
		return this.dependencies;
	}

	public boolean isInvalid() {
		return this.invalid;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}

	public boolean isInvalidJavaVersion() {
		return this.invalidJavaVersion;
	}

	public void setInvalidJavaVersion(boolean invalidJavaVersion) {
		this.invalidJavaVersion = invalidJavaVersion;
	}

	public boolean isInvalidLanguage() {
		return this.invalidLanguage;
	}

	public void setInvalidLanguage(boolean invalidLanguage) {
		this.invalidLanguage = invalidLanguage;
	}

	public boolean isInvalidPackaging() {
		return this.invalidPackaging;
	}

	public void setInvalidPackaging(boolean invalidPackaging) {
		this.invalidPackaging = invalidPackaging;
	}

	public boolean isInvalidType() {
		return this.invalidType;
	}

	public void setInvalidType(boolean invalidType) {
		this.invalidType = invalidType;
	}

	public List<String> getInvalidDependencies() {
		return this.invalidDependencies;
	}

}
