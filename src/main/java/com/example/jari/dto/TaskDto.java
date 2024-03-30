package com.example.jari.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class TaskDto {

    @Schema(
            description = "Thứ tự của nhiệm vụ",
            example = "1"
    )
    private int order;

    @Schema(
            description = "Ưu tiên của nhiệm vụ",
            example = "2"
    )
    private int priority;

    @Schema(
            description = "Loại của nhiệm vụ",
            example = "1"
    )
    private int type;

    @Schema(
            description = "Tóm tắt của nhiệm vụ",
            example = "Sửa lỗi đăng nhập"
    )
    @NotEmpty(message = "Tóm tắt không được để trống")
    @Size(min = 5, max = 30, message = "Độ dài của tên nhiệm vụ phải từ 5 đến 30 ký tự")
    private String summary;

    @Schema(
            description = "Mô tả của nhiệm vụ",
            example = "Nhiệm vụ này liên quan đến sửa lỗi đăng nhập trên trang web"
    )
    @NotEmpty(message = "Tóm tắt không được để trống")
    @Size(min = 5, max = 100, message = "Độ dài của tên nhiệm vụ phải từ 5 đến 100 ký tự")
    private String descr;

}
