package reisetech.student.management.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

    private String id;

    @NotBlank(message = "名前の入力は必須です")
    private String fullName;

    @NotBlank(message = "フリガナは必須です")
    private String kanaName;
    private String nickname;

    @NotBlank(message = "メールアドレスの入力は必須です")
    @Email(message = "メールアドレスの形式が不正です")
    private String emailAddress;

    private String address;

    @Min(value = 0, message = "年齢は０歳以上を入力してください")
    @Max(value = 120, message = "年齢は120歳以下を入力してください")
    private Integer age;

    @Pattern(regexp = "^[MF]$", message = "性別は 'M' または 'F' を指定してください")
    private String sex;

    private String remark;
    private Boolean isDeleted = false;
}

