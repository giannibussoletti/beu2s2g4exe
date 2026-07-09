package gianni_bussoletti.beu2s2g4exe.payloads;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorsWithListsDTO(String message, LocalDateTime timestamp, List<String> errorsList) {
}
