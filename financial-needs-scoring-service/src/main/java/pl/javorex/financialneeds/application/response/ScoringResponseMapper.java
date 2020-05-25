package pl.javorex.financialneeds.application.response;

import pl.javorex.financialneeds.application.dto.ScoringDto;

public final class ScoringResponseMapper {
    public ScoringRes map(ScoringDto dto) {
        return new ScoringRes(dto.getType(), dto.getResult());
    }
}
