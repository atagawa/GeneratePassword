package com.example;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * パスワードを生成するコントロールクラス<br>
 * 以下を引数に指定可能（指定しない場合はデフォルト値が適用される）<br>
 * <ul>
 * <li>パスワードの長さ：-l int（デフォルト値8）</li>
 * <li>英字の種類：-c int（0:英字なし, 1:大文字のみ許可, 2:小文字のみ許可, 3:大文字小文字混在を許可（デフォルト値））</li>
 * <li>記号の許可：-s boolean（true:記号を許可（デフォルト値）, false:記号を拒否）</li>
 * </ul>
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 */
public class PasswordController {

    /**
     * パスワードを生成するコントロールクラスのコンストラクタ
     */
    public PasswordController() {
    }

    /**
     * パスワードを生成する処理をコントロールするメソッド
     * 
     * @param args
     *            args:String[] -l length -c charCase -s acceptSymbolChar
     */
    public void run(String... args) {
        try {
            PasswordPolicyParser parser = new PasswordPolicyParser();
            Policy policy = parser.parse(args);
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            String password = new PasswordGenerator().generatePassword(policy, secureRandom);
            System.out.println(password);
        } catch (NoSuchAlgorithmException e) {
            // TODO: handle exception

        } catch (GeneratePasswordAppException e) {

        }
    }
}
