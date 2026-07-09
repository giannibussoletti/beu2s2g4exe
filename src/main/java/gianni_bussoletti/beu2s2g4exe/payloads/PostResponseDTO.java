package gianni_bussoletti.beu2s2g4exe.payloads;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostResponseDTO(UUID id,
                              LocalDateTime createdAt) {
}
