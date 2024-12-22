package com.example.cosmocatsmarketplacelabs.dto.cat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CosmicCatListDto {

    private List<CosmicCatDto> cosmicCats;
}
