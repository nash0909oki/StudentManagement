package reisetech.student.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生の詳細情報(名前、年齢など）")
@Getter
@Setter
public class Student {


    @Schema(description = "Id(更新時は必須。登録時は含めないでください。）", example = "1", readOnly = true)
    private String id;

    @NotBlank(message = "名前の入力は必須です")
    @Schema(description = "名前", requiredMode = RequiredMode.REQUIRED, example = "山田太郎")
    private String fullName;

    @NotBlank(message = "フリガナは必須です")
    @Schema(description = "ふりがな", requiredMode = RequiredMode.REQUIRED, example = "やまだたろう")
    private String kanaName;

    @Schema(description = "ニックネーム", example = "たろー")
    private String nickname;

    @NotBlank(message = "メールアドレスの入力は必須です")
    @Email(message = "メールアドレスの形式が不正です")
    @Schema(description = "メールアドレス", requiredMode = RequiredMode.REQUIRED, example = "taro@example.com")
    private String emailAddress;

    @Schema(description = "住所", example = "沖縄")
    private String address;

    @Schema(description = "年齢", example = "34")
    @Min(value = 0, message = "年齢は０歳以上を入力してください")
    @Max(value = 120, message = "年齢は120歳以下を入力してください")
    private Integer age;

    @Schema(description = "性別", example = "M")
    @Pattern(regexp = "^[MF]$", message = "性別は 'M' または 'F' を指定してください")
    private String sex;

    @Schema(description = "備考", example = "特になし")
    private String remark;

    @Schema(description = "削除フラグ", example = "False", readOnly = true)
    private Boolean isDeleted = false;
}

