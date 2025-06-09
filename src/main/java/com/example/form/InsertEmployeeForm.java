package com.example.form;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InsertEmployeeForm {
    /** id */
    private Integer id;

    /** 従業員名 */
    @NotNull(message = "名前は必須です")
    @NotBlank(message = "名前を空文字にはできません")
    private String name;

    /** 画像 */
    @NotBlank(message = "画像は必須です")
    private String image;

    /** 性別 */
    @NotBlank(message = "性別は必須です")
    private String gender;

    /** 入社日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "入社日は必須です")
    private Date hireDate;

    /** メールアドレス */
    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "メールアドレスの形式が正しくありません")
    private String mailAddress;

    /** 郵便番号 */
    @Pattern(regexp = "\\d{3}-\\d{4}",
            message = "郵便番号は○○○-○○○○の形式である必要があります")
    private String zipCode;

    /** 住所 */
    @NotBlank(message = "住所は必須です")
    private String address;

    /** 電話番号 */
    @Pattern(regexp = "\\d{2,4}-\\d{2,4}-\\d{4}",
            message = "電話番号は○○○-○○○○-○○○○の形式である必要があります")
    private String telephone;

    /** 給料 */
    @NotNull(message = "給料は必須です")
    @Min(0)
    private Integer salary;

    /** 特性 */
    @NotBlank(message = "特性を空文字にはできません")
    private String characteristics;

    /** 扶養人数 */
    @NotNull(message = "扶養人数は必須です")
    @Min(0)
    private Integer dependentsCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public Integer getDependentsCount() {
        return dependentsCount;
    }

    public void setDependentsCount(Integer dependentsCount) {
        this.dependentsCount = dependentsCount;
    }

    @Override
    public String toString() {
        return "InsertEmployeeForm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", hireDate=" + hireDate +
                ", mailAddress='" + mailAddress + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", salary=" + salary +
                ", characteristics='" + characteristics + '\'' +
                ", dependentsCount=" + dependentsCount +
                '}';
    }
}
