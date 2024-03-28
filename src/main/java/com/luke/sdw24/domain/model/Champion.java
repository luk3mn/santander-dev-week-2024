package com.luke.sdw24.domain.model;

public record Champion(
    Long id,
    String name,
    String role,
    String lore,
    String imageUrl
) {
    public String generateContextByQuestion(String question) {
        return """
                Question: %s
                Champion Name: %s
                Role: %s
                Lore: %s
                """.formatted(question,this.name, this.role, this.lore);
    }
}
