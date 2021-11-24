package com.tuana9a.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WebFile {
    String name;
    String url;
    String type;//EXPLAIN: VD: folder | file | .png | .mp4
}
