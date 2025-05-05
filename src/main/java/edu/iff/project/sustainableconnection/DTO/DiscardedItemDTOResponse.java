package edu.iff.project.sustainableconnection.DTO;

public record DiscardedItemDTOResponse(
    Long id,
    String discardDate,
    String categoryName,
    String dropOffPointName,
    String dropOffPointDescription,
    int pointsEarned
) {}
