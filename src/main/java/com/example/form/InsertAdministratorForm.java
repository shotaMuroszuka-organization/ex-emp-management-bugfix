package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
public class InsertAdministratorForm {
	/** 名前 */
	@NotNull(message = "名前は必須です")
	@NotBlank(message = "名前を空文字にはできません")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスは必須です")
	@Email(message = "Eメールの形式が正しくありません")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "パスワードは必須です")
	@Length(min = 3, max = 100, message = "パスワードは3文字以上100文字以下である必要があります")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ "]";
	}

}
