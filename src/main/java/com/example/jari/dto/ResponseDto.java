package com.example.jari.dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDto {
    @Schema(
            description = "Status code in the response"
    )
    private String statusCode;

    @Schema(
            description = "Status message in the response"
    )
    private String statusMsg;

    public ResponseDto(String number, String createdTaskSuccessfully) {
    }
}
